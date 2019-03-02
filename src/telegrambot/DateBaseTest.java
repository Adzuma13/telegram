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
import java.util.Scanner;

/**
 *
 * @author rusco
 */
public class DateBaseTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scan = new Scanner(System.in);
       
        String name = scan.next();
        String lastName = scan.next();
        
        String url = "jdbc:postgresql://localhost:5432/telegram";
        String login = "postgres";
        String password = "rd123321010504";
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(url, login, password);
        
        Statement state = con.createStatement();
        state.executeUpdate(String.format("insert into users values(default,'%s', '%s');", name, lastName));
        ResultSet rs = state.executeQuery("Select * from users;");
//        while(rs.next()){
//            System.out.print(rs.getString(name));
//        }
    }
            
}
