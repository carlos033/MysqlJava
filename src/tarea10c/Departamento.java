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
public class Departamento {

    /*
     Longitud de cadenas 20 de maximo y numero de departamento 2 digitos a lo sumo
     */

    private String nombre;
    private String direccion;
    private int numDepartamento;

    public Departamento(String nombre, String direccion, int numDepartamento) {
        this.setNombre(nombre);
        this.setDireccion(direccion);
        this.setNumDepartamento(numDepartamento);
    }

    public Departamento() {
        this.setNombre("");
        this.setDireccion("");
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion != "" && direccion.length() <= 20) {
            this.direccion = direccion;
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
        String src = "Departamento con nombre:" + nombre + "direccion: " + direccion
                + "y numero: " + numDepartamento;
        return src;
    }

}
