package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLLite {
    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:agenciadeviajes");
            //System.out.println("Conectado a la base de datos");
            return conexion;
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos SQLLite");
            throw new RuntimeException(e);
        }
    }
}
