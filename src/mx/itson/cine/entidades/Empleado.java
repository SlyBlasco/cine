/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.cine.entidades;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mx.itson.cine.persistencia.MySQLConnection;

/**
 *
 * @author luism
 */
public class Empleado {

    private int id;
    private String nombre;
    private String puesto;
    private double salario;

    public static List<Empleado> getAll() {
        List<Empleado> empleados = new ArrayList();
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM empleados");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Empleado e = new Empleado();
                e.setId(resultSet.getInt(1));
                e.setNombre(resultSet.getString(2));
                e.setPuesto(resultSet.getString(3));
                e.setSalario(resultSet.getInt(4));
                empleados.add(e);
            }
        } catch (SQLException e) {
            System.err.print("Error:" + e.getMessage());
        }
        return empleados;
    }

    public static boolean create(String nombre, String puesto, int salario) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "INSERT INTO empleados(nombre, puesto, salario) VALUES(? , ? , ?)";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, puesto);
            statement.setInt(3, salario);
            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return result;
    }

    public static boolean update(int id, String name, String phone) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "UPDATE officer SET name = ?, phone = ? WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, phone);
            statement.setInt(3, id);
            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return result;
    }

    public static boolean delete(int id) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "DELETE officer WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return result;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

}
