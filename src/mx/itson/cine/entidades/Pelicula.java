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
 * Clase para declarar las mismas variablas de la tabla peliculas, asi como los metodo CRUD.
 * @author Luis Blasco, Mario Le Blohic, Emiliano Bojorquez
 */
public class Pelicula {
    private int id;
    private String titulo;
    private String genero;
    private int duracion;
    private String sinopsis;

    // Metodo que lee toda los datos de la tabla peliculas y los agrega a una lista.
    public static List<Pelicula> getAll(String filtro){
        List<Pelicula> peliculas = new ArrayList();
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM peliculas WHERE titulo LIKE ?");
            statement.setString(1, "%"+ filtro +"%");
            
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
    
    // Metodo que crea filas en la tabla peliculas.
    public static boolean create(String titulo, String genero, int duracion, String sinopsis){
            boolean result = false;
            try {
                Connection conexion  = MySQLConnection.get();
                String query = "INSERT INTO peliculas(titulo, genero, duracion, sinopsis) VALUES(? , ? , ?, ?)";
                PreparedStatement statement = conexion.prepareStatement(query);
                statement.setString(1, titulo);
                statement.setString(2, genero);
                statement.setInt(3, duracion);
                statement.setString(4, sinopsis);
                statement.execute();
                
                result = statement.getUpdateCount() == 1;
                
                conexion.close();
            } catch (Exception e) {
                System.err.println("Error: "+ e.getMessage());
            }
            return result;
        }
    
     // Metodo que actualiza una de las filas de la tabla peliculas.
    public static boolean update(int id, String titulo, String genero, int duracion, String sinopsis) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "UPDATE peliculas SET titulo = ?, genero = ?, duracion = ?, sinopsis = ? WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, titulo);
            statement.setString(2, genero);
            statement.setInt(3, duracion);
            statement.setString(4, sinopsis);
            statement.setInt(5, id);
            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return result;
    }

    // Metodo que elimina una de las filas de la tabla peliculas.
    public static boolean delete(int id) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "DELETE from peliculas WHERE id = ?";
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
