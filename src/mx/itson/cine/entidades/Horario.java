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
import java.sql.Date;
import java.util.List;
import mx.itson.cine.persistencia.MySQLConnection;

/**
 * Clase para declarar las mismas variablas de la tabla horarios, asi como los metodo CRUD.
 * @author Luis Blasco, Mario Le Blohic, Emiliano Bojorquez
 */
public class Horario {
    private int id;
    private String horaInicio;
    private String horaFinal;
    private Date fecha;

    // Metodo que lee toda los datos de la tabla horarios y los agrega a una lista.
    public static List<Horario> getAll(String filtro){
        List<Horario> horarios = new ArrayList();
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM horarios where fecha like ?");
            statement.setString(1, "%"+ filtro +"%");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Horario h = new Horario();
                h.setId(resultSet.getInt(1));
                h.setHoraInicio(resultSet.getString(2));
                h.setHoraFinal(resultSet.getString(3));
                h.setFecha(resultSet.getDate(4));
                horarios.add(h);
            }
        } catch (SQLException e) {
            System.err.print("Error:"+ e.getMessage());
        }
        return horarios;
    }

      // Metodo que crea filas en la tabla horarios.
    public static boolean create(String horaInicio, String horaFinal, Date fecha) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "INSERT INTO horarios(hora_inicio, hora_final, fecha) VALUES(? , ? , ?)";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, horaInicio);
            statement.setString(2, horaFinal);
            statement.setDate(3,fecha);
            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return result;
    }

    // Metodo que actualiza una de las filas de la tabla horarios.
    public static boolean update(int id, String horaInicio, String horaFinal, Date fecha) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "UPDATE horarios SET hora_inicio = ?, hora_final = ?, fecha = ? WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, horaInicio);
            statement.setString(2, horaFinal);
            statement.setDate(3, fecha);
            statement.setInt(4, id);
            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return result;
    }

    // Metodo que elimina una de las filas de la tabla horarios.
    public static boolean delete(int id) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "DELETE from horarios WHERE id = ?";
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
    
    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
