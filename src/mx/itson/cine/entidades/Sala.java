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
public class Sala {
    private int id;
    private String tipo;
    private int capacidad;
    
    public static List<Sala> getAll() {
        List<Sala> salas = new ArrayList();
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM salas");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Sala s = new Sala();
                s.setId(resultSet.getInt(1));
                s.setTipo(resultSet.getString(2));
                s.setCapacidad(resultSet.getInt(3));
                salas.add(s);
            }
        } catch (SQLException e) {
            System.err.print("Error:" + e.getMessage());
        }
        return salas;
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
    
    
}
