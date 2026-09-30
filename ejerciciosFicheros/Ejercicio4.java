package ejerciciosFicheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Ejercicio4 {

    public static void main(String[] args) {
        File archivo = new File("ejerciciosFicheros/archivo.txt");
        int totalHachesIntercaladas = 0;
        int palabrasConHacheIntercalada = 0;

        if (archivo.exists()) {
            try {

                FileReader fr = new FileReader(archivo);
                BufferedReader bf = new BufferedReader(fr);

                String linea;

                while ((linea = bf.readLine()) != null) {

                    StringTokenizer tokens = new StringTokenizer(linea);

                    while (tokens.hasMoreTokens()) {
                        String palabra = tokens.nextToken();
                        boolean tieneHacheIntercalada = false;
                        int hachesEnPalabra = 0;

                        for (int i = 0; i < palabra.length(); i++) {
                            char c = Character.toLowerCase(palabra.charAt(i));

                            if (c == 'h') {

                                boolean esComienzo = (i == 0);
                                boolean esCh = (i > 0 && Character.toLowerCase(palabra.charAt(i - 1)) == 'c');

                                if (!esComienzo && !esCh) {
                                    hachesEnPalabra++;
                                    tieneHacheIntercalada = true;
                                }
                            }
                        }

                        totalHachesIntercaladas += hachesEnPalabra;

                        if (tieneHacheIntercalada) {
                            palabrasConHacheIntercalada++;
                        }
                    }
                }

                System.out.println("Número total de haches intercaladas: " + totalHachesIntercaladas);
                System.out.println("Número de palabras con hache intercalada: " + palabrasConHacheIntercalada);

            } catch (IOException e) {
                System.out.println("Error de lectura del fichero");
            }
        } else {
            System.out.println("El fichero no existe");
        }
    }
}
