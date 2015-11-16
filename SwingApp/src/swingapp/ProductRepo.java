/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ислам
 */
public class ProductRepo {
    
    public static boolean addNew(String name, String category, String weight, String price) throws DBException {
        Connection conn = DBConnection.getConnection();        
        try{
             PreparedStatement st= 
                     conn.prepareStatement("INSERT INTO products "
                             + "(name, category, weight, price)"
                             + "VALUES(\'"+name+"\',\'"+category+"\',\'"
                             +weight+"\',"+price+");");
             st.execute();
        } catch (SQLException ex) {
          throw new DBException();
        }
        return true;
    }
    
    public static String[][] getAll() throws DBException{
        String[][] table;
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet r = conn.createStatement().executeQuery("SELECT COUNT(*) AS count FROM products;");
            int c = r.getInt("count");
            table = new String[4][c];
            r = conn.createStatement().executeQuery("SELECT * FROM products;");
            for (int j=0; j<c; j++) {
                r.next();
                for (int i=0; i<4; i++) {
                    table[i][j]=r.getString(i);
                }
            }                
        } catch (SQLException ex) {
            throw new DBException();
        }
        return table;
    }
    
    public static String[] getCategories() throws DBException {
        String[] cats;
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet r = conn.createStatement().executeQuery("SELECT COUNT(*) AS count FROM categories;");
            int c = r.getInt("count");
            cats = new String[c];
            r = conn.createStatement().executeQuery("SELECT name FROM categories;");
            for (int j=0; j<c; j++) {
                r.next();                
                cats[j]=r.getString("name");
            }
        } catch (SQLException ex) {
            throw new DBException();
        }
        return cats;
    }
    
 //   public static ArrayList<Product> getAll() {        
   // }
}
