/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MailRepository;

import Mail.Mail;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivanz
 */
public class MailSerialization implements Serializable {
    //poslani od drugog windowa
    private List<Mail> firstWindowReceivedMails;
    //poslani od prvog windowa
    private List<Mail> secondWindowReceivedMails;
    private MailSerialization mailSerialization;

    public List<Mail> getFirstWindowReceivedMails() {
        return firstWindowReceivedMails;
    }

    public void setFirstWindowReceivedMails(List<Mail> firstWindowReceivedMails) {
        this.firstWindowReceivedMails = firstWindowReceivedMails;
    }

    public List<Mail> getSecondWindowReceivedMails() {
        return secondWindowReceivedMails;
    }

    public void setSecondWindowReceivedMails(List<Mail> secondWindowReceivedMails) {
        this.secondWindowReceivedMails = secondWindowReceivedMails;
    }
    
    public boolean Load(){
        ArrayList<Mail> deserializedMails = new ArrayList<Mail>();
        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream("C:\\Users\\ivanz\\Desktop\\Seril.ser"))) {
            //object
	Object object = inStream.readObject();
           
        deserializedMails = (ArrayList<Mail>)object;
            for (Mail mail : deserializedMails) {
                System.out.println(mail);
            }
            if (object!=null) {
                return true;
            }
	

        } catch (Exception ex) {
            ex.printStackTrace();
        }
         return false;
    }
    
    public boolean Save(List<Mail> mails){
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("C:\\Users\\ivanz\\Desktop\\Seril.ser"))) {
            stream.writeObject(mails);
            return true;
        } 
        catch (IOException ex) {
	ex.printStackTrace();
        }
        return false;
    }
    
}
