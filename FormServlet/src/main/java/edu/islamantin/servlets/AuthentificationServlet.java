package edu.islamantin.servlets;

import edu.islamantin.repositoryes.UsersRepository;
import edu.islamantin.exceptions.DBException;
import edu.islamantin.exceptions.InvalidDataException;
import edu.islamantin.exceptions.SuchUserDoesntExistException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthentificationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if(req.getParameter("email") != null && req.getParameter("password") != null){
            try {
                if (UsersRepository.isValidData(req.getParameter("email"), req.getParameter("password"))) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", UsersRepository.getUser(req.getParameter("email"), UsersRepository.md5Apache(req.getParameter("password"))));
                    resp.sendRedirect("/profile");
                }
            } catch (DBException ex) {
                req.setAttribute("text", "Error with DB has been occured.");
                getServletContext().getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req, resp);
                resp.sendRedirect(req.getRequestURI() + "?status=0");
            } catch (SuchUserDoesntExistException ex){
                req.setAttribute("text", "Such user doesn't exist.");
                getServletContext().getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req, resp);
                resp.sendRedirect(req.getRequestURI() + "?status=0");
            }
            catch(InvalidDataException ex) {
                req.setAttribute("text", "Incorrect password or e-mail!");
                getServletContext().getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req, resp);
                resp.sendRedirect(req.getRequestURI() + "?status=0");
            }
        } else {
            req.setAttribute("text", "You have to fill all fields!");
            getServletContext().getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req, resp);
            resp.sendRedirect(req.getRequestURI() + "?status=0");
        }
    }
}