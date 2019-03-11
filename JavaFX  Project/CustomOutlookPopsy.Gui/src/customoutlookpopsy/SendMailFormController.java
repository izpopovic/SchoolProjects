/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customoutlookpopsy;

import Mail.Mail;
import MailRepository.IRepository;
import MailRepository.MailRepository;
import MailRepository.MailSerialization;
import MailRepository.XmlRepository;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ivanz
 */
public class SendMailFormController implements Initializable {

 
   @FXML
   private GridPane gridPaneSendMailForm;
   
   @FXML
   private TextField txtToEmailAdress;
   
   @FXML
   private TextField txtSubject;
   
   @FXML
   private TextArea txtAreaMailContent;
    
   @FXML
   private Button btnSendEmail;
    
   private IRepository repo;
   
   private String stateofRepoSecondMailController = "";
   
   private SecondMailClientController secondMailClientController;
   
   public void setSecondMailClientController(SecondMailClientController secondMailClientController){
       this.secondMailClientController = secondMailClientController;
   }
   
   public void setStateOfRepoSecondMailController(String stateOfRepoSecondMailController){
       this.stateofRepoSecondMailController = stateOfRepoSecondMailController;
   }
 
   
  
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClearElements();
    }    

    private void ClearElements() {
        txtToEmailAdress.clear();
        txtSubject.clear();
        txtAreaMailContent.clear();
    }
    
    

   
     @FXML
    private void onBtnClickSendNewMail (ActionEvent event) throws InterruptedException {
       
           // Niti nek jos uvijek zapisuju u txt
         // ovo nek dodaje mail u tablicu
         Mail mail = new Mail();
         DateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY");
         Date date = new Date();
         String subject = txtSubject.getText();
         String reciever = txtToEmailAdress.getText();
         String mailContent = txtAreaMailContent.getText();
         mail.setSubject(subject);
         mail.setRecieverMail(reciever);
         mail.setDescription(mailContent);
         mail.setSenderMail("ivica.ivic@gmail.com");
         mail.setTimeOfSending(dateFormat.format(date));
         SocketsHandler.getInstance().send(mail);
         
          //XML 
          
          
          
//          FXMLLoader loader = new FXMLLoader(getClass().getResource("SecondMailClient.fxml"));
//          SecondMailClientController controller2 = loader.getController();
      
//          System.out.println(controller2);
//          String stateofReposiotry2 = controller2.getStateOfRepo();
          
         System.out.println("is null: " + " " + stateofRepoSecondMailController + "SecondMailClient:" + secondMailClientController);
         //ovo odkomenmtiraj da se vreatis
//         if (stateofRepoSecondMailController.equals(SecondMailClientController.txtDocument2)) {
//           repo = new MailRepository();
//           repo.InsertNewMail(reciever, subject, mailContent);
//           
//        }
//         else{
//             repo = new XmlRepository();
//             repo.InsertNewMail(reciever, subject, mailContent);
//           
//         }
         
        
        
        //System.out.println("Null: " + (btnSendEmail.getScene() == null));
        Stage currentStage = (Stage)btnSendEmail.getScene().getWindow();
        PrvaNit prvaNit = new PrvaNit(currentStage,1);
        DrugaNit drugaNit = new DrugaNit(currentStage);
        prvaNit.setDaemon(true);
        drugaNit.setDaemon(true);
        prvaNit.addEmail(txtToEmailAdress.getText(), txtSubject.getText(), txtAreaMailContent.getText());
        
         if (!drugaNit.isAlive()) {
             drugaNit.start();
             
         }
         if (!prvaNit.isAlive()) {
             prvaNit.start();
             
         }
        
      
        
         
         
        
       
    }
    
}
