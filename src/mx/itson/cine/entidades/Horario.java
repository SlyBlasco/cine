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
import java.util.Date;
import java.util.List;
import mx.itson.cine.persistencia.MySQLConnection;

/**
 *
 * @author luism
 */
public class Horario {
    private int id;
    private String horaInicio;
    private String horaFinal;
    private Date fecha;
    
    public static List<Horario> getAll(){
        List<Horario> horarios = new ArrayList();
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM horarios");
            
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
