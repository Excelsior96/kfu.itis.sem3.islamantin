package edu.islamantin.repositoryes;

import edu.islamantin.utils.DBConnection;
import edu.islamantin.entityes.User;
import edu.islamantin.exceptions.DBException;
import edu.islamantin.exceptions.InvalidDataException;
import edu.islamantin.exceptions.SuchUserDoesntExistException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.digest.DigestUtils;

public class UsersRepository {
    
    public static void addNew(User u) throws DBException{
        Connection conn = DBConnection.getConnection();        
        try{
             PreparedStatement st= 
                     conn.prepareStatement("INSERT INTO Users "
                             + "(email, pass, gender, subscription)"
                             + "VALUES(\'"+u.getEmail()+"\',\'"+u.getPassword()+"\',\'"
                             +u.getGender()+"\',"+u.getSubscription()+");");
             st.execute();
        } catch (SQLException ex) {
          throw new DBException();
        }
    }
    
    public static ArrayList<User> getAll() throws DBException {
        ArrayList<User> users = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet r = conn.createStatement().executeQuery("SELECT * FROM users;");
            while(r.next()) {                
                users.add(new User(r.getInt("ID"), r.getString("email"), r.getString("pass"),
                          r.getString("gender"), Boolean.toString(r.getBoolean("subscription")), r.getString("about")));
            }
        } catch (SQLException ex) {
            throw new DBException();
        }
        return users;
    }
    
    public static boolean isExist(String email) throws DBException {
        boolean exist = false;
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet r = conn.createStatement().executeQuery("SELECT email FROM users;");
            while(r.next()) {                
                if (email.equals(r.getString("email"))){
                    exist =true;
                    break;
                }
            }
        } catch (SQLException ex) {
            throw new DBException();
        }
        return exist;
    }
    
    public static User getUser(String email, String password) throws DBException, SuchUserDoesntExistException {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet r = conn.createStatement().executeQuery("SELECT * FROM users;");
            while(r.next()) {                
                if (email.equals(r.getString("email"))){
                    if (password.equals(r.getString("pass"))){
                        return new User
        (r.getString("email"), r.getString("pass"), r.getString("gender"),
                Boolean.toString(r.getBoolean("subscription")), r.getString("about"));
                    }
                }
            }
            throw new SuchUserDoesntExistException();
        } catch (SQLException ex) {
            throw new DBException();
        }
    }
    
    public static void addAboutTo(String about, String email) throws DBException {
        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement st=
        conn.prepareStatement("update users set about = '"+about+"' where email = '"+email+"';");
             st.execute();
        } catch (SQLException ex) {
          throw new DBException();
        }
    }
    
    public static boolean isValidData(String email, String pass) throws InvalidDataException {
        boolean valid = true;
        Pattern pat = Pattern.compile("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$");
        Matcher m = pat.matcher(email);
        if (m.matches()==false) {
            throw new InvalidDataException();
        }
        pat = Pattern.compile("[\\w]{6,}");
        m = pat.matcher(pass);
        if (m.matches()==false) {
            throw new InvalidDataException();
        }
        return valid;
    }
    
    public static String md5Apache(String pass) {
        String md5Hex = DigestUtils.md5Hex(pass); 
        return md5Hex;
    }
}
