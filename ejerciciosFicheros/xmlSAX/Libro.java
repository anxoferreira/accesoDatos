package xmlSAX;

import java.util.ArrayList;

public class Libro {
    private String isbn;
    private String idioma;
    private String titulo;
    private ArrayList<String> autores = new ArrayList<>();
    private String editorial;
    private int anio;
    private double precio;
    private String moneda;

    // Getters y Setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<String> getAutores() {
        return autores;
    }

    public void addAutor(String autor) {
        this.autores.add(autor);
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Override
    public String toString() {
        return "Libro [isbn=" + isbn + ", idioma=" + idioma + ", titulo=" + titulo + ", autores=" + autores
                + ", editorial=" + editorial + ", anio=" + anio + ", precio=" + precio + ", moneda=" + moneda + "]";
    }
}
