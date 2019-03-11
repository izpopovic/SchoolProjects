package Utils;



import MailRepository.JndiUtil;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ivanz
 */
public class RmiServer {
    public static void main(String[] args) {
        try{
            int port = Integer.parseInt(JndiUtil.getProperty("rmiPort"));
            Registry registry = LocateRegistry.createRegistry(port);
            IChat chat = new Chat("Server");
            IChat stub = (IChat)UnicastRemoteObject.exportObject(chat, port);
            registry.bind(JndiUtil.getProperty("rmiKey"), stub);
            System.out.println("Server je pokrenut");
            while(true);
        }catch(Exception ex){
            System.out.println("Nije moguce pokrenut server");
            ex.printStackTrace();
        }
    }
}
