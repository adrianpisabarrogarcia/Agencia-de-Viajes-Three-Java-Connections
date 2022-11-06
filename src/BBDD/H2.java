package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2 {

    public static void conectar() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
            System.out.println("Error al cargar el driver");
        }

        try {
            Connection conexion = DriverManager.getConnection("jdbc:h2:D:/DB/H2/ejemplo/ejemplo", "sa","");
        } catch (SQLException e) {
            throw new RuntimeException(e);
            System.out.println("Error al conectar con la base de datos H2");
        }

    }


}
