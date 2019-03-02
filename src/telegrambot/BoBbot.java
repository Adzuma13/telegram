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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class BoBbot extends TelegramLongPollingBot{
    
    @Override
    public void onUpdateReceived(Update update){
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        
        String userName = message.getFrom().getUserName();
        String userID = String.valueOf(message.getFrom().getId());
        String firstName  = message.getFrom().getFirstName();
        String lastName  = message.getFrom().getLastName();
        String userNumber = null;
        String status;
        
        try {
            Database db = new Database();
            if(db.insertUserIfAvailable(userID, userName, userNumber)){
                 sendMessage.setText(String.format("Привет, новый пользователь - %s\nВставь файд с тестами!", firstName));
            }
            else{
                String text = message.getText();
                if(text.toLowerCase().equals("привет")){
                    sendMessage.setText("Привет " + message.getFrom().getFirstName());
                }else if(text.toLowerCase().equals("как дела?")){
                    sendMessage.setText("Нормально " + message.getFrom().getFirstName());
                }else{
                    sendMessage.setText("sadfasfsa");
                }
            }
            execute(sendMessage);
            db.closeConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BoBbot.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BoBbot.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TelegramApiException ex) {
            Logger.getLogger(BoBbot.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    @Override
    public String getBotUsername() {
        return "Adzuma_bot";
    }

    @Override
    public String getBotToken() {
        return "686827537:AAGiyWYK0DVzJt7CTZTunD7bkezkV5r-_eo";
    }   
}
