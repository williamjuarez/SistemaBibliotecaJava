
package com.plataforma.laboratoriobiblioteca.model;

public class Prestamo {
    public String isbn;
    public String userId;
    public String fecha;
    
    public Prestamo(String isbn, String userId, String fecha){
        this.isbn = isbn;
        this.userId = userId;
        this.fecha = fecha;
                
    }
}
