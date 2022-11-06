package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2 {

    public static Connection conectar() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver");
            throw new RuntimeException(e);
        }

        try {
            Connection conexion = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa","");
            //System.out.println("Conectado a la base de datos");
            return conexion;
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos H2");
            throw new RuntimeException(e);
        }
    }

    //Desconectar
    public static void desconectar(Connection conexion) {
        try {
            conexion.close();
            //System.out.println("Desconectado de la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al desconectar de la base de datos H2");
            throw new RuntimeException(e);
        }
    }

    //Insertar
    public static void insertar(String SQL) {
        try {
            Connection conexion = conectar();
            conexion.createStatement().executeUpdate(SQL);
            System.out.println("Insertado correctamente");
            desconectar(conexion);
        } catch (SQLException e) {
            System.out.println("Error al insertar en H2");
            throw new RuntimeException(e);
        }
    }

    //Modificar
    public static void modificar(String SQL) {
        try {
            Connection conexion = conectar();
            conexion.createStatement().executeUpdate(SQL);
            System.out.println("Modificado correctamente");
            desconectar(conexion);
        } catch (SQLException e) {
            System.out.println("Error al modificar en H2");
            throw new RuntimeException(e);
        }
    }

    //Eliminar
    public static void eliminar(String SQL) {
        try {
            Connection conexion = conectar();
            conexion.createStatement().executeUpdate(SQL);
            System.out.println("Eliminado correctamente");
            desconectar(conexion);
        } catch (SQLException e) {
            System.out.println("Error al eliminar en H2");
            throw new RuntimeException(e);
        }
    }

    //Consultar
    public static ResultSet consultar(String SQL) {
        try {
            Connection conexion = conectar();
            ResultSet rs = conexion.createStatement().executeQuery(SQL);
            System.out.println("Consultado correctamente");
            desconectar(conexion);

            //Obtener los datos de la consulta
            return rs;

        } catch (SQLException e) {
            System.out.println("Error al consultar en H2");
            throw new RuntimeException(e);
        }
    }


}
