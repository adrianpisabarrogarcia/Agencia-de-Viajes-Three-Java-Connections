import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int seleccionAgencias = menuAgencias();
        if ()
    }


    public static int menuAgencias() {

        System.out.println("******************");
        System.out.println("Menu Agencias");
        System.out.println("******************");
        System.out.println("1. Viajes Eroski - MySQL");
        System.out.println("2. Viajes Bakomat - SQLLite");
        System.out.println("3. Viajes Bidasoa - H2");
        System.out.println("4. Viajes Euskotren - PostgreSQL");

        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();

        if (opcion < 1 || opcion > 4) {
            System.out.println("Opcion incorrecta");
            return menuAgencias();
        }
        return opcion;
    }
}