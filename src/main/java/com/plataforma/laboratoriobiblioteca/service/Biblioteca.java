package com.plataforma.laboratoriobiblioteca.service;

import com.plataforma.laboratoriobiblioteca.structures.ListaPrestamos;
import com.plataforma.laboratoriobiblioteca.model.Prestamo;
import com.plataforma.laboratoriobiblioteca.structures.NodoBST;
import com.plataforma.laboratoriobiblioteca.model.Usuario;
import com.plataforma.laboratoriobiblioteca.model.Libro;
import java.util.*;

public class Biblioteca {

    HashMap<String, Libro> libros = new HashMap<>();
    HashMap<String, Usuario> usuarios = new HashMap<>();
    HashMap<String, List<String>> grafoLibros = new HashMap<>();
    
    Queue<Usuario> colaEspera = new LinkedList<>();
    Stack<String> historial = new Stack<>();
    
    ListaPrestamos listaPrestamos = new ListaPrestamos();
    NodoBST raiz = null;

    // ================= BST =================
    public NodoBST insertar(NodoBST nodo, Libro libro){
        if(nodo == null) return new NodoBST(libro);

        if(libro.titulo.compareTo(nodo.libro.titulo) < 0){
            nodo.izquierda = insertar(nodo.izquierda, libro);
        } else {
            nodo.derecha = insertar(nodo.derecha, libro);
        }

        return nodo;
    }

    // ================= LIBROS =================
    public void registrarLibro(Libro libro){
        libros.put(libro.isbn, libro);
        raiz = insertar(raiz, libro);
        historial.push("Libro registrado: " + libro.titulo);
    }

    public Libro buscarLibro(String isbn){
        return libros.get(isbn);
    }

    public void mostrarLibros(){
        for(Libro l : libros.values()){
            System.out.println("ISBN: " + l.isbn +
                               ", Titulo: " + l.titulo +
                               ", Disponible: " + l.disponible);
        }
    }

    public void eliminarLibro(String isbn){
        Libro eliminado = libros.remove(isbn);

        if(eliminado != null){
            historial.push("Libro eliminado: " + eliminado.titulo);
            System.out.println("Libro eliminado correctamente");
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    // ================= USUARIOS =================
    public void registrarUsuario(Usuario usuario){
        usuarios.put(usuario.id, usuario);
        historial.push("Usuario registrado: " + usuario.nombre);
    }

    public Usuario buscarUsuario(String id){
        return usuarios.get(id);
    }

    public void mostrarUsuarios(){
        for(Usuario u : usuarios.values()){
            System.out.println("ID: " + u.id +
                               ", Nombre: " + u.nombre);
        }
    }

    // ================= PRÉSTAMOS =================
    public void prestarLibro(String isbn, String userID){
        Libro libro = libros.get(isbn);
        Usuario usuario = usuarios.get(userID);

        if(libro == null){
            System.out.println("Libro no encontrado");
            return;
        }

        if(usuario == null){
            System.out.println("Usuario no encontrado");
            return;
        }

        if(libro.disponible){
            libro.disponible = false;

            Prestamo p = new Prestamo(isbn, userID, "2026-05-05");
            listaPrestamos.agregar(p);

            historial.push("Libro prestado a " + usuario.nombre);
        } else {
            colaEspera.add(usuario);
            historial.push("Usuario en espera: " + usuario.nombre);
        }
    }

    // ================= DEVOLUCIÓN =================
    public void devolverLibro(String isbn){
        Libro libro = libros.get(isbn);

        if (libro == null){
            System.out.println("Libro no encontrado");
            return;
        }

        if (!colaEspera.isEmpty()) {
            Usuario siguiente = colaEspera.poll();

            Prestamo p = new Prestamo(isbn, siguiente.id, "2026-05-05");
            listaPrestamos.agregar(p);

            historial.push("Libro asignado a: " + siguiente.nombre);
        } else {
            libro.disponible = true;
            historial.push("Libro devuelto y disponible");
        }
    }

    // ================= HISTORIAL =================
    public void mostrarHistorial(){
        System.out.println("\n---------- Historial de acciones ----------");
        for (String h : historial){
            System.out.println(h);
        }
        System.out.println("-----------------------------------------");
    }

    // ================= PRÉSTAMOS =================
    public void mostrarPrestamos(){
        System.out.println("\n------ Lista de Prestamos ------");
        listaPrestamos.mostrar();
    }

    // ================= BST (ORDENADOS) =================
    public void mostrarLibrosOrdenados(NodoBST nodo){
        if(nodo != null) {
            mostrarLibrosOrdenados(nodo.izquierda);

            System.out.println("Titulo: " + nodo.libro.titulo + 
                               ", ISBN: " + nodo.libro.isbn + 
                               ", Disponible: " + nodo.libro.disponible);

            mostrarLibrosOrdenados(nodo.derecha);
        } 
    }
    
    public void relacionarLibros(String isbn1, String isbn2){
    grafoLibros.putIfAbsent(isbn1, new ArrayList<>());
    grafoLibros.get(isbn1).add(isbn2);

    historial.push("Relacion creada entre " + isbn1 + " y " + isbn2);
}
    
    public void mostrarRelaciones(){
    System.out.println("\n----- RELACIONES DE LIBROS -----");

    for(String isbn : grafoLibros.keySet()){
        System.out.print("Libro " + isbn + " relacionado con: ");

        for(String rel : grafoLibros.get(isbn)){
            System.out.print(rel + " ");
        }

        System.out.println();
    }
}
    
    

    public void mostrarLibrosOrdenados(){
        mostrarLibrosOrdenados(raiz);
    }
}