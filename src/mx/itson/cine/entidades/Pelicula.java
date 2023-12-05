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
public class Pelicula {
    private int id;
    private String titulo;
    private String genero;
    private int duracion;
    private String sinopsis;

    public static List<Pelicula> getAll(){
        List<Pelicula> peliculas = new ArrayList();
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM peliculas");
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Pelicula p = new Pelicula();
                p.setId(resultSet.getInt(1));
                p.setTitulo(resultSet.getString(2));
                p.setGenero(resultSet.getString(3));
                p.setDuracion(resultSet.getInt(4));
                p.setSinopsis(resultSet.getString(5));
                peliculas.add(p);
            }
        } catch (SQLException e) {
            System.err.print("Error:"+ e.getMessage());
        }
        return peliculas;
    }
    
    public static boolean create(String nombre, String puesto, int salario){
            boolean result = false;
            try {
                Connection conexion  = MySQLConnection.get();
                String query = "INSERT INTO empleados(nombre, puesto, salario) VALUES(? , ? , ?)";
                PreparedStatement statement = conexion.prepareStatement(query);
                statement.setString(1, nombre);
                statement.setString(2, puesto);
                statement.setInt(3, salario);
                statement.execute();
                
                result = statement.getUpdateCount() == 1;
                
                conexion.close();
            } catch (Exception e) {
                System.err.println("Error: "+ e.getMessage());
            }
            return result;
        }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    
    
}
