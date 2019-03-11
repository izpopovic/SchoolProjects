/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customoutlookpopsy;


import Mail.Mail;
import MailRepository.IRepository;
import MailRepository.MailRepository;
import MailRepository.XmlRepository;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class SendMailFormController_1 implements Initializable {

 
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
    
    private String stateofRepoFirstMailController = "";
   
    
    public void setStateOfRepoFirstMailController(String stateofRepoFirstMailController){
       this.stateofRepoFirstMailController = stateofRepoFirstMailController;
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
    private void onBtnClickSendNewMail2 (ActionEvent event) {
      
        
            // Niti nek jos uvijek zapisuju u txt
         // ovo nek dodaje mail u tablicu
         DateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY");
         Date date = new Date();
         Mail mail = new Mail();
         String subject = txtSubject.getText();
         String reciever = txtToEmailAdress.getText();
         String mailContent = txtAreaMailContent.getText();
         mail.setSubject(subject);
         mail.setRecieverMail(reciever);
         mail.setDescription(mailContent);
         mail.setSenderMail("perica.peric@gmail.com");
         mail.setTimeOfSending(dateFormat.format(date));
         SocketsHandler.getInstance().send(mail);
        
          //XML 
          
          String repoStateFristMail = FirstMailClientController.stateOfRepo;
         
          System.out.println("is null: " + (String)FirstMailClientController.stateOfRepo
           + " " + FirstMailClientController.txtDocument);
          //ovo odkomentiraj da se vratis
//         if (stateofRepoFirstMailController.equals(FirstMailClientController.txtDocument)) {
//           repo = new MailRepository();
//           repo.InsertNewMail2(reciever, subject, mailContent);
//           
//        }
//         else{
//             repo = new XmlRepository();
//             repo.InsertNewMail2(reciever, subject, mailContent);
//           
//         }
        
        
        //System.out.println("Null: " + (btnSendEmail.getScene() == null));
        Stage currentStage = (Stage)btnSendEmail.getScene().getWindow();
        PrvaNit prvaNit = new PrvaNit(currentStage,2);
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
