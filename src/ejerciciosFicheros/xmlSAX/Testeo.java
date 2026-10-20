package xmlSAX;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Testeo {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            GestionAlumnos handler = new GestionAlumnos();
            saxParser.parse(new File("alumnos.xml"), handler);

        } catch (Exception e) {
            System.err.println("Error al procesar el archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }

}