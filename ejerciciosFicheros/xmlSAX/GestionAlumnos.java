package xmlSAX;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionAlumnos extends DefaultHandler {
    ArrayList<Alumno> alumnado = new ArrayList<>();
    Alumno alumnoActual;
    StringBuilder texto = new StringBuilder();

    public ArrayList<Alumno> getListaDeAlumnos() {
        return alumnado;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Iniciando lectura de datos\n");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        texto.setLength(0);

        if (qName.equalsIgnoreCase("alumno")) {
            alumnoActual = new Alumno();
            String id = attributes.getValue("id");
            alumnoActual.setId(id);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        texto.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String valor = texto.toString().trim();
        if (alumnoActual != null) {
            switch (qName.toLowerCase()) {
                case "nombre":
                    alumnoActual.setNombre(valor);
                    break;
                case "apellidos":
                    alumnoActual.setApellidos(valor);
                    break;
                case "ciclo":
                    alumnoActual.setCiclo(valor);
                    break;
                case "nota":
                    alumnoActual.setNota(Double.parseDouble(valor));
                    break;
                case "alumno":
                    alumnado.add(alumnoActual);
                    alumnoActual = null;
                    break;
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Terminando lectura de datos\n");

        if (alumnado.isEmpty()) {
            System.out.println("No se encontraron alumnos en el archivo");
            return;
        }

        System.out.println("--- LISTADO DE ALUMNOS ---");
        for (Alumno a : alumnado) {
            System.out.println(a);
        }
        System.out.println();

        int totalAlumnos = alumnado.size();
        double sumaNotas = 0;
        int aprobados = 0;
        int suspensos = 0;

        Alumno mejorAlumno = alumnado.get(0);
        Alumno peorAlumno = alumnado.get(0);

        for (Alumno a : alumnado) {
            double nota = a.getNota();
            sumaNotas += nota;

            if (nota > mejorAlumno.getNota()) {
                mejorAlumno = a;
            }

            if (nota < peorAlumno.getNota()) {
                peorAlumno = a;
            }

            if (nota >= 5.0) {
                aprobados++;
            } else {
                suspensos++;
            }
        }

        double notaMedia = sumaNotas / totalAlumnos;

        System.out.println("--- RESUMEN Y ESTADÍSTICAS ---");
        System.out.println("Número total de alumnos: " + totalAlumnos);
        System.out.printf("Nota media general: ", notaMedia);
        System.out.println("Alumno con la nota más alta: " + mejorAlumno);
        System.out.println("Alumno con la nota más baja: " + peorAlumno);
        System.out.println("Alumnos aprobados (nota >= 5): " + aprobados);
        System.out.println("Alumnos suspensos: " + suspensos);
    }
}