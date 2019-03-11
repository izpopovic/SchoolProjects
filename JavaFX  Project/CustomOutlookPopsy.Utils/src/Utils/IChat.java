/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ivanz
 */
public interface IChat extends Remote {
    void sendMessage(String message) throws RemoteException;
    String getMessages() throws RemoteException;
}
