/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customoutlookpopsy;

import MailRepository.JndiUtil;
import Utils.IChat;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;

/**
 *
 * @author ivanz
 */
public class RmiHandler {
    private static String lastMessage;
    
    public static IChat get(){
        try{
            Registry registry = LocateRegistry.getRegistry();
            String rmiKey = JndiUtil.getProperty("rmiKey");
            return (IChat)registry.lookup(rmiKey);
        } catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public static void listenForMessages(String identificator){
        Thread thread = new Thread(() -> {
            while(true){
                try{
                    String messages = get().getMessages();
                    if (identificator.equals( "prvi")) {
                        FirstMailClientController.textArea.setText(messages);
                    }
                    if (identificator.equals( "drugi")) {
                        SecondMailClientController.txtArea.setText(messages);
                    }
                       
                    
                   
                    
                    if(messages != lastMessage){
                        //TextArea textarea = FirstMailClientController.txtAreaChat;
                        //textarea2.setText(messages);
                        // textarea.setText("This would be inside a TextArea, used with SetText method LOL");
//                      System.out.println("=============================================================");
                        //System.out.println(messages);
                        
                          Thread.sleep(1000);
                    }   
                    
                    
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
