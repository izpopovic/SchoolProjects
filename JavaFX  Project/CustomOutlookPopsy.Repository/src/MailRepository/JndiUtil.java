/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MailRepository;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author ivanz
 */
public class JndiUtil {
    private static final String file = "C:\\Users\\ivanz\\Desktop\\Algebra\\5. semestar\\Java 2\\settings.txt";
    private static Properties properties;
    
    public static String getProperty(String key){
        if(properties == null){
            properties = new Properties();
            try(InputStream fileInput = new FileInputStream(file)){
                properties.load(fileInput);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        return properties.getProperty(key);
    }
}
