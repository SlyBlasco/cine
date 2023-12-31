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
 * Clase para declarar las mismas variablas de la tabla salas, asi como los metodo CRUD.
 * @author Luis Blasco, Mario Le Blohic, Emiliano Bojorquez
 */
public class Sala {
    private int id;
    private String tipo;
    private int capacidad;
    private boolean disponible;
    
    // Metodo que lee toda los datos de la tabla salas y los agrega a una lista.
    public static List<Sala> getAll(String filtro) {
        List<Sala> salas = new ArrayList();
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM salas WHERE disponible LIKE ?");
            statement.setString(1, "%"+ filtro +"%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Sala s = new Sala();
                s.setId(resultSet.getInt(1));
                s.setTipo(resultSet.getString(2));
                s.setCapacidad(resultSet.getInt(3));
                s.setDisponible(resultSet.getBoolean(4));
                salas.add(s);
            }
        } catch (SQLException e) {
            System.err.print("Error:" + e.getMessage());
        }
        return salas;
    }

    // Metodo que crea filas en la tabla salas.
    public static boolean create(String tipo, String capacidad, boolean disponible) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "INSERT INTO salas(tipo, capacidad, disponible) VALUES(? , ? , ?)";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, tipo);
            statement.setString(2, capacidad);
            statement.setBoolean(3, disponible);
            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return result;
    }

    // Metodo que actualiza una de las filas de la tabla salas.
    public static boolean update(int id, String tipo, String capacidad, boolean disponible) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "UPDATE salas SET tipo = ?, capacidad = ?, disponible = ? WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, tipo);
            statement.setString(2, capacidad);
            statement.setBoolean(3, disponible);
            statement.setInt(4, id);
            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return result;
    }

    // Metodo que elimina una de las filas de la tabla salas.
    public static boolean delete(int id) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "DELETE from salas WHERE id = ?";
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    
}
