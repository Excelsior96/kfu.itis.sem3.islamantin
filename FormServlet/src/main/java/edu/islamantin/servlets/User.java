package edu.islamantin.servlets;

public class User {
    private int id;
    private String email;
    private String password;
    private String gender;
    private String subscription;
    private String about;
    
    User(int id, String email, String password,  String gender, String subscription, String about){
        this.id = id;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.subscription = subscription;
        this.about = about;
    }
    
    User(String email, String password,  String gender, String subscription){
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.subscription = subscription;
        this.about = "";
    }
    
 /*   protected void setEmail(String email){
        this.email = email;
    }
    
    protected void setPassword(String password){
        this.password = password;
    }
     
    protected void setGender(String gender){
         this.gender = gender;
    }
      
    protected void setSubscription(String subscription){
        this.subscription = subscription;
    } */
    protected void setAbout(String about){
        this.about = about;
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
     
     protected String getSubscription(){
        return subscription;
    }
     
     protected String getAbout(){
        return about;
    }
    
    /*
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
    } */
}
