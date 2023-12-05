/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.cine.persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 * Clase que se usa para establecer una conección entre la base de datos y el programa java.
 * @author Luis Blasco, Emiliano Bojorquez, Mario Le Blohic 
 */
public class MySQLConnection {
    public static Connection get(){
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://p3nlmysql165plsk.secureserver.net:3306/cine","root","");
        } catch (Exception e) {
            System.err.print("Error: "+ e.getMessage());
        }
        return conexion;
    }
}
