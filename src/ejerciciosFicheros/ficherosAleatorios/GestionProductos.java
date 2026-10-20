package ficherosAleatorios;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class GestionProductos {

    private static final String fichero = "productos.dat";
    private static final int longNombre = 12;
    private static final int tamRegistro = 40; // 4 + 24 + 4 + 8
    private static final int offsetExistencias = 4 + 2 * longNombre; // 28
    private static final int offsetPrecio = offsetExistencias + 4; // 32

    private static final String[] nombres = {"Teclado", "Ratón", "Monitor 24 pulgadas", "Altavoces", "Impresora"};
    private static final int[] existencias = { 25, 40, 12, 30, 8 };
    private static final double[] precios = { 19.99, 12.5, 149.9, 35.0, 89.95 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "rw")) {

            // crear el fichero 
            raf.setLength(0);
            for (int i = 0; i <nombres.length; i++) {
                raf.writeInt(i + 1);
                raf.writeChars(ajustarNombre(nombres[i]));
                raf.writeInt(existencias[i]);
                raf.writeDouble(precios[i]);
            }

            // mostrar todos los productos
            System.out.println("Productos almacenados");
            mostrarTodos(raf);

            long numRegistros = raf.length() / tamRegistro;

            // mostrar un producto concreto
            int id = pedirEntero(sc, "Identificador del producto a consultar: ", 1, (int) numRegistros);
            raf.seek(posicion(id));
            mostrarRegistro(raf);

            // actualizar solo las existencias
            id = pedirEntero(sc, "Identificador del producto a modificar (existencias): ", 1, (int) numRegistros);
            int nuevasExistencias = pedirEntero(sc, "Nuevas existencias: ", 0, Integer.MAX_VALUE);
            raf.seek(posicion(id) + offsetExistencias);
            raf.writeInt(nuevasExistencias);

            // actualizar solo el precio
            id = pedirEntero(sc, "Identificador del producto a modificar (precio): ", 1, (int) numRegistros);
            double nuevoPrecio = pedirDouble(sc, "Nuevo precio: ", 0);
            raf.seek(posicion(id) + offsetPrecio);
            raf.writeDouble(nuevoPrecio);

            // mostrar de nuevo todos los productos
            System.out.println("Productos tras las modificaciones");
            mostrarTodos(raf);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // posición = (id - 1) * tamaño del registro
    private static long posicion(int id) {
        return (long) (id - 1) * tamRegistro;
    }

    // Recorta a 12 caracteres o rellena con espacios hasta 12
    private static String ajustarNombre(String nombre) {
        if (nombre.length() > longNombre) {
            return nombre.substring(0, longNombre);
        }
        StringBuilder sb = new StringBuilder(nombre);
        while (sb.length() < longNombre) {
            sb.append(' ');
        }
        return sb.toString();
    }

    // Lee un registro desde la posición actual del puntero
    private static void mostrarRegistro(RandomAccessFile raf) throws IOException {
        int id = raf.readInt();
        char[] chars = new char[longNombre];
        for (int i = 0; i < longNombre; i++) {
            chars[i] = raf.readChar();
        }
        String nombre = new String(chars).trim();
        int existenciasLeidas = raf.readInt();
        double precio = raf.readDouble();
        System.out.printf("ID: %d | Nombre: %-12s | Existencias: %d | Precio: %.2f%n",
                id, nombre, existenciasLeidas, precio);
    }

    private static void mostrarTodos(RandomAccessFile raf) throws IOException {
        raf.seek(0);
        while (raf.getFilePointer() < raf.length()) {
            mostrarRegistro(raf);
        }
    }

    private static int pedirEntero(Scanner sc, String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(sc.nextLine().trim());
                if (valor >= min && valor <= max) {
                    return valor;
                }
                if (max == Integer.MAX_VALUE) {
                    System.out.println("El valor no puede ser negativo.");
                } else {
                    System.out.println("El valor debe estar entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero válido.");
            }
        }
    }

    private static double pedirDouble(Scanner sc, String mensaje, double min) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
                if (valor >= min) {
                    return valor;
                }
                System.out.println("El valor no puede ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
            }
        }
    }
}