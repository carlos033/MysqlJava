/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea10c;

/**
 *
 * @author ck
 */
public class Persona {

    /*
     La longitud maxima de la cadenas es 20 y de los int es 2 para el numero y 4 para el identificador
     entinedo que puedo tener el departamento -3 si me da la gana y el id pues igual nadie dice 
     lo contrario
     */

    private String nombre;
    private int identificador;
    private int numDepartamento;

    public Persona(String nombre, int identificador, int numDepartamento) {
        this.setNombre(nombre);
        this.setIdentificador(identificador);
        this.setNumDepartamento(numDepartamento);
    }

    public Persona() {
        this.setNombre("");
        this.setIdentificador(0);
        this.setNumDepartamento(0);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != "" && nombre.length() <= 20) {
            this.nombre = nombre;
        }
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        if (Integer.toString(identificador).length() <= 4) {
            this.identificador = identificador;
        }
    }

    public int getNumDepartamento() {
        return numDepartamento;
    }

    public void setNumDepartamento(int numDepartamento) {
        if (Integer.toString(numDepartamento).length() <= 2) {
            this.numDepartamento = numDepartamento;
        }
    }

    @Override
    public String toString() {
        String src = "Trabajador con nombre: " + nombre + "\n numero de identificador: " + identificador
                + "\n numDepartamento: " + numDepartamento;

        return src;
    }
}
