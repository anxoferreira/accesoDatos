package ejerciciosFicheros;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) {

        String nombreArchivo = "parejas_numeros.txt";

        try (Scanner scanner = new Scanner(System.in);
                BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {

            System.out.println("Introduce los números enteros separados por espacios.");
            System.out.println("Escribe intro en una línea para terminar:");

            List<Integer> numeros = new ArrayList<>();

            while (true) {
                System.out.print("> ");
                String linea = scanner.nextLine();

                if (linea.equalsIgnoreCase("intro")) {
                    break;
                }
                String[] partes = linea.split("\\s+");
                for (String parte : partes) {
                    try {
                        numeros.add(Integer.parseInt(parte));
                    } catch (NumberFormatException e) {
                        System.out.println("Atención: '" + parte + "' no es un entero válido y será omitido.");
                    }
                }
            }

            if (!numeros.isEmpty()) {
                
                for (int i = 0; i < numeros.size(); i++) {
                    writer.write(String.valueOf(numeros.get(i)));
                    if (i < numeros.size() - 1) {
                        writer.write(" ");
                    }
                }
                writer.newLine();

                for (int i = 0; i < numeros.size(); i += 2) {
                    writer.newLine();
                    
                    if (i + 1 < numeros.size()) {
                        writer.write(numeros.get(i) + " " + numeros.get(i + 1));
                        writer.newLine();
                    } else {
                        writer.write(String.valueOf(numeros.get(i)));
                        writer.newLine();
                    }
                }
                writer.newLine();
            }

            System.out.println("Archivo guardado correctamente en " + nombreArchivo);

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}