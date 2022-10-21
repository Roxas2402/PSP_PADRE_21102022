package com.cieep;

import com.cieep.modelos.Alumno;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();


        int opcion;
        do {
            opcion = menu(scanner);
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    Alumno alumno = crearAlumno(scanner);
                    listaAlumnos.add(alumno);
                    break;
                case 2:
                    try {
                        cargarAlumnos(listaAlumnos);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    guardarAlumnos(listaAlumnos);
                    break;
                case 4:
                    muestraAlumnos(listaAlumnos);
                    break;

            }

        } while (opcion != 4);

    }

    private static void cargarAlumnos(ArrayList<Alumno> listaAlumnos) throws IOException {
        listaAlumnos.clear();
        ProcessBuilder pb = new ProcessBuilder("java", "-jar", "librerias/HijoLector.jar");
        pb.redirectErrorStream(true);

        Process proceso = pb.start();
        OutputStream os = proceso.getOutputStream(); // ESTO ES PARA ESCRIBIR
        PrintStream ps = new PrintStream(os);

    }

    private static Alumno crearAlumno(Scanner scanner) {
        System.out.println("Dime el nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Dime los apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.println("Dime el DNI: ");
        String dni = scanner.nextLine();

        return new Alumno(nombre, apellidos, dni);
    }

    private static void guardarAlumnos(ArrayList<Alumno> listaAlumnos) {

    }

    private static void muestraAlumnos(ArrayList<Alumno> listaAlumnos) {
        for (Alumno a : listaAlumnos) {
            System.out.println(a.toString());
        }
    }

    private static int menu(Scanner scanner) {
        System.out.println("1: Crear alumno");
        System.out.println("2: Cargar alumnos");
        System.out.println("3: Guardar alumnos");
        System.out.println("4: Salir");
        return scanner.nextInt();
    }

}