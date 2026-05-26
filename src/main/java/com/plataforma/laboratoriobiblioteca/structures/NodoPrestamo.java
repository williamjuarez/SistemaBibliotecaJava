
package com.plataforma.laboratoriobiblioteca.structures;

import com.plataforma.laboratoriobiblioteca.model.Prestamo;

public class NodoPrestamo {
    public Prestamo prestamo;
    public NodoPrestamo Siguiente;
    
    public NodoPrestamo(Prestamo prestamo){
        this.prestamo = prestamo;
        this.Siguiente = null;
    }
}
