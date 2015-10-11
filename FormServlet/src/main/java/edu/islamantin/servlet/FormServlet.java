package edu.islamantin.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FormServlet extends HttpServlet {
    public static final File data = new File("C:\\data.txt");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (formIsValid(req.getParameter("e-mail"),req.getParameter("password"))) {
            toFile(req.getParameter("e-mail"), req.getParameter("password"), req.getParameter("gender"), req.getParameter("newsletter"));
            req.setAttribute("text", "You have registered successfully!");
        } else req.setAttribute("text", "The login details are incorrect");
    }

    protected void toFile(String email, String password, String gender, String newsletter) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(data));
        Scanner scan = new Scanner(data);
        StringBuilder sb = new StringBuilder();
        while (scan.hasNext()){
            sb.append(scan.nextLine()).append("\n");
        }
        pw.print(sb.toString());
        if (newsletter == null){
            newsletter = "newsletting off";
        } else newsletter = "newsletting on";
        if (gender == "f") {
            gender = "female";
        } else gender = "male";
        pw.printf("%30s\t%20s\t%10s\t%5s", email, password, gender, newsletter);
    }
    protected boolean formIsValid(String email, String password){
        Pattern p = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Matcher m = p.matcher(email);
        if (m.matches()==false){
            return false;
        }
        p = Pattern.compile("\\w{4,}");
        m = p.matcher(password);
        if (m.matches()==false){
            return false;
        }
        return true;
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
