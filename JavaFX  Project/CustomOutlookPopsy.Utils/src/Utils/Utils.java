/*

package Utils;

import Mail.Mail;
import customoutlookpopsy.CustomOutlookPopsy;
import customoutlookpopsy.FirstMailClientController;
import customoutlookpopsy.SendMailFormController;
import customoutlookpopsy.SendMailFormController_1;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    
    public static void main(String[] args) {
        //YEA BOIIIii
 
        
        
        Mail mail = new Mail();
        CustomOutlookPopsy customOutlookPopsy = new CustomOutlookPopsy();
        FirstMailClientController fxmlDocController = new FirstMailClientController();
        SendMailFormController sendMailForm = new SendMailFormController();
        SendMailFormController_1 sendMailForm1 = new SendMailFormController_1();
        
   
        
        

        
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append("<!DOCTYPE html>\n");
        strBuffer.append("<html>\n");
        strBuffer.append("<head>\n");
        strBuffer.append("<title>Moja prva dokumentacija</title>\n");
        strBuffer.append("</head>\n");
        strBuffer.append("<body>\n");
        strBuffer.append("<h1>Popis klasa</h1>\n");
        
        try {
            FileWriter writer = new FileWriter(new File("C:\\Users\\ivanz\\Desktop\\dokumentacija.html"), false);
            //1
            Class klasa = mail.getClass();
            //1
            Class klasaOutlook = customOutlookPopsy.getClass();
            //3
            Class klasaFxmlDocController = fxmlDocController.getClass();
            //4
            Class klasaSendMailForm = sendMailForm.getClass();
            //5
            Class klasaSendMailForm1 = sendMailForm1.getClass();
            strBuffer.append("<h2>" + klasa.getName() +  "</h2>\n");
            strBuffer.append("<h2>" + klasaOutlook.getName() +  "</h2>\n");
            strBuffer.append("<h2>" + klasaFxmlDocController.getName() +  "</h2>\n");
            strBuffer.append("<h2>" + klasaSendMailForm.getName() +  "</h2>\n");
            strBuffer.append("<h2>" + klasaOutlook.getName() +  "</h2>\n");

            strBuffer.append("<h3>Popis konstruktora</h3>\n");
            strBuffer.append("<table border='1'>\n");
            strBuffer.append("<th>Naziv konstruktora</th>"
                    + "<th>Parametri konstruktora</th>");
            
            for(Constructor c : klasa.getConstructors()) {
                strBuffer.append("<tr><td>\n");
                strBuffer.append(c.getName());
                strBuffer.append("</td><td>\n");
                if(c.getParameterCount() > 0) {
                    for(Parameter parameter : c.getParameters()) {
                        strBuffer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");
                                
                    }
                }
                strBuffer.append("</td></tr>\n");
            }
            
            for(Constructor c : klasaOutlook.getConstructors()) {
                strBuffer.append("<tr><td>\n");
                strBuffer.append(c.getName());
                strBuffer.append("</td><td>\n");
                if(c.getParameterCount() > 0) {
                    for(Parameter parameter : c.getParameters()) {
                        strBuffer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");
                                
                    }
                }
                strBuffer.append("</td></tr>\n");
            }
            
            for(Constructor c : klasaFxmlDocController.getConstructors()) {
                strBuffer.append("<tr><td>\n");
                strBuffer.append(c.getName());
                strBuffer.append("</td><td>\n");
                if(c.getParameterCount() > 0) {
                    for(Parameter parameter : c.getParameters()) {
                        strBuffer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");
                                
                    }
                }
                strBuffer.append("</td></tr>\n");
            }
            for(Constructor c : klasa.getConstructors()) {
                strBuffer.append("<tr><td>\n");
                strBuffer.append(c.getName());
                strBuffer.append("</td><td>\n");
                if(c.getParameterCount() > 0) {
                    for(Parameter parameter : c.getParameters()) {
                        strBuffer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");
                                
                    }
                }
                strBuffer.append("</td></tr>\n");
            }
            
            
            strBuffer.append("</table>\n");
            
            strBuffer.append("<h3>Popis metoda</h3>\n");
            strBuffer.append("<table border='1'>\n");
            strBuffer.append("<th>Naziv metode</th>"
                    + "<th>Ulazni parametri metode</th>"
                    + "<th>Izlazni parametar metode</th>");
            
            for(Method metoda : klasa.getMethods()) {
                strBuffer.append("<tr><td>\n");
                strBuffer.append(metoda.getName());
                strBuffer.append("</td><td>\n");
                if(metoda.getParameterCount() > 0) {
                    for(Parameter parameter : metoda.getParameters()) {
                        strBuffer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");
                                
                    }
                }
                strBuffer.append("</td><td>\n");
                strBuffer.append(metoda.getReturnType().getName() + "</td>");
            }
            //ZA OUTLOOK
            for(Method metoda : klasaOutlook.getMethods()) {
                strBuffer.append("<tr><td>\n");
                strBuffer.append(metoda.getName());
                strBuffer.append("</td><td>\n");
                if(metoda.getParameterCount() > 0) {
                    for(Parameter parameter : metoda.getParameters()) {
                        strBuffer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");
                                
                    }
                }
                strBuffer.append("</td><td>\n");
                strBuffer.append(metoda.getReturnType().getName() + "</td>");
            }
            
            //za doccontroller
            for(Method metoda : klasaFxmlDocController.getMethods()) {
                strBuffer.append("<tr><td>\n");
                strBuffer.append(metoda.getName());
                strBuffer.append("</td><td>\n");
                if(metoda.getParameterCount() > 0) {
                    for(Parameter parameter : metoda.getParameters()) {
                        strBuffer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");
                                
                    }
                }
                strBuffer.append("</td><td>\n");
                strBuffer.append(metoda.getReturnType().getName() + "</td>");
            }
            
            for(Method metoda : klasaSendMailForm.getMethods()) {
                strBuffer.append("<tr><td>\n");
                strBuffer.append(metoda.getName());
                strBuffer.append("</td><td>\n");
                if(metoda.getParameterCount() > 0) {
                    for(Parameter parameter : metoda.getParameters()) {
                        strBuffer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");
                                
                    }
                }
                
           
                strBuffer.append("</td><td>\n");
                strBuffer.append(metoda.getReturnType().getName() + "</td>");
            }
            
            
            
            
            strBuffer.append("</table>\n");
            
            for(Field polje : klasa.getDeclaredFields()) {
                String nazivVarijable = polje.getName();
                System.out.println("Naziv varijable je:" + nazivVarijable);
                //modifier inace bez ovog vraca 2 22 22 sad ce vratit publi cili private
                System.out.println("Access modifier je:" + Modifier.toString(polje.getModifiers()));
            }
            
            for(Field polje : klasaOutlook.getDeclaredFields()) {
                String nazivVarijable = polje.getName();
                System.out.println("Naziv varijable je:" + nazivVarijable);
                //modifier inace bez ovog vraca 2 22 22 sad ce vratit publi cili private
                System.out.println("Access modifier je:" + Modifier.toString(polje.getModifiers()));
            }
            
            for(Field polje : klasaFxmlDocController.getDeclaredFields()) {
                String nazivVarijable = polje.getName();
                System.out.println("Naziv varijable je:" + nazivVarijable);
                //modifier inace bez ovog vraca 2 22 22 sad ce vratit publi cili private
                System.out.println("Access modifier je:" + Modifier.toString(polje.getModifiers()));
            }
            strBuffer.append("</body>\n");
            strBuffer.append("</html>\n");
            
            writer.append(strBuffer.toString());
            
            writer.flush();
            writer.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


     */
        
    
    

