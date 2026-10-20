package xmlSAX;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class TesteoLibreria {
    public static void main(String[] args) {
        String rutaArchivo = "libreria.xml";

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            Libreria handler = new Libreria();
            saxParser.getXMLReader().setErrorHandler(handler);

            System.out.println("Procesando archivo: " + rutaArchivo);
            saxParser.parse(new File(rutaArchivo), handler);

        } catch (Exception e) {
            System.err.println("\nProcesamiento interrumpido debido a un error de parseo.");
        }
    }
}