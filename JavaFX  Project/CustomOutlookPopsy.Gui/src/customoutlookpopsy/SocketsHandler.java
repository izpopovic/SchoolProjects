/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customoutlookpopsy;

import Mail.Mail;
import MailRepository.JndiUtil;
import static customoutlookpopsy.FirstMailClientController.mailListObs;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 *
 * @author ivanz
 */
public class SocketsHandler {
    private static SocketsHandler instance;
    
    private ServerSocket server;
    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    
    private int port;
    
    private Thread connectionThread;
    
    public boolean isServer;
    
    private SocketsHandler(){
        port = Integer.parseInt(JndiUtil.getProperty("socketsPort"));
        
        try{
            server = new ServerSocket(port);
            isServer = true;
        }catch(Exception ignored){
            isServer = false;
        }
        
        connectionThread = new Thread(() -> {
            setupConnection();
            handleInput();
        });
        
        connectionThread.setDaemon(true);
        connectionThread.start();
    }
    
    private void setupConnection(){
        try{
            if(isServer){
                connection = server.accept();
                FirstMailClientController.test.setDisable(false);
            }
            else{
                // Ako nismo server, spajamo se na server
                connection = new Socket(InetAddress.getByName("localhost"), port);
            }
            
            // Povezi server i klijenta
            output = new ObjectOutputStream(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void handleInput(){
        while(true){
            try {
                Object inputObject = input.readObject();
                System.out.println("We have received:\n" + inputObject);
                
                if(isServer){
                    // handlaj mail koji je doso s klijenta na server
                    // FirstMailController - dodaj u tablicu ovaj mail
                    FirstMailClientController.mailListObs.add((Mail)inputObject);
                    System.out.println("We are server");
                    
                  //  for (Mail newMail : mailListObs) {
              //      tableColumnFrom1.setCellValueFactory(new PropertyValueFactory<>("RecieverMail"));
            //        tableColumnSubject1.setCellValueFactory(new PropertyValueFactory<>("Subject"));
          //          tableColumnDateOfReceivedEmail1.setCellValueFactory(new PropertyValueFactory<>("TimeOfSending"));
        }
        
        //tblViewEmails1.setItems(mailListObs);
            
                else{
                    // handlaj mail koji je doso sa servera na klijenta
                    SecondMailClientController.newMailObs.add((Mail)inputObject);
                    System.out.println("We are client");
                    
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void send(Object item){
        try {
            output.writeObject(item);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static SocketsHandler getInstance(){
        if(instance == null){
            instance = new SocketsHandler();
        }
        return instance;
    }
}
