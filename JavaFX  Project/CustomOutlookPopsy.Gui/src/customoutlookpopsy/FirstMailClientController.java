/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customoutlookpopsy;



import java.io.File;
import java.nio.file.Files;
import java.util.Collection;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Mail.Mail;

import MailRepository.IRepository;
import MailRepository.MailRepository;
import MailRepository.MailSerialization;
import MailRepository.XmlRepository;
import Utils.Chat;
import Utils.IChat;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author ivanz
 */
public class FirstMailClientController implements Initializable {
    
    
    @FXML
    private AnchorPane anchorPaneStarter;
    
    //prva tablica
    @FXML
    private TabPane tbPaneSendMail;
    
    @FXML
    private Tab tabInboxSender1;
    
    @FXML
    private Tab tabOutboxSender1;
    
    @FXML
    private TableView<Mail> tblViewEmails1;
    
    @FXML
    private TableColumn<Mail,String> tableColumnFrom1;
    
    @FXML
    private TableColumn<Mail,String> tableColumnSubject1;
    
    @FXML
    private TableColumn<Mail,String> tableColumnDateOfReceivedEmail1;
    
    //druga tablica za primanje mailova
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
    
    //Buttoni
    
    @FXML
    private Button button1NewMail;
    
    @FXML
    private Button button1Refresh;
    
    @FXML
    private Button button2Send;
    
    @FXML
    private Button button2Refresh;
    
    @FXML 
    private Button btnLoadSerial;
    
    @FXML 
    private Button btnSaveSerial;
    
    @FXML
    private TextArea txtAreaChat;
    
    @FXML
    private TextField txtChatMessage;
    
    @FXML
    private Button btnSendChatMessage;
    
    @FXML
    public  ComboBox cbRepoSwitch;
    
    private MailSerialization mailSerialization = new MailSerialization();
    
    private IRepository repo = new MailRepository();
    //IRepository repoXML = new XmlRepository();
    
    public static Button test;
    public static TextArea textArea;
    public static ObservableList<Mail> mailListObs;
    
    public static final String txtDocument = "Text document (.txt)";
    public static final String xmlDocument = "XML document (.xml)";
    
    public static String stateOfRepo = "";
    public static String stateOfOtherClientRepo;
    
   
    
    private static final ObservableList<String> options = 
    FXCollections.observableArrayList(
        txtDocument,
        xmlDocument
    );

   
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //pokazuje samo za drugu tablicu
        //ShowReceivedMails();
        cbRepoSwitch.getItems().addAll(options);
       
        test = button1NewMail;
        textArea = txtAreaChat;
        
        showReceivedMails2();
       
        
        
       
    }
     @FXML
    private void onCbRepoSwitch (ActionEvent event) throws Exception {
        checkWhatRepositoryToUse();
         System.out.println("Repo Switch : " + stateOfRepo);
         //System.out.println(cbRepoSwitch.getValue());
      //showReceivedMails2();
        repo.ReadMails();
         //System.out.println(returnStateOfRepo());
        }

    public  void checkWhatRepositoryToUse() {
        
      
        
        if (cbRepoSwitch.getValue().toString().equals(xmlDocument)) {
            stateOfRepo = "XML document (.xml)";
            repo = new XmlRepository();
        }
        else {
            stateOfRepo = "Text document (.txt)";
            repo = new MailRepository();
        }
    }
    
    public static String returnStateOfRepo(){
        System.out.println(stateOfRepo);
        return stateOfRepo;
    }
    
//    public static String checkForOtherControllerRepoState(){
//        return stateOfOtherClientRepo = SecondMailClientController.getRepoState2;
//        //unreachable statement???
//    }
   
    
    
    
    
    @FXML
    private void onBtnSendChatMessage (ActionEvent event) {
      
          // Ovdje umjesto ServeR:  stavis neko ime koje je iz nekakvog textboxa kao da
        // se diferenciraju poruke
        String chatMessage = "Ivica: " +  txtChatMessage.getText();
       try {
           RmiHandler.get().sendMessage(chatMessage);
       } catch (RemoteException ex) {
           ex.printStackTrace();
       }
       txtChatMessage.clear();
    }
    
   @FXML
    private void onBtnClickNewMail(ActionEvent event) {
        //otvori novi scene
         //checkWhatRepositoryToUse();
         //checkForOtherControllerRepoState();
         //returnStateOfRepo();
         
         System.out.println(cbRepoSwitch.getValue().toString());
         
          try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SendMailForm.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
                
                
        } catch(Exception e) {
           e.printStackTrace();
          }
    }
    
  
    
    @FXML
    private void onBtnClickRefresh1 (ActionEvent event) throws Exception {
      //zove funkciju koja iz druge txtice ucitava u prvu tablicu sve
        
        //checkWhatRepositoryToUse();
        showReceivedMails2();
        //checkForOtherControllerRepoState();
        //returnStateOfRepo();
        }
    
    public String getCBRepoValue(){
        return cbRepoSwitch.getValue().toString();
    }

    private void showReceivedMails2() {
        
        
        ArrayList<Mail> mails = repo.ReadMails2();
        
        //mozes ju samo jednom kreirati
        //trreba dodati ovo u fejk listu i binditat
        mailListObs = (ObservableList<Mail>) FXCollections.observableArrayList(mails);
        
        for (Mail newMail : mailListObs) {
            tableColumnFrom1.setCellValueFactory(new PropertyValueFactory<>("RecieverMail"));
            tableColumnSubject1.setCellValueFactory(new PropertyValueFactory<>("Subject"));
            tableColumnDateOfReceivedEmail1.setCellValueFactory(new PropertyValueFactory<>("TimeOfSending"));
        }
        
        tblViewEmails1.setItems(mailListObs);
    }
    
    
 
    @FXML
    private void onBtnLoadSerial (ActionEvent event) {
        
        if (mailSerialization.Load()) {
             Alert alert = new Alert(AlertType.INFORMATION,"Deserijalizacija uspješna!");
             alert.showAndWait();
        }
        else{
            Alert alert = new Alert(AlertType.INFORMATION,"Deserijalizacija neuspješna!");
            alert.showAndWait();
        }
       
    }
    @FXML
    private void onBtnSaveSerial (ActionEvent event) {
        List<Mail> mailovi2 = repo.ReadMails2();
        List<Mail> mailovi = repo.ReadMails();
        List<Mail> sviMailovi = new ArrayList<Mail>();
        sviMailovi.addAll(mailovi);
        sviMailovi.addAll(mailovi2);
        
        if (mailSerialization.Save(sviMailovi)) {
            Alert alert = new Alert(AlertType.INFORMATION,"Serijalizacija uspješna!");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(AlertType.INFORMATION,"Serijalizacija neuspješna!");
            alert.showAndWait();
        }

    }
    
    
    
    
}
    
