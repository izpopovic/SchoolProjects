import com.console.Console;
import com.gui.GUI;
import com.lib.xml.XmlReader;
import com.lib.xml.XmlTag;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        String appType = getXmlSetting("AppType");

        if(appType.equals("Console"))
            Console.start();
        else
            GUI.start();
    }

    private static String getXmlSetting(String tagName) throws Exception {
        XmlReader reader = new XmlReader("C:\\Users\\Ivan\\Desktop\\Algebra\\Projekti Algebra\\JAVA I\\JavaProjectPopsi\\Postaavke.xml");

        Map<String, List<String>> data = reader.getXMLData(new XmlTag("Settings"), new XmlTag(tagName));

        List<String> values =  data.get(tagName); //data[tagName]
        return values.get(0); //values[0]
    }
}
