/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea10c;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ck
 */
public class TareaUnidad10 {

    private static ConectorBD base_datos;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        base_datos = new ConectorBD();
        Scanner entrada = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while (!salir) {
            System.out.println("*********************************************");
            System.out.println("1. Alta de persona");
            System.out.println("2. Baja de persona");
            System.out.println("3. Eliminar departamento");
            System.out.println("4. Modificación los datos de una Persona ");
            System.out.println("5. Modificación los datos de un departamento ");
            System.out.println("6. Consultar los registro de las personas de un departamento");
            System.out.println("7. Salir");
            System.out.println("*********************************************");
            System.out.println("Elija una de las opciones");

            opcion = Integer.parseInt(entrada.nextLine());

            switch (opcion) {
                /*
                 case 1 busca 1º si existe le departamento si existe entonces hace unas pregutas
                 para obtener los datos necesario y luego invoca al metodo
                 si no existe hace mas pregutas para poder tener los datos del nuevo departamento 
                 y llama al metodo
                 */
                case 1:
                    Persona p = new Persona();
                    Departamento d = new Departamento();
                    System.out.println("introduzca numero de departamento: ");
                    p.setNumDepartamento(Integer.parseInt(entrada.nextLine()));
                    if (base_datos.existeDepartamento(p.getNumDepartamento()) == true) {
                        System.out.println("introduzca nombre de la persona: ");
                        p.setNombre(entrada.nextLine());
                        System.out.println("introduzca la identificacion de la persona: ");
                        p.setIdentificador(Integer.parseInt(entrada.nextLine()));

                        if (base_datos.altaPersona(p, d)) {
                            System.out.println("Contacto dado de alta correctamente...");
                        } else {

                            System.out.println("Error al dar de alta un contacto...");
                        }
                    } else {
                        System.out.println("introduzca nombre de la persona: ");
                        p.setNombre(entrada.nextLine());
                        System.out.println("introduzca la identificacion de la persona: ");
                        p.setIdentificador(Integer.parseInt(entrada.nextLine()));
                        System.out.println("introduzca nombre del nuevo departamento: ");
                        d.setNombre(entrada.nextLine());
                        System.out.println("introduzca la direccion del nuevo departamento: ");
                        d.setDireccion(entrada.nextLine());
                        if (base_datos.altaPersona(p, d)) {
                            System.out.println("Contacto dado de alta correctamente...");
                        } else {

                            System.out.println("Error al dar de alta un contacto...");
                        }
                    }
                    System.out.println("\nPresiona Intro para continuar....");
                    entrada.nextLine();
                    break;

                case 2:
                    Persona p1 = new Persona();
                    System.out.println("introduzca la identificacion de la persona: ");
                    p1.setIdentificador(Integer.parseInt(entrada.nextLine()));
                    if (base_datos.borrarPersona(p1)) {
                        System.out.println("Contacto borrado de la base de datos...");
                    } else {
                        System.out.println("Contacto no encontrado en la base de datos...");
                    }
                    System.out.println("\nPresiona Intro para continuar....");
                    entrada.nextLine();
                    break;
                /*
                 Mismo caso que el 1 primero busca si hay personas y en funcion de eso
                 hace unas preguntas u otras para poder tener los datos necesarios antes de invocar 
                 al metodo
                 */
                case 3:
                    Departamento d1 = new Departamento();
                    int numero;
                    System.out.println("introduzca numero de departamento: ");
                    numero = Integer.parseInt(entrada.nextLine());
                    if (base_datos.hayGente(numero) == true) {
                        System.out.println("introduzca nombre del nuevo departamento: ");
                        d1.setNombre(entrada.nextLine());
                        System.out.println("introduzca la direccion del nuevo departamento: ");
                        d1.setDireccion(entrada.nextLine());
                        System.out.println("introduzca numero de departamento nuevo: ");
                        d1.setNumDepartamento(Integer.parseInt(entrada.nextLine()));
                        if (base_datos.borrarDepartamento(d1, numero)) {
                            System.out.println("Departamento borrado de la base de datos...");
                        } else {
                            System.out.println("Departamento no encontrado en la base de datos...");
                        }
                    } else {
                        if (base_datos.borrarDepartamento(d1, numero)) {

                            System.out.println("Departamento borrado de la base de datos...");
                        } else {
                            System.out.println("Departamento no encontrado en la base de datos...");
                        }
                    }
                    System.out.println("\nPresiona Intro para continuar....");
                    entrada.nextLine();
                    break;
                case 4:
                    Persona p3 = new Persona();
                    System.out.println("introduzca la identificacion de la persona: ");
                    p3.setIdentificador(Integer.parseInt(entrada.nextLine()));
                    System.out.println("introduzca nombre de la persona: ");
                    p3.setNombre(entrada.nextLine());
                    if (base_datos.actualizarPersona(p3)) {
                        System.out.println("Datos modigicados");
                    } else {
                        System.out.println("no se encuentra a esta persona");
                    }
                    System.out.println("\nPresiona Intro para continuar....");
                    entrada.nextLine();
                    break;
                case 5:
                    Departamento d2 = new Departamento();
                    System.out.println("introduzca nuevo nombre: ");
                    d2.setNombre(entrada.nextLine());
                    System.out.println("introduzca la nueva direccion: ");
                    d2.setDireccion(entrada.nextLine());
                    System.out.println("introduzca numero de departamento que desea modificar: ");
                    d2.setNumDepartamento(Integer.parseInt(entrada.nextLine()));
                    if (base_datos.actualizarDepartamento(d2)) {
                        System.out.println("Datos modigicados");
                    } else {
                        System.out.println("no se encuentra a el departamento");
                    }
                    System.out.println("\nPresiona Intro para continuar....");
                    entrada.nextLine();
                    break;
                case 6:
                    Persona p4 = new Persona();
                    ArrayList<Persona> personas;
                    int numero1;
                    System.out.println("introduzca numero de departamento que desea buscar: ");
                    numero1 = Integer.parseInt(entrada.nextLine());
                    personas = base_datos.personasEnDepartamento(numero1);
                    System.out.println("Contactos encontrados en la base de datos...");
                    for (Persona a : personas) {
                        System.out.println(a);
                    }
                    System.out.println("\nPresiona Intro para continuar....");
                    entrada.nextLine();
                    break;
                case 7:
                    System.out.println("Saliendo de la aplicación....");
                    base_datos.desconectar();
                    salir = true;
                    break;
            }
        }
    }

}
