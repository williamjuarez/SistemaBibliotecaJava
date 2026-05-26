
package com.plataforma.laboratoriobiblioteca.structures;

import com.plataforma.laboratoriobiblioteca.model.Prestamo;

public class ListaPrestamos {
    
    private NodoPrestamo cabeza;
    
    public void agregar(Prestamo prestamo){
        NodoPrestamo nuevo = new NodoPrestamo(prestamo);
        
        if(cabeza == null){
            cabeza = nuevo;
        } else {
            NodoPrestamo temp = cabeza;
            while(temp.Siguiente != null){
                temp = temp.Siguiente;
            }
            
            temp.Siguiente = nuevo;
        }
    }
        public void mostrar(){
            NodoPrestamo temp = cabeza;
            
            while(temp != null){
                System.out.println("ISBN: " + temp.prestamo.isbn + 
                       ", Usuario: " + temp.prestamo.userId + 
                       ", Fecha: " + temp.prestamo.fecha);
                temp = temp.Siguiente;
            }
        }
    }

