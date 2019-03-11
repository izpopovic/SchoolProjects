/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mail;

import java.io.Serializable;

/**
 *
 * @author ivanz
 */
public class Mail implements Serializable {
    
    private Integer IDMail;
    //Saljem vam novi mail hitno
    private String Subject;
    // to: izpsadsad@outlook.com
    private String SenderMail;
     //from: me@gmail.com
    private String RecieverMail;
    private String Description;
    private String TimeOfReceivng;
    private String TimeOfSending;
  

 

  
   
    public Integer getIDMail() {
        return IDMail;
    }

    @Override
    public String toString() {
        return "Subject: " + Subject + ",\n Mail from:" + SenderMail + ",\n Mail to: " + RecieverMail + ",\n Description: " + Description;
    }

    public void setIDMail(Integer IDMail) {
        this.IDMail = IDMail;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getSenderMail() {
        return SenderMail;
    }

    public void setSenderMail(String SenderMail) {
        this.SenderMail = SenderMail;
    }

    public String getRecieverMail() {
        return RecieverMail;
    }

    public void setRecieverMail(String RecieverMail) {
        this.RecieverMail = RecieverMail;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getTimeOfReceivng() {
        return TimeOfReceivng;
    }

    public void setTimeOfReceivng(String TimeOfReceivng) {
        this.TimeOfReceivng = TimeOfReceivng;
    }

    public String getTimeOfSending() {
        return TimeOfSending;
    }

    public void setTimeOfSending(String TimeOfSending) {
        this.TimeOfSending = TimeOfSending;
    }
   
    
}
