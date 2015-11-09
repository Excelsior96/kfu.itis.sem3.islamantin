package edu.islamantin.servlets;

import edu.islamantin.entityes.User;
import edu.islamantin.exceptions.DBException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileServlet extends HttpServlet {
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        user = (User) req.getSession().getAttribute("user");
        if (user!=null) {
            req.setAttribute("email", user.getEmail());
            req.setAttribute("gender", user.getGender());
            req.setAttribute("about", user.getAbout());
            getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/form");
            req.setAttribute("text", "You have to log in");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            user.setAbout(req.getParameter("about"));
        } catch (DBException ex) {
            resp.sendRedirect(req.getRequestURI() + "?status=0");
            req.setAttribute("text", "Error with DB has been occured.");

        }
        resp.sendRedirect(req.getRequestURI() + "?status=1");
        req.setAttribute("text", "Info has been updated!");
    }

}
