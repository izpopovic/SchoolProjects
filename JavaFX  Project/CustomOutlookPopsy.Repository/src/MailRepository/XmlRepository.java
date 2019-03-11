/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MailRepository;

import Mail.Mail;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ivanz
 */
public class XmlRepository implements IRepository {

     DateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY");
     
    private final String PATH_RECEIVED_MAILS = "C:\\\\Users\\\\ivanz\\\\Desktop\\\\Algebra\\\\5. semestar\\\\Java 2\\\\CustomOutlookPopsy.Repository\\\\src\\\\ReceivedMails.xml";
    private final String PATH_RECEIVED_MAILS2 = "C:\\\\Users\\\\ivanz\\\\Desktop\\\\Algebra\\\\5. semestar\\\\Java 2\\\\CustomOutlookPopsy.Repository\\\\src\\\\ReceivedMails2.xml";
    private ArrayList<Mail> mailsFromXml;

 
    
    @Override
    public ArrayList<Mail> ReadMails() {
        
        mailsFromXml = new ArrayList<Mail>();
        
        
        try {
             DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
             
             Document document = documentBuilder.parse(PATH_RECEIVED_MAILS);
             
             document.getDocumentElement().normalize();
             
             Element root = document.getDocumentElement();
             
             NodeList nList = document.getElementsByTagName("mail");
             
             for (int i = 0; i < nList.getLength(); i++) {
                 
                 Node node = nList.item(i);
                 
                 if (node.getNodeType() == Node.ELEMENT_NODE) {
                     
                     Element mailxml = (Element) node;
                     Mail mail = new Mail();
                     
                     mail.setIDMail(Integer.parseInt(mailxml.getElementsByTagName("idMail").item(0).getTextContent()));
                     mail.setSenderMail(mailxml.getElementsByTagName("sender").item(0).getTextContent());
                     mail.setRecieverMail(mailxml.getElementsByTagName("reciever").item(0).getTextContent());
                     mail.setSubject(mailxml.getElementsByTagName("subject").item(0).getTextContent());
                     mail.setDescription(mailxml.getElementsByTagName("content").item(0).getTextContent());
                     mail.setTimeOfSending(mailxml.getElementsByTagName("date").item(0).getTextContent());
                     
                     //System.out.println(mail);
                     
                     mailsFromXml.add(mail);
                     
                 }
                 
             }
             
             
         
         } catch (ParserConfigurationException | SAXException | IOException ex) {
             Logger.getLogger(XmlRepository.class.getName()).log(Level.SEVERE, null, ex);
         }
         
            return mailsFromXml;
    }

    @Override
    public ArrayList<Mail> ReadMails2() {
         mailsFromXml = new ArrayList<Mail>();
        
        
        try {
             DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
             
             Document document = documentBuilder.parse(PATH_RECEIVED_MAILS2);
             
             document.getDocumentElement().normalize();
             
             Element root = document.getDocumentElement();
             
             NodeList nList = document.getElementsByTagName("mail");
             
             for (int i = 0; i < nList.getLength(); i++) {
                 
                 Node node = nList.item(i);
                 
                 if (node.getNodeType() == Node.ELEMENT_NODE) {
                     
                     Element mailxml = (Element) node;
                     Mail mail = new Mail();
                     
                     mail.setIDMail(Integer.parseInt(mailxml.getElementsByTagName("idMail").item(0).getTextContent()));
                     mail.setSenderMail(mailxml.getElementsByTagName("sender").item(0).getTextContent());
                     mail.setRecieverMail(mailxml.getElementsByTagName("reciever").item(0).getTextContent());
                     mail.setSubject(mailxml.getElementsByTagName("subject").item(0).getTextContent());
                     mail.setDescription(mailxml.getElementsByTagName("content").item(0).getTextContent());
                     mail.setTimeOfSending(mailxml.getElementsByTagName("date").item(0).getTextContent());
                     
                     //System.out.println(mail);
                     
                     mailsFromXml.add(mail);
                     
                 }
                 
             }
             
             
         
         } catch (ParserConfigurationException | SAXException | IOException ex) {
             Logger.getLogger(XmlRepository.class.getName()).log(Level.SEVERE, null, ex);
         }
         
            return mailsFromXml;
    }

