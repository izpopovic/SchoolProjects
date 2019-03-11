/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customoutlookpopsy;


import MailRepository.IRepository;
import MailRepository.MailRepository;
import MailRepository.XmlRepository;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author ivanz
 */
public class PrvaNit extends Thread {
    
    private String toEmailAdress;
    private String subject;
    private String mailContent;
    private final Stage currentStage;
    private int identificator;
    IRepository repo = new MailRepository(); 
    IRepository repoXml = new XmlRepository();
    
    

    public PrvaNit(Stage currentStage,int identificator) {
        this.currentStage = currentStage;
        this.identificator = identificator;
    }

    
    
    @Override
    public void run() {
        while(!interrupted()){
            synchronized(currentStage){
                if(emailAvailable()){
                    boolean insertNewMail = false;
                    
                   //tu ga uopće ne lovi
                    if (identificator == 2) {
                       
                    
                        System.out.println(FirstMailClientController.stateOfRepo);
                        if (FirstMailClientController.returnStateOfRepo().equals("Text document (.txt)")) {
                            repo = new MailRepository();
                        }
                        if (FirstMailClientController.returnStateOfRepo().equals("XML document (.xml)")) {
                            repo = new XmlRepository();
                        }
                        repo.InsertNewMail2(toEmailAdress,subject,mailContent);
                        insertNewMail = repoXml.InsertNewMail2(toEmailAdress, subject, mailContent);
                    }
                    if (identificator == 1) {
                        if (SecondMailClientController.returnStateOfRepo().equals("Text document (.txt)")) {
                            repo = new MailRepository();
                        }
                        if (SecondMailClientController.returnStateOfRepo().equals("XML document (.xml)")) {
                            repo = new XmlRepository();
                        }
                        repo.InsertNewMail(toEmailAdress,subject,mailContent);
                        insertNewMail = repoXml.InsertNewMail(toEmailAdress, subject, mailContent);
                        
                    }
                   
                    insertNewMail = true;
                    
                    
                    if(insertNewMail){
                        try {
                            System.out.println("Insert succeeded, notifying DrugaNit...");
                            Thread.sleep(1000);
                            Platform.runLater(new Runnable(){
                       @Override public void run() {
                           
                           try {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Mail sending...");
                                alert.show();
                                Thread.sleep(3000);
                                alert.close();
                           } catch (InterruptedException ex) {
                               Logger.getLogger(DrugaNit.class.getName()).log(Level.SEVERE, null, ex);
                           }
                       }});
                            currentStage.notifyAll();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PrvaNit.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else{
                        System.out.println("Insert failed...");
                    }
                        
                    toEmailAdress = null;
                    subject = null;
                    mailContent = null;
                }
            }
        }
        
        
        
    }
    
    private boolean emailAvailable(){
        return toEmailAdress != null && !toEmailAdress.isEmpty() &&
                subject != null && !subject.isEmpty() &&
                mailContent != null && !mailContent.isEmpty();
    }

    public void addEmail(String toEmailAdress, String subject, String mailContent) {
        this.toEmailAdress = toEmailAdress;
        this.subject = subject;
        this.mailContent = mailContent;
    }
    
    
    
    
}
