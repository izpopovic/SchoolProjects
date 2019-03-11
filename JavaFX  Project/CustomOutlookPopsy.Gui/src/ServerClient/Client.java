/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author ivanz
 */
public class Client {
    
      public static void main(String[] args) throws IOException {
        try(Socket s = new Socket("localhost", 12345);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
            System.out.println("Connected to server: " + s);
            while(true) {
                System.out.print("> ");
                String line = reader.readLine();
                if (line.equals("")) {
                    System.out.println("Closing client");
                    break;
                }
                System.out.println("Sending to server: " + line);
                s.getOutputStream().write((line + "\n").getBytes());
                s.getOutputStream().flush();
                
                String responseFromServer = socketReader.readLine();
                System.out.println(responseFromServer);
            }
        }
    }
}
