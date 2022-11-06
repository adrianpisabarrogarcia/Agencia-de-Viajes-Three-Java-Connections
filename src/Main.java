import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //int seleccionAgencias = menuAgencias();
    }


    public static int menuAgencias() {

        System.out.println("******************");
        System.out.println("Menu Agencias");
        System.out.println("******************");
        System.out.println("1. Viajes Eroski - BBDD.MySQL");
        System.out.println("2. Viajes Bakomat - SQLLite");
        System.out.println("3. Viajes Bidasoa - H2");

        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();

        if (opcion < 1 || opcion > 3) {
            System.out.println("Opcion incorrecta");
            return menuAgencias();
        }
        return opcion;
    }
}