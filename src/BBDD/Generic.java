package BBDD;

import java.sql.*;

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
        if (baseDeDatos.equalsIgnoreCase("H2")) {
            conexion = H2.conectar();
        } else if (baseDeDatos.equalsIgnoreCase("SQLLite")) {
            conexion = SQLLite.conectar();
        } else if (baseDeDatos.equalsIgnoreCase("MySQL")) {
            conexion = MySQL.conectar();
        }

        try {
            conexion.createStatement().executeUpdate(SQL);
            System.out.println("Insertado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar en la base de datos");
            throw new RuntimeException(e);
        } finally {
            desconectar(conexion);
        }
    }

    //Modificar
    public static void modificar(String SQL, String baseDeDatos) {
        try {
            Connection conexion = null;
            if (baseDeDatos.equalsIgnoreCase("H2")) {
                conexion = H2.conectar();
            } else if (baseDeDatos.equalsIgnoreCase("SQLLite")) {
                conexion = SQLLite.conectar();
            } else if (baseDeDatos.equalsIgnoreCase("MySQL")) {
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
            if (baseDeDatos.equalsIgnoreCase("H2")) {
                conexion = H2.conectar();
            } else if (baseDeDatos.equalsIgnoreCase("SQLLite")) {
                conexion = SQLLite.conectar();
            } else if (baseDeDatos.equalsIgnoreCase("MySQL")) {
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
            if (baseDeDatos.equalsIgnoreCase("H2")) {
                conexion = H2.conectar();
            } else if (baseDeDatos.equalsIgnoreCase("SQLLite")) {
                conexion = SQLLite.conectar();
            } else if (baseDeDatos.equalsIgnoreCase("MySQL")) {
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

    //Obtener información de la BBDD
    public static void informacionBBDD(String baseDeDatos) {
        Connection conexion = null;
        if (baseDeDatos.equalsIgnoreCase("H2")) {
            conexion = H2.conectar();
        } else if (baseDeDatos.equalsIgnoreCase("SQLLite")) {
            conexion = SQLLite.conectar();
        } else if (baseDeDatos.equalsIgnoreCase("MySQL")) {
            conexion = MySQL.conectar();
        }

        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet rs = null;
            String nombre = dbmd.getDatabaseProductName();
            String driver = dbmd.getDriverName();
            String url = dbmd.getURL();
            String usuario = dbmd.getUserName();
            System.out.println("Nombre: " + nombre);
            System.out.println("Driver: " + driver);
            System.out.println("URL: " + url);
            System.out.println("Usuario: " + usuario);
            System.out.println("*********");
            //tables
            rs = dbmd.getTables("empresa", null, null, null);
            while (rs.next()) {
                //Catalogo
                System.out.println("Catalogo: " + rs.getString(1));
                //Esquema
                System.out.println("Esquema: " + rs.getString(2));
                //Nombre de la tabla
                System.out.println("Tabla: " + rs.getString(3));
                //Tipo de tabla
                System.out.println("Tipo: " + rs.getString(4));
            }
            System.out.println("*********");

            //columns
            rs = dbmd.getColumns("empresa", null, "empleado", null);
            while (rs.next()) {
                //Catalogo
                System.out.println("Catalogo: " + rs.getString(1));
                //Esquema
                System.out.println("Esquema: " + rs.getString(2));
                //Nombre de la tabla
                System.out.println("Tabla: " + rs.getString(3));
                //Nombre de la columna
                System.out.println("Columna: " + rs.getString(4));
                //Tipo de dato
                System.out.println("Tipo: " + rs.getString(5));
                //Tamaño
                System.out.println("Tamaño: " + rs.getString(7));
                //Nulo
                System.out.println("Nulo: " + rs.getString(18));
            }
            System.out.println("*********");

            System.out.println("Columnas de la tabla departamento");
            rs = dbmd.getColumns("empresa", null, "departamento", null);
            while (rs.next()) {
                //Nombre columna
                System.out.println("Nombre de columna: " + rs.getString("COLUMN_NAME"));
                //Type name
                System.out.println("Tipo: " + rs.getString("TYPE_NAME"));
                //Tamaño
                System.out.println("Tamaño de la columna: " + rs.getString("COLUMN_SIZE"));
                //Nulo
                System.out.println("Es nulo?: " + rs.getString("IS_NULLABLE"));
                System.out.println("*********");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
