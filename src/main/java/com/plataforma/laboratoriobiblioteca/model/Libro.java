
package com.plataforma.laboratoriobiblioteca.model;

public class Libro {
    public String isbn;
    public String titulo;
    public boolean disponible;

    public Libro(String isbn, String titulo){
        this.isbn = isbn;
        this.titulo = titulo;
        this.disponible = true;
    }
}