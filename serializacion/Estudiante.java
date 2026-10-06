package serializacion;

import java.io.*;

public class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int edad;
    private double notaMedia;
    private transient String contraseña;

    public Estudiante(String nombre, int edad, double notaMedia, String contraseña) {
        this.nombre = nombre;
        this.edad = edad;
        this.notaMedia = notaMedia;
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Estudiante{nombre='" + nombre + "', edad=" + edad
                + ", notaMedia=" + notaMedia + ", contraseña='" + contraseña + "'}";
    }

    public static void main(String[] args) {
        File archivo = new File("estudiante.ser");
        Estudiante original = new Estudiante("Ana", 20, 8.5, "abc123");
        System.out.println("Antes: " + original);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(original);
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Estudiante recuperado = (Estudiante) ois.readObject();
            System.out.println("Después: " + recuperado);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer: " + e.getMessage());
        }
    }
}