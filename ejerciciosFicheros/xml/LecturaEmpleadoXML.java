package xml;

import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class LecturaEmpleadoXML {

    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("Empleados.xml"));
            document.getDocumentElement().normalize();
            System.out.println("Elemento raíz: " +
                    document.getDocumentElement().getNodeName());

            NodeList empleados = document.getElementsByTagName("Empleado");
            System.out.println("Nodos empleado a recorrer: " + empleados.getLength());

            for (int i = 0; i < empleados.getLength(); i++) {
                Node emple = empleados.item(i);
                if (emple.getNodeType() == Node.ELEMENT_NODE) {
                    // obtener los elementos
                    Element elemento = (Element) emple;
                    System.out.println("ID: " + getNodo("id", elemento));
                    System.out.println("Apellido: " + getNodo("apellido", elemento));
                    System.out.println("Departamento: " + getNodo("departamento", elemento));
                    System.out.println("Salario: " + getNodo("salario", elemento));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getNodo(String etiqueta, Element elem) {
        NodeList nodos = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node nodo = (Node) nodos.item(0);
        return nodo.getNodeValue();
    }

}