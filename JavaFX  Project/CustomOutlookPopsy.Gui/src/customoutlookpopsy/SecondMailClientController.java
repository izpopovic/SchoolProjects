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
import Utils.Chat;
import Utils.IChat;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ivanz
 */
public class SecondMailClientController implements Initializable {

    @FXML
    private TabPane tbPaneRecieveMail;
    
     @FXML
    private Tab tabInboxSender2;
    
    @FXML
    private Tab tabOutboxSender2;
    
    @FXML
    private TableView<Mail> tblViewEmails2;
    
    @FXML
    private TableColumn tableColumnFrom2;
    
    @FXML
    private TableColumn tableColumnSubject2;
    
    @FXML
    private TableColumn tableColumnDateOfReceivedEmail2;
    
     @FXML
    private Button button2Send;
    
    @FXML
    private Button button2Refresh;
    
    @FXML
    private TextArea txtAreaChat;
    
    @FXML
    private TextField txtChatMessage;
    
    @FXML
    private Button btnSendChatMessage;
    
    @FXML
    public ComboBox cbRepoSwitch;
    
    IRepository repo = new MailRepository();
   
    
    public static TextArea txtArea;
    public static ObservableList<Mail> newMailObs;
    
    public static final String txtDocument2 = "Text document (.txt)";
    public static final String xmlDocument2 = "XML document (.xml)";
    
    public static String stateOfRepo2 = "";
    public static String stateOfOtherClientRepo;
    private static final ObservableList<String> options = 
    FXCollections.observableArrayList(
        txtDocument2,
        xmlDocument2
    );
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        txtArea = txtAreaChat;
        
        cbRepoSwitch.getItems().addAll(options);
        
        
        ShowReceivedMails();
       
        
    }    
    
      @FXML
    private void onCbRepoSwitch (ActionEvent event) throws Exception {
        checkWhatRepositoryToUse();
          System.out.println("On repo switch: " + stateOfRepo2);
         //System.out.println(cbRepoSwitch.getValue());
      //showReceivedMails2();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("SendMailFormController.fxml"));
//        SendMailFormController controllerSendMail = loader.getController();
//static{} inicijalizator; i tu guras meso
       
                //KADA BI OVAJ TU DIO KODA RADIO APLIKACIJA BI RADILA AS INTENDED
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SendMailForm.fxml"));
            Parent root = (Parent)loader.load();
            SendMailFormController controllerSendMail = loader.getController();//OVO VRACA NULL ZA CONTROLLERSENDMAIL
            System.out.println("Controller sendmailform: " + controllerSendMail);
            controllerSendMail.setStateOfRepoSecondMailController(stateOfRepo2);
           
        
            //controllerSendMail.setSecondMailClientController(this);
        //System.out.println(stateOfRepo2);
        repo.ReadMails();
        }
    
     private void checkWhatRepositoryToUse() {
        
         
        if (cbRepoSwitch.getValue().toString().equals(xmlDocument2)) {
            stateOfRepo2 = "XML document (.xml)";
            repo = new XmlRepository();
            System.out.println(stateOfRepo2);
        }
        else{
            stateOfRepo2 = "Text document (.txt)";
            repo = new MailRepository();
            System.out.println(stateOfRepo2);
        }
    }

 
     
    public static String returnStateOfRepo(){
        System.out.println("Return state of repo func: " + stateOfRepo2);
        
        return stateOfRepo2;
    }
    
    public String getStateOfRepo(){
        return SecondMailClientController.stateOfRepo2;
    }
    
     
    public static String checkStateOfOtherClientsRepo(){
        return stateOfOtherClientRepo = FirstMailClientController.returnStateOfRepo();
}
     
   
    
    
    
     @FXML
    private void onBtnSendChatMessage (ActionEvent event) throws Exception {
      
        // Ovdje umjesto ServeR:  stavis neko ime koje je iz nekakvog textboxa kao da
        // se diferenciraju poruke
        String chatMessage ="Perica: " + txtChatMessage.getText();
       try {
           RmiHandler.get().sendMessage(chatMessage);
       } catch (RemoteException ex) {
           ex.printStackTrace();
       }
       txtChatMessage.clear();
        
    }
    
    
    
    
    
       @FXML
    private void onBtnClickNewMail2(ActionEvent event) {
        //otvori novi scene
        //checkWhatRepositoryToUse();
        checkStateOfOtherClientsRepo();
        //returnStateOfRepo();
          try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SendMailForm_1.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
          }
    }
    
    
      @FXML
    private void onBtnClickRefresh2 (ActionEvent event)  {
      //zove funkciju koja iz prve txtice ucitava u drugu tablicu sve podatke
     
      
      
        //checkStateOfOtherClientsRepo();
        //checkWhatRepositoryToUse();
        ShowReceivedMails();
        //returnStateOfRepo();
        
        
        
        
        
        }
       private void ShowReceivedMails() {
        //novi majlovi
        //checkWhatRepositoryToUse();

        ArrayList<Mail> receivedMails = repo.ReadMails();
          
        
         newMailObs = (ObservableList<Mail>) FXCollections.observableArrayList(receivedMails);
        
        for (Mail newMail : newMailObs) {
            tableColumnFrom2.setCellValueFactory(new PropertyValueFactory<>("RecieverMail"));
            tableColumnSubject2.setCellValueFactory(new PropertyValueFactory<>("Subject"));
            tableColumnDateOfReceivedEmail2.setCellValueFactory(new PropertyValueFactory<>("TimeOfSending"));
            
        }
        tblViewEmails2.setItems(newMailObs);
    }
    
    
}
