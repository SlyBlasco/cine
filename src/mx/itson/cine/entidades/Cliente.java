/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.cine.entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.itson.cine.persistencia.MySQLConnection;

/**
 *
 * @author luism
 */
public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    
    // Metodo que lee toda los datos de la tabla clientes y los agrega a una lista.
    public static List<Cliente> getAll(){
        List<Cliente> clientes = new ArrayList();
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM clientes");
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Cliente c = new Cliente ();
                c.setId(resultSet.getInt(1));
                c.setNombre(resultSet.getString(2));
                c.setEmail(resultSet.getString(3));
                clientes.add(c);
            }
        } catch (SQLException e) {
            System.err.print("Error:"+ e.getMessage());
        }
        return clientes;
    }
    
    // Metodo que crea filas en la tabla clientes.
    public static boolean create(String nombre, String email) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "INSERT INTO clientes(nombre, email) VALUES(? , ?)";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, email);
            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return result;
    }

    // Metodo que actualiza una de las filas de la tabla clientes.
    public static boolean update(int id, String nombre, String email) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "UPDATE clientes SET nombre = ?, email = ? WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, email);
            statement.setInt(3, id);
            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return result;
    }

    // Metodo que elimina una de las filas de la tabla clientes.
    public static boolean delete(int id) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "DELETE clientes WHERE id = ?";
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
