package edu.islamantin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TableServlet extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User u = new User();
        User[] user = new User[u.getLoginsCount()];
       req.setAttribute("end",u.getLoginsCount());
        for (int i=0; i<u.getLoginsCount(); i++) {
            user[i] = u.fromFile(i+1);
            req.setAttribute("email"+i,u.getEmail());
            req.setAttribute("password"+i,u.getPassword());
            req.setAttribute("gender"+i,u.getGender());
            req.setAttribute("newsletter"+i,u.getNewsletter());
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/table.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }
    
   
}
