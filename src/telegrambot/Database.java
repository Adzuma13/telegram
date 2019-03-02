/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrambot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rusco
 */
public class Database {
    private String url = "jdbc:postgresql://localhost:5432/telegram";
    private String login = "postgres";
    private String password = "rd123321010504";
    
    private Connection con;
    
    Database() throws ClassNotFoundException, SQLException{
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(url, login, password);
    }
    
    private boolean isOldUser(String userID) throws SQLException{
        boolean res;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(String.format("Select * from users where userid = %s", userID));
        res = rs.next() ? true : false;
        stmt.close();
        System.out.println("res = " + res);
        return res;
    }
    
    public boolean insertUserIfAvailable(String userID, String userName, String userNumber) throws SQLException{
        Statement stmt = con.createStatement();
        boolean res = false;
        if(!isOldUser(userID)){
            System.out.println("zawel");
            stmt.executeUpdate(String.format("insert into users values(default,'%s', '%s', 'null', '%s');", userID, userName, "0"));
            res = true;
        }
        stmt.close();
        return res;
    }
    
    public int getStatus(String user_id) throws SQLException{
        Statement stmt = con.createStatement();
        int res = 0;
        
        ResultSet rs = stmt.executeQuery(String.format("select * from users where userid = %s", user_id));
        if(rs.next()){
            res = Integer.valueOf(rs.getString("status"));
        }
        stmt.close();
        return res;
    }
    
    public void closeConnection() throws SQLException{
        con.close();
    }
    
    
}
