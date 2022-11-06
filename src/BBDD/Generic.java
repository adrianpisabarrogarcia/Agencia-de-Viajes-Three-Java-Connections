package BBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Generic {

    //Desconectar
    public static void desconectar(Connection conexion) {
        try {
            conexion.close();
            //System.out.println("Desconectado de la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al desconectar de la base de datos SQLLite");
            throw new RuntimeException(e);
        }
    }

    //Insertar
    public static void insertar(String SQL, String baseDeDatos) {
        Connection conexion = null;
        if(baseDeDatos.equalsIgnoreCase("H2")) {
            conexion = H2.conectar();
        } else if(baseDeDatos.equalsIgnoreCase("SQLLite")) {
            conexion = SQLLite.conectar();
        } else if(baseDeDatos.equalsIgnoreCase("MySQL")) {
            conexion = MySQL.conectar();
        }
        System.out.println("Insertado correctamente");
        desconectar(conexion);
    }

    //Modificar
    public static void modificar(String SQL, String baseDeDatos) {
        try {
            Connection conexion = null;
            if(baseDeDatos.equalsIgnoreCase("H2")) {
                conexion = H2.conectar();
            } else if(baseDeDatos.equalsIgnoreCase("SQLLite")) {
                conexion = SQLLite.conectar();
            } else if(baseDeDatos.equalsIgnoreCase("MySQL")) {
                conexion = MySQL.conectar();
            }
            conexion.createStatement().executeUpdate(SQL);
            System.out.println("Modificado correctamente");
            desconectar(conexion);
        } catch (SQLException e) {
            System.out.println("Error al modificar en SQLLite");
            throw new RuntimeException(e);
        }
    }

    //Eliminar
    public static void eliminar(String SQL, String baseDeDatos) {
        try {
            Connection conexion = null;
            if(baseDeDatos.equalsIgnoreCase("H2")) {
                conexion = H2.conectar();
            } else if(baseDeDatos.equalsIgnoreCase("SQLLite")) {
                conexion = SQLLite.conectar();
            } else if(baseDeDatos.equalsIgnoreCase("MySQL")) {
                conexion = MySQL.conectar();
            }
            conexion.createStatement().executeUpdate(SQL);
            System.out.println("Eliminado correctamente");
            desconectar(conexion);
        } catch (SQLException e) {
            System.out.println("Error al eliminar en SQLLite");
            throw new RuntimeException(e);
        }
    }

    //Consultar
    public static ResultSet consultar(String SQL, String baseDeDatos) {
        try {
            Connection conexion = null;
            if(baseDeDatos.equalsIgnoreCase("H2")) {
                conexion = H2.conectar();
            } else if(baseDeDatos.equalsIgnoreCase("SQLLite")) {
                conexion = SQLLite.conectar();
            } else if(baseDeDatos.equalsIgnoreCase("MySQL")) {
                conexion = MySQL.conectar();
            }
            ResultSet rs = conexion.createStatement().executeQuery(SQL);
            //System.out.println("Consultado correctamente");
            //desconectar(conexion);

            //Obtener los datos de la consulta
            return rs;
        } catch (SQLException e) {
            System.out.println("Error al consultar en SQLLite");
            throw new RuntimeException(e);
        }
    }



}
