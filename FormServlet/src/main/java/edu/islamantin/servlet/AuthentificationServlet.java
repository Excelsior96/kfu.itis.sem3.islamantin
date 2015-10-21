package edu.islamantin.servlet;

import java.io.IOException;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static edu.islamantin.servlet.FormServlet.data;

public class AuthentificationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("text","");
        getServletContext().getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User(req.getParameter("email"), req.getParameter("password"), req.getParameter("gender"), req.getParameter("newsletter"));
        Scanner scan = new Scanner(data);
        String line = scan.nextLine();
        while (line!=null) {
            String[] l = line.split(",");
            if (req.getParameter("email")==l[0]){
                if (req.getParameter("password")==l[1]) {
                    resp.sendRedirect("/profile");
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
                }
            }
            line = scan.nextLine();
        }
        req.setAttribute("text", "Incorrect password or e-mail");
        resp.sendRedirect(req.getRequestURI() + "?status=0");
        getServletContext().getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req, resp);
    }
}