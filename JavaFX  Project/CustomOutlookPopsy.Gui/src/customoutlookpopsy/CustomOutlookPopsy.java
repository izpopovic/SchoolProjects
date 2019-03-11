/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customoutlookpopsy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ivanz
 */
public class CustomOutlookPopsy extends Application {
    
   
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root;
        if(SocketsHandler.getInstance().isServer){
            
            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
             
             
            RmiHandler.listenForMessages("prvi");
        }
        else{
         
            root = FXMLLoader.load(getClass().getResource("SecondMailClient.fxml"));
           
            RmiHandler.listenForMessages("drugi");
        }
        
        
        Scene scene = new Scene(root);
        //Scene scene2 = new Scene(root2);
        
        stage.setScene(scene);
        stage.show();
        //stage.setScene(scene2);
        //stage.show();
    }
    
     

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
