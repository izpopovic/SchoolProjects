/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customoutlookpopsy;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author ivanz
 */
public class DrugaNit extends Thread {

    private Stage currentStage;

    public DrugaNit(Stage currentStage) {
        this.currentStage = currentStage;
    }

    @Override
    public void run() {
        while(!interrupted()){
            synchronized(currentStage){
                try {
                    System.out.println("Druga nit čeka prvu nit da nesto odradi...");
                    currentStage.wait();
                    System.out.println("Received notification to continue running");
                    Thread.sleep(1000);
                   Platform.runLater(new Runnable(){
                       @Override public void run() {
                           
                           try {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Mail sent!");
                                alert.show();
                                Thread.sleep(2500);
                                alert.close();
                           } catch (InterruptedException ex) {
                               Logger.getLogger(DrugaNit.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           currentStage.close();
                       }});

                } catch (InterruptedException ex) {
                    Logger.getLogger(DrugaNit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
