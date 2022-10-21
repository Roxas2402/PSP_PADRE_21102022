package com.cieep;

import com.cieep.modelos.Alumno;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
                        System.out.println("Dime el nombre del archivo: ");
                        cargarAlumnos(listaAlumnos, scanner.nextLine());
                    } catch (IOException e) {
                        System.out.println("Error al leer el fichero.");
                    }
                    break;
                case 3:
                    System.out.println("Dime el nombre del fichero: ");
                    try {
                        guardarAlumnos(listaAlumnos, scanner.nextLine());
                    } catch (IOException e) {
                        System.out.println("Error al escribir el fichero.");
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    muestraAlumnos(listaAlumnos);
                    break;

            }

        } while (opcion != 4);

    }

    private static void cargarAlumnos(ArrayList<Alumno> listaAlumnos, String fileName) throws IOException {
        listaAlumnos.clear();
        ProcessBuilder pb = new ProcessBuilder("java", "-jar", "librerias/HijoLector.jar");
        pb.redirectErrorStream(true);

        Process proceso = pb.start();
        OutputStream os = proceso.getOutputStream(); //ESTO ES PARA ESCRIBIR
        PrintStream ps = new PrintStream(os);

        InputStream is = proceso.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);

        ps.println(fileName);
        ps.flush();

        if ((new File(fileName)).exists()) {
            listaAlumnos.clear();
        } else {
            return;
        }

        String linea;
        while (!(linea = br.readLine()).isEmpty()) {
            listaAlumnos.add(new Alumno(linea));
        }
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

    private static void guardarAlumnos(ArrayList<Alumno> listaAlumnos, String fileName) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("java", "-jar", "librerias/HijoEscritor.jar");
        pb.redirectErrorStream(true);
        Process process = pb.start();

        OutputStream os = process.getOutputStream();
        PrintStream ps = new PrintStream(os);

        ps.println(fileName);
        ps.flush();

        for (Alumno a : listaAlumnos) {
            ps.println(a.toFile());
            ps.flush();
        }

        ps.println();
        ps.flush();
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