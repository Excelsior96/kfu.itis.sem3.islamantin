package edu.islamantin.servlets;

import edu.islamantin.exceptions.DBException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.digest.DigestUtils;


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
            if (isValid(req.getParameter("email"), req.getParameter("password"))) {
                try {
                    if (UsersRepository.isAlreadyExist(req.getParameter("email"))==false) {
                        String subscription = "false";
                        if (req.getParameter("subscription")!=null) {
                            subscription = "true";
                        }
                        User u = new User
            (req.getParameter("email"),md5Apache(req.getParameter("password")),req.getParameter("gender"),subscription);
                        UsersRepository.addNew(u);
                        HttpSession session = req.getSession();
                        session.setAttribute("logged", true);
                        req.setAttribute("text", "You have registered successfully!");
                        getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
                        resp.sendRedirect("/profile");
                    }
                } catch (DBException ex) {
                    req.setAttribute("text", "Error with DB has been occured.");
                    getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
                    resp.sendRedirect(req.getRequestURI() + "?status=0");
                }
            } else {                        
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
    
    
    boolean isValid(String email, String pass) {
        boolean valid = true;
        Pattern pat = Pattern.compile("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$");
        Matcher m = pat.matcher(email);
        if (m.matches()==false) {
            valid = false;
        }
        pat = Pattern.compile("[\\w]{6,}");
        m = pat.matcher(pass);
        if (m.matches()==false) {
            valid = false;
        }
        return valid;
    }
    
    public static String md5Apache(String pass) {
        String md5Hex = DigestUtils.md5Hex(pass); 
        return md5Hex;
    }
}
