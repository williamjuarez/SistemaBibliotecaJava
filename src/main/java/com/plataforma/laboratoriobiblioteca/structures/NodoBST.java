
package com.plataforma.laboratoriobiblioteca.structures;

import com.plataforma.laboratoriobiblioteca.model.Libro;

public class NodoBST {
    public Libro libro;
    public NodoBST izquierda;
    public NodoBST derecha;

    public NodoBST(Libro libro){
        this.libro = libro;
        this.izquierda = null;
        this.derecha = null;
    }
}