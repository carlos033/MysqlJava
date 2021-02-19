/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea10c;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ck
 */
public class ConectorBD {

    private String bd = "test";
    private String login = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost/" + bd;
    private Connection conn = null;

    public ConectorBD() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            if (conn != null) {
                System.out.println("Conección a base de datos " + bd + ". lista");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void desconectar() {
        conn = null;
        System.out.println("La conexion a la  base de datos " + bd + " ha terminado");
    }

    /*
     Buscamos si existe el departamento contando los nombres que haya con el mismo numero de departamento
     si es 0 no existe el nº buscado y si no pues existe
     */
    public boolean existeDepartamento(int num) {
        boolean existe = false;
        try {
            Statement orden = conn.createStatement();
            ResultSet res;
            res = orden.executeQuery("SELECT count(nombre) total FROM departamento WHERE numDepto ='" + num + "'");
            if (res.next()) {
                existe = res.getInt("total") > 0 ? true : false;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return existe;
    }

    /*
     Si existe el departamento añade la persona
     si no crea el departamento y  luego añade a la persona
     */
    public boolean altaPersona(Persona p, Departamento d) {
        boolean flag = false;
        int numDepartamento = p.getNumDepartamento();
        try {
            if (existeDepartamento(numDepartamento) == true) {
                Statement orden1 = conn.createStatement();
                orden1.executeUpdate("INSERT INTO persona(identificador,nombrePersona,numDepartamento)  VALUES('"
                        + p.getIdentificador() + "','" + p.getNombre() + "','" + p.getNumDepartamento() + "')");
                flag = true;

            } else {
                Statement orden2 = conn.createStatement();
                orden2.executeUpdate("INSERT INTO departamento(Nombre, direccion, numDepto)  VALUES('"
                        + d.getNombre() + "','" + d.getDireccion() + "','" + numDepartamento + "')");
                orden2.close();
                Statement orden3 = conn.createStatement();
                orden3.executeUpdate("INSERT INTO persona(identificador,nombrePersona,numDepartamento)  VALUES('"
                        + p.getIdentificador() + "','" + p.getNombre() + "','" + p.getNumDepartamento() + "')");
                orden3.close();
                flag = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return flag;
    }

    /* 
     siguiendo el mismo metodo de si existe el departamento vemos si hay gente en el 
     contando el nº de nombres que hay en el departamento que queremos
     */
    public boolean hayGente(int num) {
        boolean hay = false;
        try {
            Statement orden = conn.createStatement();
            ResultSet res;
            res = orden.executeQuery("SELECT count(nombrePersona) total FROM persona WHERE numDepartamento ='" + num + "'");
            if (res.next()) {
                hay = res.getInt("total") > 0 ? true : false;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return hay;
    }

    /*
     Si hay personas las cambiamos a otro departamento que creemos y luego eliminamos el departamento
     si no hay pues eliminamos el departamento
     para eliminar el departamentos desactivamso la clave ajena y luego la volvemos a activar
     */
    public boolean borrarDepartamento(Departamento d, int numDepartamento) {
        boolean flag = false;
        String nombreD = d.getNombre();
        String direccion = d.getDireccion();
        int numNDepartamento = d.getNumDepartamento();
        try {
            if (hayGente(numDepartamento) == true) {
                Statement orden1 = conn.createStatement();
                orden1.executeUpdate("INSERT INTO departamento(nombre, direccion, numDepto)  VALUES('"
                        + nombreD + "','" + direccion + "','" + numNDepartamento + "')");
                orden1.close();
                Statement orden5 = conn.createStatement();
                orden5.execute("SET FOREIGN_KEY_CHECKS=0");
                orden5.close();
                Statement orden2 = conn.createStatement();
                orden2.executeUpdate("UPDATE persona "
                        + "SET numDepartamento = '" + numNDepartamento + "' "
                        + "WHERE numDepartamento = " + numDepartamento);
                orden2.close();
                Statement orden3 = conn.createStatement();
                orden3.executeUpdate("DELETE FROM departamento where numDepto =" + numDepartamento);
                orden3.close();
                Statement orden6 = conn.createStatement();
                orden6.execute("SET FOREIGN_KEY_CHECKS=1");
                orden6.close();
                flag = true;
            } else {
                Statement orden4 = conn.createStatement();
                orden4.executeUpdate("DELETE FROM departamento where numDepto =" + numDepartamento);
                orden4.close();
                flag = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return flag;
    }

    public boolean borrarPersona(Persona p) {
        boolean flag = false;
        try {
            Statement orden = conn.createStatement();
            orden.executeUpdate("DELETE FROM persona WHERE identificador = " + p.getIdentificador());
            orden.close();
            flag = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return flag;
    }

    public boolean actualizarPersona(Persona p) {
        boolean flag = false;
        int identificador = p.getIdentificador();
        String nombreE = p.getNombre();
        try {
            Statement orden = conn.createStatement();
            orden.executeUpdate("UPDATE persona "
                    + "SET nombrePersona = '" + nombreE + "' "
                    + "WHERE identificador = " + identificador);
            orden.close();
            flag = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return flag;
    }

    public boolean actualizarDepartamento(Departamento d) {
        boolean flag = false;
        String direccion = d.getDireccion();
        int numDepartamento = d.getNumDepartamento();
        String nombreD = d.getNombre();
        try {
            Statement orden = conn.createStatement();
            orden.executeUpdate("UPDATE departamento "
                    + "SET nombre = '" + nombreD + "',"
                    + "direccion = '" + direccion + "' "
                    + "WHERE numDepto = " + numDepartamento);
            orden.close();
            flag = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return flag;
    }

    public ArrayList personasEnDepartamento(int numDepartamento) {
        ArrayList<Persona> data = new ArrayList();

        try {
            Statement orden = conn.createStatement();
            ResultSet res;
            res = orden.executeQuery("SELECT * FROM persona WHERE numDepartamento = " + numDepartamento);
            while (res.next()) {
                Persona p = new Persona();
                p.setIdentificador(res.getInt("identificador"));
                p.setNombre(res.getString("nombrePersona"));
                p.setNumDepartamento(res.getInt("numDepartamento"));
                data.add(p);
            }
            orden.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }

}
