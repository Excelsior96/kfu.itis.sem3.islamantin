package edu.islamantin.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.*;


public class FormServlet extends HttpServlet {
    public static final File data = new File("E:\\Workspace\\Repositories\\edu\\sem3\\FormServlet\\src\\main\\webapp\\WEB-INF\\data\\data.csv");
    private int loginsCount=0;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("text","");
        getServletContext().getRequestDispatcher("/WEB-INF/views/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User(loginsCount++,req.getParameter("email"), req.getParameter("password"), req.getParameter("gender"), req.getParameter("newsletter"));
        if (user.formIsValid()) {
            user.toFile();
            req.setAttribute("text", "You have registered successfully!");
            resp.sendRedirect(req.getRequestURI()+"?status=1");
        } else {
             req.setAttribute("text", "The login details are incorrect");
             resp.sendRedirect(req.getRequestURI()+"?status=2");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/form.jsp").forward(req, resp);
    }


//    protected String getHTML(String text){
//        StringBuilder sb = new StringBuilder();
//        sb.append("<!DOCTYPE html>");
//        sb.append("<html>");
//        sb.append("<head>");
//        sb.append("<meta charset=\"utf-8\">");
//        sb.append("<title>registration</title>");
//        sb.append("</head>");
//        sb.append("<body>");
//        sb.append("<div>");
//        sb.append("<h1>"+text+"</h1>");
//        sb.append("<form action=\"\" method=\"POST\">");
//        sb.append("<table>");
//        sb.append("<th>");
//        sb.append("<tr>");
//        sb.append("<p><input type=\"textarea\" name=\"e-mail\" placeholder=\"e-mail\"></p>");
//        sb.append("</tr>");
//        sb.append("<tr>");
//        sb.append("<p><input type=\"textarea\" name=\"password\" placeholder=\"password\"></p>");
//        sb.append("</tr>");
//        sb.append("<tr>");
//        sb.append("<p><input type=\"radio\" name=\"gender\" value=\"m\" checked>�");
//        sb.append("<input type=\"radio\" name=\"gender\" value=\"f\">�</p>");
//        sb.append("</tr>");
//        sb.append("<tr>");
//        sb.append("<p><input type=\"checkbox\" name=\"newsletter\">����������� �� �������</p>");
//        sb.append("<input type=\"submit\">");
//        sb.append("</tr>");
//        sb.append("</th>");
//        sb.append("</table>");
//        sb.append("</form>");
//        sb.append("</div>");
//        sb.append("</body>");
//        sb.append("</html>");
//        return sb.toString();
//    }
}
