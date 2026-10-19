package xmlSAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Libreria extends DefaultHandler {
    private final ArrayList<Libro> listaLibros = new ArrayList<>();
    private Libro libroActual;
    private final StringBuilder bufferTexto = new StringBuilder();

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Iniciando lectura de libros\n");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        bufferTexto.setLength(0);

        if (qName.equalsIgnoreCase("libro")) {
            libroActual = new Libro();
            libroActual.setIsbn(attributes.getValue("isbn"));
            libroActual.setIdioma(attributes.getValue("idioma"));
        } else if (qName.equalsIgnoreCase("precio") && libroActual != null) {
            libroActual.setMoneda(attributes.getValue("moneda"));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        bufferTexto.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String valor = bufferTexto.toString().trim();

        if (libroActual != null) {
            switch (qName.toLowerCase()) {
                case "titulo":
                    libroActual.setTitulo(valor);
                    break;
                case "autor":
                    libroActual.addAutor(valor);
                    break;
                case "editorial":
                    libroActual.setEditorial(valor);
                    break;
                case "anio":
                    try {
                        libroActual.setAnio(Integer.parseInt(valor));
                    } catch (NumberFormatException e) {
                        libroActual.setAnio(0);
                    }
                    break;
                case "precio":
                    try {
                        libroActual.setPrecio(Double.parseDouble(valor));
                    } catch (NumberFormatException e) {
                        libroActual.setPrecio(0.0);
                    }
                    break;
                case "libro":
                    listaLibros.add(libroActual);
                    libroActual = null;
                    break;
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Lectura de documento finalizada\n");
        imprimirResultados();
    }

    public void imprimirResultados() {
        if (listaLibros.isEmpty()) {
            System.out.println("No se han podido procesar libros.");
            return;
        }

        System.out.println("---Listado de libros: ---");
        for (Libro l : listaLibros) {
            System.out.println(l);
        }

        System.out.println("\n---Libros posteriores a 2010: ---");
        for (Libro l : listaLibros) {
            if (l.getAnio() >= 2010) {
                System.out.println(l);
            }
        }

        Map<String, Integer> conteoIdiomas = new HashMap<>();
        double precioTotalEur = 0;
        int cantidadEur = 0;
        Libro libroMasAutores = listaLibros.get(0);

        for (Libro libro : listaLibros) {
            String idioma = libro.getIdioma() != null ? libro.getIdioma() : "desconocido";
            conteoIdiomas.put(idioma, conteoIdiomas.getOrDefault(idioma, 0) + 1);

            if ("EUR".equalsIgnoreCase(libro.getMoneda())) {
                precioTotalEur += libro.getPrecio();
                cantidadEur++;
            }

            if (libro.getAutores().size() > libroMasAutores.getAutores().size()) {
                libroMasAutores = libro;
            }
        }

        System.out.println("\n---Conteo de libros por idioma: ---");
        conteoIdiomas.forEach((idioma, total) -> System.out.println("Idioma [" + idioma + "]: " + total + " libro(s)"));

        System.out.println("\n---Estadisticas de precios: ---");
        System.out.printf("Precio Total (EUR): ", precioTotalEur);
        if (cantidadEur > 0) {
            System.out.printf("Precio Medio (EUR): ", (precioTotalEur / cantidadEur));
        } else {
            System.out.println("No hay libros en euros (EUR).");
        }

        System.out.println("\n---Libros con mas autores: ---");
        System.out.println(libroMasAutores + " (Total autores: " + libroMasAutores.getAutores().size() + ")");
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.err.printf("[ADVERTENCIA] Línea %d, Columna %d: %s%n",
                e.getLineNumber(), e.getColumnNumber(), e.getMessage());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.err.printf("[ERROR DE VALIDACIÓN] Línea %d, Columna %d: %s%n",
                e.getLineNumber(), e.getColumnNumber(), e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.err.printf("[ERROR FATAL - XML MAL FORMADO] Línea %d, Columna %d: %s%n",
                e.getLineNumber(), e.getColumnNumber(), e.getMessage());
        throw e;
    }
}