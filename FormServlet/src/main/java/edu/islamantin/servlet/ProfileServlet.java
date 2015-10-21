package edu.islamantin.servlet;

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
        req.setAttribute("text", "");
        if (req.getSession().getAttribute("user")!=null) {
            user = (User) req.getSession().getAttribute("user");
            req.setAttribute("email", user.getEmail());
            req.setAttribute("gender", user.getGender());
            req.setAttribute("about", user.getAbout());
            getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
        } else {
            req.setAttribute("text", "You have to log in");
            resp.sendRedirect("/form");
            getServletContext().getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("text", "Info has been updated!");
        user.setAbout(req.getParameter("about"));
        resp.sendRedirect(req.getRequestURI() + "?status=1");
        getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
    }

}
