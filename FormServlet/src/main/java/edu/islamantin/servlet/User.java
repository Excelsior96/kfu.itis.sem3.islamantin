package edu.islamantin.servlet;

import static edu.islamantin.servlet.FormServlet.data;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class User {
    private String email;
    private String password;
    private String gender;
    private String newsletter;
    private String about;
    
    User() {
        
    }
    
    User( String email, String password, String gender, String newsletter){
        this.email=email;
        this.password=password;
        this.gender=gender;
        this.newsletter=newsletter;
        this.about="";
    }

    protected void setAbout(String about){
        this.about = about;

    }

    protected String getAbout(){
        return about;
    }
    
    protected String getEmail(){
        return email;
    }
     
     protected String getPassword(){
        return password;
    }
    
     protected String getGender(){
        return gender;
    }
     
     protected String getNewsletter(){
        return newsletter;
    }
    
    protected User fromFile(int row) throws FileNotFoundException{
        Scanner scan = new Scanner(data);
        String line = "";
        for (int i=0; i<row; i++) {
            line = scan.nextLine();
        }
        String[] l = line.split(",");
        User u = new User();
        u.email=l[0];
        u.password=l[1];
        u.gender=l[2];
        u.newsletter=l[3];
        u.about=l[4];
        return u;
    }
    
    protected void toFile() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(data));
        Scanner scan = new Scanner(data);
        StringBuilder sb = new StringBuilder();

        while (scan.hasNext()) {
            sb.append(scan.nextLine()).append("\n");
        }
        pw.print(sb.toString());
        if (newsletter != "off") {
            newsletter = "on";
        }
        pw.print("\"" + email + "\", \"" + password + "\", \"" + gender + "\", \"" + newsletter + "\", \"" + about + "\"");
    }
}
