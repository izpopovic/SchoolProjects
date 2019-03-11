/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivanz
 */
public class Chat implements IChat {
    private List<String> messages = new ArrayList<>();
    private String name;
    
     public Chat(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public void sendMessage(String message) throws RemoteException {
        messages.add(message);
    }

    @Override
    public String getMessages() throws RemoteException {
        return String.join(System.lineSeparator(), messages);
    }
    
}
