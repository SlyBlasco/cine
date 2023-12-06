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
public class Funcion {

    private int id;
    private Empleado empleado;
    private Pelicula pelicula;
    private Sala sala;
    private Horario horario;

    // Metodo que lee toda los datos de la tabla funciones y los agrega a una lista.
    public static List<Funcion> getAll() {
        List<Funcion> funciones = new ArrayList<>();
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement(
                    "SELECT funciones.id, empleados.id as empleado_id, empleados.nombre as empleado_nombre, "
                    + "peliculas.titulo as pelicula_nombre, salas.id as sala_id, horarios.fecha as horario_fecha "
                    + "FROM funciones "
                    + "JOIN empleados ON funciones.empleado_id = empleados.id "
                    + "JOIN peliculas ON funciones.pelicula_id = peliculas.id "
                    + "JOIN salas ON funciones.sala_id = salas.id "
                    + "JOIN horarios ON funciones.horario_id = horarios.id"
            );

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Funcion f = new Funcion();
                f.setId(resultSet.getInt("id"));

                Empleado empleado = new Empleado();
                empleado.setNombre(resultSet.getString("empleado_nombre"));
                f.setEmpleado(empleado);

                Pelicula pelicula = new Pelicula();
                pelicula.setTitulo(resultSet.getString("pelicula_nombre"));
                f.setPelicula(pelicula);

                Sala sala = new Sala();
                sala.setId(resultSet.getInt("sala_id"));
                f.setSala(sala);

                Horario horario = new Horario();
                horario.setFecha(resultSet.getDate("horario_fecha"));
                f.setHorario(horario);

                funciones.add(f);
            }
        } catch (SQLException e) {
            System.err.print("Error:" + e.getMessage());
        }
        return funciones;
    }

    public static boolean create(int empleadoId, int peliculaId, int salaId, int horarioId) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "INSERT INTO funciones(empleado_id, pelicula_id, sala_id, horario_id) VALUES(?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, empleadoId);
            statement.setInt(2, peliculaId);
            statement.setInt(3, salaId);
            statement.setInt(4, horarioId);
            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return result;
    }

    // Método que actualiza una de las filas de la tabla funciones.
    public static boolean update(int id, int empleadoId, int peliculaId, int salaId, int horarioId) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "UPDATE funciones SET empleado_id = ?, pelicula_id = ?, sala_id = ?, horario_id = ? WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, empleadoId);
            statement.setInt(2, peliculaId);
            statement.setInt(3, salaId);
            statement.setInt(4, horarioId);
            statement.setInt(5, id);
            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return result;
    }

    // Método que elimina una de las filas de la tabla funciones.
    public static boolean delete(int id) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "DELETE FROM funciones WHERE id = ?";
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

    public static Empleado obtenerEmpleadoPorId(int empleadoId) {
        Empleado empleado = null;
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT id FROM empleados WHERE id = ?");
            statement.setInt(1, empleadoId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                empleado = new Empleado();
                empleado.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.print("Error: " + e.getMessage());
        }
        return empleado;
    }

    public static Pelicula obtenerPeliculaPorId(int peliculaId) {
        Pelicula pelicula = null;
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT id FROM peliculas WHERE id = ?");
            statement.setInt(1, peliculaId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                pelicula = new Pelicula();
                pelicula.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.print("Error: " + e.getMessage());
        }
        return pelicula;
    }

    public static Sala obtenerSalaPorId(int salaId) {
        Sala sala = null;
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT id FROM salas WHERE id = ?");
            statement.setInt(1, salaId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                sala = new Sala();
                sala.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.print("Error: " + e.getMessage());
        }
        return sala;
    }

    public static Horario obtenerHorarioPorId(int horarioId) {
        Horario horario = null;
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT id FROM horarios WHERE id = ?");
            statement.setInt(1, horarioId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                horario = new Horario();
                horario.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.print("Error: " + e.getMessage());
        }
        return horario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

}
