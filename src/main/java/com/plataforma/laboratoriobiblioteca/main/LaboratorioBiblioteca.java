package com.plataforma.laboratoriobiblioteca.main;

import com.plataforma.laboratoriobiblioteca.service.Biblioteca;
import com.plataforma.laboratoriobiblioteca.model.Libro;
import com.plataforma.laboratoriobiblioteca.model.Usuario;

import java.util.Scanner;

public class LaboratorioBiblioteca {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Biblioteca b = new Biblioteca();

        int opcion;

        do {
            System.out.println("\n===== SISTEMA DE BIBLIOTECA =====");
            System.out.println("1. Registrar libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Mostrar libros (HashMap)");
            System.out.println("6. Mostrar libros ordenados (BST)");
            System.out.println("7. Mostrar usuarios");
            System.out.println("8. Buscar libro");
            System.out.println("9. Eliminar libro");
            System.out.println("10. Mostrar prestamos");
            System.out.println("11. Mostrar historial");
            System.out.println("12. Relacionar libros");
            System.out.println("13. Mostrar relaciones");
            System.out.println("0. Salir");

            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch(opcion){

                case 1:
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.print("Titulo: ");
                    String titulo = sc.nextLine();

                    b.registrarLibro(new Libro(isbn, titulo));
                    break;

                case 2:
                    System.out.print("ID: ");
                    String id = sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    b.registrarUsuario(new Usuario(id, nombre));
                    break;

                case 3:
                    System.out.print("ISBN del libro: ");
                    String isbnP = sc.nextLine();

                    System.out.print("ID del usuario: ");
                    String user = sc.nextLine();

                    b.prestarLibro(isbnP, user);
                    break;

                case 4:
                    System.out.print("ISBN del libro: ");
                    String isbnD = sc.nextLine();

                    b.devolverLibro(isbnD);
                    break;

                case 5:
                    b.mostrarLibros();
                    break;

                case 6:
                    b.mostrarLibrosOrdenados();
                    break;

                case 7:
                    b.mostrarUsuarios();
                    break;

                case 8:
                    System.out.print("ISBN a buscar: ");
                    String buscar = sc.nextLine();

                    if(b.buscarLibro(buscar) != null){
                        System.out.println("Libro encontrado: " + b.buscarLibro(buscar).titulo);
                    } else {
                        System.out.println("Libro no encontrado");
                    }
                    break;

                case 9:
                    System.out.print("ISBN a eliminar: ");
                    String eliminar = sc.nextLine();

                    b.eliminarLibro(eliminar);
                    break;

                case 10:
                    b.mostrarPrestamos();
                    break;

                case 11:
                    b.mostrarHistorial();
                    break;
                    
                case 12:
                    System.out.print("ISBN libro 1: ");
                    String l1 = sc.nextLine();
                    
                    System.out.print("ISBN libro 2: ");
                    String l2 = sc.nextLine();
                    
                    b.relacionarLibros(l1, l2);
                    break;
                    
                    case 13:
                        b.mostrarRelaciones();
                        break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while(opcion != 0);

        sc.close();
    }
}