    @Override
    public boolean InsertNewMail(String toEmailAdress, String subject, String mailContent) {
       
        boolean success = false;
        
        Date date = new Date();
        
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.parse(PATH_RECEIVED_MAILS);
            Element root = doc.getDocumentElement();
           //doc.appendChild(root);
            
            Element mail = doc.createElement("mail");
            //root.appendChild(mail);

            Element iDMail = doc.createElement("idMail");
            iDMail.appendChild(doc.createTextNode(GetNewMailID(1).toString()));
            mail.appendChild(iDMail);

            Element senderMail = doc.createElement("sender");
            senderMail.appendChild(doc.createTextNode(toEmailAdress));
            mail.appendChild(senderMail);

            Element recieverMail = doc.createElement("reciever");
            recieverMail.appendChild(doc.createTextNode("ivica.ivic@gmail.com"));
            mail.appendChild(recieverMail);

            Element subject_node = doc.createElement("subject");
            subject_node.appendChild(doc.createTextNode(subject));
            mail.appendChild(subject_node);

            Element mailContent_node = doc.createElement("content");
            mailContent_node.appendChild(doc.createTextNode(mailContent));
            mail.appendChild(mailContent_node);
            
            Element date_node = doc.createElement("date");
            date_node.appendChild(doc.createTextNode(dateFormat.format(date)));
            mail.appendChild(date_node);
            
            root.appendChild(mail);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            DOMSource source = new DOMSource(doc);
            //ovo pobrise tu fajlu brebrejka ju na novu
            StreamResult result = new StreamResult(PATH_RECEIVED_MAILS);
            
            transformer.transform(source, result);
            success = true;
            
            
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (TransformerConfigurationException ex) {
             ex.printStackTrace();
         } catch (TransformerException ex) {
             ex.printStackTrace();
         } catch (SAXException ex) {
             Logger.getLogger(XmlRepository.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(XmlRepository.class.getName()).log(Level.SEVERE, null, ex);
         }
       
        return success;
    }


    @Override
    public boolean InsertNewMail2(String toEmailAdress, String subject, String mailContent) {
        boolean success = false;
        
        Date date = new Date();
        
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.parse(PATH_RECEIVED_MAILS2);
            Element root = doc.getDocumentElement();
           //doc.appendChild(root);
            
            Element mail = doc.createElement("mail");
            //root.appendChild(mail);

            Element iDMail = doc.createElement("idMail");
            iDMail.appendChild(doc.createTextNode(GetNewMailID(2).toString()));
            mail.appendChild(iDMail);

            Element senderMail = doc.createElement("sender");
            senderMail.appendChild(doc.createTextNode(toEmailAdress));
            mail.appendChild(senderMail);

            Element recieverMail = doc.createElement("reciever");
            recieverMail.appendChild(doc.createTextNode("perica.peric@gmail.com"));
            mail.appendChild(recieverMail);

            Element subject_node = doc.createElement("subject");
            subject_node.appendChild(doc.createTextNode(subject));
            mail.appendChild(subject_node);

            Element mailContent_node = doc.createElement("content");
            mailContent_node.appendChild(doc.createTextNode(mailContent));
            mail.appendChild(mailContent_node);
            
            Element date_node = doc.createElement("date");
            date_node.appendChild(doc.createTextNode(dateFormat.format(date)));
            mail.appendChild(date_node);
            
            root.appendChild(mail);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            DOMSource source = new DOMSource(doc);
            //ovo pobrise tu fajlu brebrejka ju na novu
            StreamResult result = new StreamResult(PATH_RECEIVED_MAILS2);
            
            transformer.transform(source, result);
            success = true;
            
            
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (TransformerConfigurationException ex) {
             ex.printStackTrace();
         } catch (TransformerException ex) {
             ex.printStackTrace();
         } catch (SAXException ex) {
             Logger.getLogger(XmlRepository.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(XmlRepository.class.getName()).log(Level.SEVERE, null, ex);
         }
       
        return success;
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
    
}
