/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MailRepository;

import Mail.Mail;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author ivanz
 */
public class MailRepository implements IRepository {

    private final String SEPARATOR = "///";
    //pravi se kao da je separator kaze sve iza njega je jedan mail dok ne dodje do njega
    private final String MAIL_SEPARATOR = "NEW_LINE";
    private final String PATH_RECEIVED_MAILS = "C:\\\\Users\\\\ivanz\\\\Desktop\\\\Algebra\\\\5. semestar\\\\Java 2\\\\CustomOutlookPopsy.Repository\\\\src\\\\ReceivedMails.txt";
    private final String PATH_RECEIVED_MAILS2 = "C:\\\\Users\\\\ivanz\\\\Desktop\\\\Algebra\\\\5. semestar\\\\Java 2\\\\CustomOutlookPopsy.Repository\\\\src\\\\ReceivedMails2.txt";
    private final String DELIMITER = SEPARATOR;
    DateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY");
    //DateFormat dateFormatYear = new SimpleDateFormat("YYYY");


    //samo ocu vidjeti dali mogu citat mailove iz txtice
    @Override
    public ArrayList<Mail> ReadMails() {
        ArrayList<Mail> mailsFromTxt = new ArrayList<Mail>();
        File file = new File(PATH_RECEIVED_MAILS);
        Scanner reader = null;
        try {
            reader = new Scanner(file);
            //neka reader koristi delimiter
            reader.useDelimiter(MAIL_SEPARATOR);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MailRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (reader.hasNext()) {
            Mail mail = new Mail();
            String[] lineData = reader.next().split(SEPARATOR);

            mail.setIDMail(Integer.parseInt(lineData[0]));
            mail.setRecieverMail(lineData[1]);
            mail.setSubject(lineData[2]);
            mail.setDescription(lineData[3]);
            mail.setTimeOfSending(lineData[4]);
            //reader.nextLine();

            mailsFromTxt.add(mail);
        };
        return mailsFromTxt;
    }

    @Override
    public boolean InsertNewMail(String toEmailAdress, String subject, String mailContent) {

        Date date = new Date();
        //System.out.println(dateFormat.format(date));
        try {
            FileWriter fstream = new FileWriter(PATH_RECEIVED_MAILS, true);
            try (BufferedWriter buffWriter = new BufferedWriter(fstream)) {
                Integer newID = GetNewMailID(1);
                String data = String.join(SEPARATOR, newID.toString(), "ivica.ivic@gmail.com", subject, mailContent, dateFormat.format(date));
                buffWriter.write(data);
                buffWriter.write(MAIL_SEPARATOR);
                //buffWriter.newLine();
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(MailRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public Integer GetNewMailID(Integer identificator) {
        // Procitaj mailove iz txtice
        ArrayList<Mail> mails;
        if (identificator.equals(1)) {
            mails = ReadMails();
        }
        else{
            mails = ReadMails2();
        }
        
        
        // Deklariraj minimalni dozvoljeni id
        Integer id = 1;
        
        // Idi kroz petlju dok ne nades neki slobodan id
        while(true){
            // Deklariraj da trenutni id nije naden za sad
            boolean anyFound = false;
            
            // Prodi kroz sve mailove
            for(Mail mail : mails){
                // Ako trenutni mail vec ima id koji je jednak varijabli id...
                if(mail.getIDMail().equals(id) == true){
                    // ID nije unique
                    anyFound = true;
                    // Ne moras vise gledat za uniqueness u ovoj listi mailova
                    break;
                }
            }
            
            // Ako je unique...
            if(anyFound == false){
                // Izadi iz while petlje
                break;
            }
            
            // Ako nije unique, trazi dalje sa sljedecim
            id++;
        }
        
        return id;
    }

    @Override
    public ArrayList<Mail> ReadMails2() {
        ArrayList<Mail> newMailsFromTxt = new ArrayList<Mail>();
        File file = new File(PATH_RECEIVED_MAILS2);
        Scanner reader = null;
        try {
            reader = new Scanner(file);
            //neka reader koristi delimiter
            reader.useDelimiter(MAIL_SEPARATOR);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MailRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (reader.hasNext()) {
            Mail mail = new Mail();
            String[] lineData = reader.next().split(SEPARATOR);

            mail.setIDMail(Integer.parseInt(lineData[0]));
            mail.setRecieverMail(lineData[1]);
            mail.setSubject(lineData[2]);
            mail.setDescription(lineData[3]);
            mail.setTimeOfSending(lineData[4]);
            //reader.nextLine();

            newMailsFromTxt.add(mail);
        };

        return newMailsFromTxt;
    }

    @Override
    public boolean InsertNewMail2(String toEmailAdress, String subject, String mailContent) {
        Date date = new Date();
        //System.out.println(dateFormat.format(date));
        try {
            FileWriter fstream = new FileWriter(PATH_RECEIVED_MAILS2, true);
            try (BufferedWriter buffWriter = new BufferedWriter(fstream)) {
                Integer newID = GetNewMailID(2);
                String data = String.join(SEPARATOR, newID.toString(), "perica.peric@gmail.com", subject, mailContent, dateFormat.format(date));
                buffWriter.write(data);
                buffWriter.write(MAIL_SEPARATOR);
                //buffWriter.newLine();
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(MailRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


}
