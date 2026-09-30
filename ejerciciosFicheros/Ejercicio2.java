package ejerciciosFicheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Ejercicio2 {
    public static void main(String[] args) {
       
        File archivo = new File("ejerciciosFicheros/archivo.txt");
        int totalPalabras = 0;

        if (archivo.exists()) {
            try {
                FileReader fr = new FileReader(archivo);
                BufferedReader bf = new BufferedReader(fr);
                
                String linea;
                while ((linea = bf.readLine()) != null) {
                    
                    StringTokenizer tokens = new StringTokenizer(linea);
                    totalPalabras += tokens.countTokens();
                }
                
                System.out.println("El número total de palabras en el archivo es: " + totalPalabras);
                
            } catch (IOException e) {
                System.out.println("Error al leer el archivo");
            }
        } else {
            System.out.println("El archivo no existe");
        }
    }
}
