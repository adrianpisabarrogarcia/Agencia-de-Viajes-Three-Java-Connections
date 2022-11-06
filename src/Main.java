import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String baseDeDatos = "SQL";

        int seleccionAgencias = menuAgencias();
        baseDeDatos = seleccionarBaseDeDatos(seleccionAgencias);
        int seleccionMenuPrincipal = menuPrincipal(seleccionAgencias, baseDeDatos);
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
        System.out.println("1. Viajes Eroski - H2");
        System.out.println("2. Viajes Bakomat - SQLLite");
        System.out.println("3. Viajes Bidasoa - MySQL");
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