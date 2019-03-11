/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MailRepository;




import Mail.Mail;
import java.util.ArrayList;

/**
 *
 * @author ivanz
 */
public interface IRepository  {
    //properties na projekt pa libraries i dodas tu import
    //abstract public ArrayList<Mail> ReadNewMails();
    
    abstract public ArrayList<Mail> ReadMails();
    
    abstract public ArrayList<Mail> ReadMails2();

    abstract public boolean InsertNewMail(String toEmailAdress, String subject, String mailContent);
    
    abstract public boolean InsertNewMail2(String toEmailAdress, String subject, String mailContent);

    abstract public Integer GetNewMailID(Integer identificator);


    
    
    
    
}
