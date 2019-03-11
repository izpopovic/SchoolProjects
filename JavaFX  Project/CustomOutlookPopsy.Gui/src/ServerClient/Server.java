/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ivanz
 */
public class Server {
    private static final Logger LOG = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(12345);
        
        while(true) {
            LOG.info("Server waiting for client...");
            
            Socket clientSocket = s.accept();
            LOG.log(Level.INFO, "Server accepted client: {0}", clientSocket);
            
            new ClientProcessor(clientSocket).start();
        }
    }
}

class ClientProcessor extends Thread {
    private static final Logger LOG = Logger.getLogger(ClientProcessor.class.getName());
    
    private final Socket clientSocket;
    private final BufferedReader reader;
    private final PrintWriter printWriter;
    
    ClientProcessor(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        printWriter = new PrintWriter(clientSocket.getOutputStream());
        LOG.log(Level.INFO, "Created client processor for: {0}", clientSocket);
    }
    
    @Override
    public void run() {
        LOG.log(Level.INFO, "String to process client: {0}", clientSocket);

        String line;
        
        try {
            while((line = reader.readLine()) != null) {
                String msg = String.format("Received from client: %s line: %s\n", clientSocket, line);
                printWriter.write(msg);
                printWriter.flush();
            }
        } catch (IOException ex) {
            LOG.warning(ex.getMessage());
        } finally {
            close(clientSocket);
        }
        
        LOG.log(Level.INFO, "Closing client processor for: {0}", clientSocket);
    }

    private void close(Socket clientSocket) {
        try {
            clientSocket.close();
        } catch (IOException ex1) {
            LOG.log(Level.WARNING, ex1.getMessage(), ex1);
        }
    }
}