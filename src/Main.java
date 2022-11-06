import Models.Agencia;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String baseDeDatos = "SQL";

        int seleccionAgencias = menuAgencias();
        baseDeDatos = seleccionarBaseDeDatos(seleccionAgencias);
        int seleccionMenuPrincipal = 0;
        while (seleccionMenuPrincipal != 15){
            seleccionMenuPrincipal = menuPrincipal(seleccionAgencias, baseDeDatos);
        }
    }

    //Devuelve el nombre de la agencia seleccionada
    private static String nombreAgencia(int seleccionAgencias) {
        return switch (seleccionAgencias) {
            case 1 -> "Viajes Eroski";
            case 2 -> "Viajes Bakomat";
            case 3 -> "Viajes Bidasoa";
            default -> "";
        };
    }

    //Menú principal de la agencia
    private static int menuPrincipal(int seleccionAgencias, String baseDeDatos) {
        System.out.println("Bienvenido a " + nombreAgencia(seleccionAgencias));
        System.out.println("******************");
        System.out.println("🛫 Menu Principal de la agencia  🛫");
        System.out.println("******************");
        System.out.println("1. Mostrar los datos de la agencia");
        System.out.println("2. Listar las vistas guiadas");
        System.out.println("3. Listado de los clientes de cada visita guiada");
        System.out.println("4. Listado de los empleados de la agencia.");
        System.out.println("5. Alta de un nuevo empleado.");
        System.out.println("6. Alta de una nueva visita guiada.");
        System.out.println("7. Alta de un nuevo cliente.");
        System.out.println("8. Baja de una visita guiada.");
        System.out.println("9. Baja de un empleado.");
        System.out.println("10. Baja de un cliente.");
        System.out.println("11. Modificar datos de un cliente.");
        System.out.println("12. Modificar datos de una visita guiada.");
        System.out.println("13. Modificar datos de un empleado.");
        System.out.println("14. Listado de los metadatos de los elementos de la BBDD.");
        System.out.println("15. Salir.");
        System.out.println("******************");
        System.out.println("Selecciona una opción: ");
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        try {
            opcion = sc.nextInt();
        } catch (Exception e) {
            opcion = 0;
        }

        switch (opcion) {
            case 1 -> {
                System.out.println("Datos de la agencia");
                datosDeLaAgencia(baseDeDatos);
            }
            case 2 -> {
                System.out.println("Listar las vistas guiadas");
                listarVisitasGuiadas(baseDeDatos);
            }
            case 3 -> {
                System.out.println("Listar los clientes de cada visita guiada");
                listarClientesDeCadaVisitaGuiada(baseDeDatos);
            }
            case 4 -> {
                System.out.println("Listar los empleados de la agencia");
                listarEmpleadosDeLaAgencia(baseDeDatos);
            }
            case 5 -> {
                System.out.println("Alta de un nuevo empleado");
                altaDeUnNuevoEmpleado(baseDeDatos);
            }
            case 6 -> {
                System.out.println("Alta de una nueva visita guiada");
                altaDeUnaNuevaVisitaGuiada(baseDeDatos);
            }
            case 7 -> {
                System.out.println("Alta de un nuevo cliente");
                altaDeUnNuevoCliente(baseDeDatos);
            }
            case 8 -> {
                System.out.println("Baja de una visita guiada");
                bajaDeUnaVisitaGuiada(baseDeDatos);
            }
            case 9 -> {
                System.out.println("Baja de un empleado");
                bajaDeUnEmpleado(baseDeDatos);
            }
            case 10 -> {
                System.out.println("Baja de un cliente");
                bajaDeUnCliente(baseDeDatos);
            }
            case 11 -> {
                System.out.println("Modificar datos de un cliente");
                modificarDatosDeUnCliente(baseDeDatos);
            }
            case 12 -> {
                System.out.println("Modificar datos de una visita guiada");
                modificarDatosDeUnaVisitaGuiada(baseDeDatos);
            }
            case 13 -> {
                System.out.println("Modificar datos de un empleado");
                modificarDatosDeUnEmpleado(baseDeDatos);
            }
            case 14 -> {
                System.out.println("Listado de los metadatos de los elementos de la BBDD");
                listadoDeLosMetadatosDeLosElementosDeLaBBDD(baseDeDatos);
            }
            case 15 -> {
                System.out.println("Saliendo...");
            }
            default -> {
                System.out.println("⚠️ Debes seleccionar una opción válida");
                opcion = 0;
            }
        }
        return opcion;
    }


    //Mostar los datos de la agencia
    private static void datosDeLaAgencia(String baseDeDatos) {
        String SQL = "SELECT * FROM agencia";
        ResultSet rs = null;
        Agencia agencia = new Agencia();
        try {
            rs = BBDD.Generic.consultar(SQL, baseDeDatos);
            while (rs.next()) {
                agencia.setId(rs.getInt("id"));
                agencia.setNombre(rs.getString("nombre"));
                //para SQL Lite
                //get fecha_apertura as a string and convert it to a date
                String fechaApertura = rs.getString("fecha_apertura");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(fechaApertura);
                agencia.setFechaApertura(date);
                agencia.setDireccion(rs.getString("direccion"));
                agencia.setTelefono(rs.getString("telefono"));
                agencia.setEmail(rs.getString("email"));
                agencia.setWeb(rs.getString("web"));
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar los datos de la agencia");
            System.out.println(e.getMessage());
        }
        System.out.println(agencia.toString());
    }

    private static void listarVisitasGuiadas(String baseDeDatos) {

    }

    private static void listarClientesDeCadaVisitaGuiada(String baseDeDatos) {

    }

    private static void listarEmpleadosDeLaAgencia(String baseDeDatos) {

    }

    private static void altaDeUnNuevoEmpleado(String baseDeDatos) {

    }

    private static void altaDeUnaNuevaVisitaGuiada(String baseDeDatos) {

    }

    private static void altaDeUnNuevoCliente(String baseDeDatos) {

    }

    private static void bajaDeUnaVisitaGuiada(String baseDeDatos) {

    }

    private static void bajaDeUnEmpleado(String baseDeDatos) {

    }

    private static void bajaDeUnCliente(String baseDeDatos) {

    }

    private static void modificarDatosDeUnCliente(String baseDeDatos) {

    }

    private static void modificarDatosDeUnaVisitaGuiada(String baseDeDatos) {

    }

    private static void modificarDatosDeUnEmpleado(String baseDeDatos) {

    }

    private static void listadoDeLosMetadatosDeLosElementosDeLaBBDD(String baseDeDatos) {
        
    }

    //Método para seleccionar la base de datos que corresponda a cada agencia
    private static String seleccionarBaseDeDatos(int seleccionAgencias) {
        String baseDeDatos = "";
        switch (seleccionAgencias) {
            case 1:
                baseDeDatos = "H2";
                break;
            case 2:
                baseDeDatos = "SQLLite";
                break;
            case 3:
                baseDeDatos = "MySQL";
                break;
        }
        return baseDeDatos;
    }

    //Menú para seleccionar la agencia
    public static int menuAgencias() {
        System.out.println("******************");
        System.out.println("✈️ Menu Agencias");
        System.out.println("******************");
        System.out.println("1. "+nombreAgencia(1)+" - H2");
        System.out.println("2. "+nombreAgencia(2)+" - SQLLite");
        System.out.println("3. "+nombreAgencia(3)+" - MySQL");
        System.out.println("Selecciona una opción: ");
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        try {
            opcion = sc.nextInt();
        } catch (Exception e) {
            opcion = 0;
        }

        switch (opcion){
            case 1:
                System.out.println("Has seleccionado Viajes Eroski");
                break;
            case 2:
                System.out.println("Has seleccionado Viajes Bakomat");
                break;
            case 3:
                System.out.println("Has seleccionado Viajes Bidasoa");
                break;
            default:
                System.out.println("⚠️ Debes seleccionar una opción válida");
                menuAgencias();
        }
        return opcion;
    }
}