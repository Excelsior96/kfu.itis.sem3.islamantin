package edu.islamantin.servlets;

import edu.islamantin.repositoryes.UsersRepository;
import edu.islamantin.entityes.User;
import edu.islamantin.exceptions.DBException;
import edu.islamantin.exceptions.InvalidDataException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.*;


public class FormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if(req.getParameter("email") != null && req.getParameter("password") != null){
            try {
                if (UsersRepository.isValidData(req.getParameter("email"), req.getParameter("password"))) {

                        if (UsersRepository.isExist(req.getParameter("email"))==false) {
                            String subscription = "false";
                            if (req.getParameter("subscription")!=null) {
                                subscription = "true";
                            }
                            User u = new User
                (req.getParameter("email"),UsersRepository.md5Apache(req.getParameter("password")),req.getParameter("gender"),subscription);
                            UsersRepository.addNew(u);
                            req.setAttribute("text", "You have registered successfully!");
                            getServletContext().getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req, resp);
                            resp.sendRedirect("/form");
                        }
                }
            } catch (DBException ex) {
                req.setAttribute("text", "Error with DB has been occured.");
                getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
                resp.sendRedirect(req.getRequestURI() + "?status=0");
            }
            catch(InvalidDataException ex) {
                req.setAttribute("text", "Incorrect registration details!");
                getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
                resp.sendRedirect(req.getRequestURI() + "?status=0");
            }
        } else {
            req.setAttribute("text", "You have to fill all fields!");
            getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
            resp.sendRedirect(req.getRequestURI() + "?status=0");
        }
    }
}
