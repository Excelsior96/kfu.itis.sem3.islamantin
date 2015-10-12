package edu.islamantin.servlet;

import static edu.islamantin.servlet.FormServlet.data;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String email;
    private String password;
    private String gender;
    private String newsletter;
    private static int loginsCount;
    
    User() {
        
    }
    
    User(int loginsCount, String email, String password, String gender, String newsletter){
        this.email=email;
        this.password=password;
        this.gender=gender;
        this.newsletter=newsletter;
        User.loginsCount=loginsCount;
    }
    
    protected int getLoginsCount(){
        return loginsCount;
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
        String line;
        for (int i=0; i<row; i++){
            line = scan.nextLine();
        }
        ///
        User u = new User();
        u.email=___;
        u.password=___;
        u.gender=___;
        u.newsletter=___;
        return u;
    }
    
    protected void toFile() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(data));
        Scanner scan = new Scanner(data);
        StringBuilder sb = new StringBuilder();
        
        while (scan.hasNext()){
            sb.append(scan.nextLine()).append("\n");
        }
        pw.print(sb.toString());
        if (newsletter!="off") {
            newsletter="on";
        }
        pw.print("\""+email+"\", \""+password+"\", \""+gender+"\", \""+newsletter+"\"");
    }
    
    protected boolean formIsValid(){
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
}
