import Models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        System.out.println("Bienvenid@ a " + nombreAgencia(seleccionAgencias));
        System.out.println("******************");
        System.out.println("🛫 Menú Principal de la agencia  🛫");
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
            case 1 -> { // ✅
                System.out.println("Datos de la agencia");
                datosDeLaAgencia(baseDeDatos);
            }
            case 2 -> { // ✅
                System.out.println("Listar las vistas guiadas");
                listarVisitasGuiadas(baseDeDatos);
            }
            case 3 -> { // ✅
                System.out.println("Listar los clientes de cada visita guiada");
                listarClientesDeCadaVisitaGuiada(baseDeDatos);
            }
            case 4 -> { // ✅
                System.out.println("Listar los empleados de la agencia");
                listarEmpleadosDeLaAgencia(baseDeDatos);
            }
            case 5 -> { // ✅
                System.out.println("Alta de un nuevo empleado");
                altaDeUnNuevoEmpleado(baseDeDatos);
            }
            case 6 -> { // ✅
                System.out.println("Alta de una nueva visita guiada");
                altaDeUnaNuevaVisitaGuiada(baseDeDatos);
            }
            case 7 -> { // ✅
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
                BBDD.Generic.informacionBBDD(baseDeDatos);
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
        String SQL = "SELECT V.*, E.id AS \"empleado_id\", E.DNI AS \"empleado_dni\", E.nombre AS \"empleado_nombre\", E.APELLIDO AS \"empelado_apellido\", E.FECHA_NACIMIENTO AS \"empleado_fecha_nacimiento\", E.FECHA_CONTRATACION AS \"empleado_fecha_contratacion\", E.NACIONALIDAD AS \"empleado_nacionalidad\", E.CARGO AS \"empleado_cargo\", E.ACTIVO AS \"empleado_activo\" FROM VISITAGUIADA V, EMPLEADO E WHERE V.RESPONSABLE_id = E.id";
        ResultSet rs = null;
        ArrayList<VisitaGuiada> visitasGuiadas = new ArrayList<>();
        try {
            rs = BBDD.Generic.consultar(SQL, baseDeDatos);
            while (rs.next()) {
                VisitaGuiada visitaGuiada = new VisitaGuiada();
                visitaGuiada.setId(rs.getInt("id"));
                visitaGuiada.setNombre(rs.getString("nombre"));
                visitaGuiada.setNumClientesMax(rs.getInt("num_clientes_max"));
                visitaGuiada.setDireccionPuntoPartida(rs.getString("direccion_punto_partida"));
                visitaGuiada.setTematica(rs.getString("tematica"));
                visitaGuiada.setLugar(rs.getString("lugar"));
                visitaGuiada.setPrecio(rs.getDouble("precio"));
                String fechaString = rs.getString("fecha");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(fechaString);
                visitaGuiada.setFecha(date);
                String horaString = rs.getString("hora_inicio");
                SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
                Date hora = formatter2.parse(horaString);
                Time timeInicio = new Time(hora.getTime());
                visitaGuiada.setHoraInicio(timeInicio);
                String horaFinString = rs.getString("hora_fin");
                SimpleDateFormat formatterHoraFin = new SimpleDateFormat("HH:mm:ss");
                Date horaFin = formatterHoraFin.parse(horaFinString);
                Time timeFin = new Time(horaFin.getTime());
                visitaGuiada.setHoraFin(timeFin);
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("empleado_id"));
                empleado.setDni(rs.getString("empleado_dni"));
                empleado.setNombre(rs.getString("empleado_nombre"));
                empleado.setApellido(rs.getString("empelado_apellido"));
                String fechaNacimientoString = rs.getString("empleado_fecha_nacimiento");
                Date fechaNacimiento = formatter.parse(fechaNacimientoString);
                empleado.setFechaNacimiento(fechaNacimiento);
                String fechaContratacionString = rs.getString("empleado_fecha_contratacion");
                Date fechaContratacion = formatter.parse(fechaContratacionString);
                empleado.setFechaContratacion(fechaContratacion);
                empleado.setNacionalidad(rs.getString("empleado_nacionalidad"));
                empleado.setCargo(rs.getString("empleado_cargo"));
                empleado.setActivo(rs.getBoolean("empleado_activo"));
                visitaGuiada.setResponsable(empleado);
                visitaGuiada.setActivo(rs.getBoolean("activo"));
                visitasGuiadas.add(visitaGuiada);
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar los datos de la agencia");
            System.out.println(e.getMessage());
        }
        visitasGuiadas.forEach(System.out::println);
    }

    private static void listarClientesDeCadaVisitaGuiada(String baseDeDatos) {
        String SQL = "SELECT c.ID AS \"id\", c.FECHA AS \"fecha\", c.ID_CLIENTE AS \"id_cliente\", c.ID_VISITA AS \"id_visita\", c2.ID AS \"cliente_id\", c2.DNI AS \"cliente_dni\", c2.NOMBRE AS \"cliente_nombre\", c2.APELLIDOS AS \"cliente_apellidos\", c2.EDAD AS \"cliente_edad\", c2.PROFESION AS \"cliente_profesion\", c2.ACTIVO AS \"cliente_activo\", v.ID AS \"visita_id\", V.NOMBRE AS \"visita_nombre\", v.NUM_CLIENTES_MAX AS \"visita_num_clientes_max\", v.DIRECCION_PUNTO_PARTIDA AS \"visita_direccion_punto_partida\", v.TEMATICA AS \"visita_tematica\", v.LUGAR AS \"visita_lugar\", V.PRECIO AS \"visita_precio\", V.FECHA AS \"visita_fecha\", V.HORA_INICIO AS \"visita_hora_inicio\", V.HORA_FIN AS \"visita_hora_fin\", V.RESPONSABLE_ID AS \"visita_responsable_id\", V.ACTIVO AS \"visita_activo\" FROM COMPRAVISITAGUIADA c, CLIENTE c2, VISITAGUIADA v WHERE c.ID_CLIENTE = c2.ID AND c.ID_VISITA = v.ID;";
        ResultSet rs = null;
        ArrayList<CompraVisitaGuiada> comprasVisitasGuiadas = new ArrayList<>();
        try {
            rs = BBDD.Generic.consultar(SQL, baseDeDatos);
            while (rs.next()) {
                CompraVisitaGuiada compraVisitaGuiada = new CompraVisitaGuiada();
                compraVisitaGuiada.setId(rs.getInt("id"));
                String fechaString = rs.getString("fecha");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(fechaString);
                compraVisitaGuiada.setFecha(date);
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setDni(rs.getString("cliente_dni"));
                cliente.setNombre(rs.getString("cliente_nombre"));
                cliente.setApellidos(rs.getString("cliente_apellidos"));
                cliente.setEdad(rs.getInt("cliente_edad"));
                cliente.setProfesion(rs.getString("cliente_profesion"));
                cliente.setActivo(rs.getBoolean("cliente_activo"));
                compraVisitaGuiada.setCliente(cliente);
                VisitaGuiada visitaGuiada = new VisitaGuiada();
                visitaGuiada.setId(rs.getInt("visita_id"));
                visitaGuiada.setNombre(rs.getString("visita_nombre"));
                visitaGuiada.setNumClientesMax(rs.getInt("visita_num_clientes_max"));
                visitaGuiada.setDireccionPuntoPartida(rs.getString("visita_direccion_punto_partida"));
                visitaGuiada.setTematica(rs.getString("visita_tematica"));
                visitaGuiada.setLugar(rs.getString("visita_lugar"));
                visitaGuiada.setPrecio(rs.getDouble("visita_precio"));
                String fechaVisitaString = rs.getString("visita_fecha");
                Date fechaVisita = formatter.parse(fechaVisitaString);
                visitaGuiada.setFecha(fechaVisita);
                String timeInicioString = rs.getString("visita_hora_inicio");
                Time timeInicio = Time.valueOf(timeInicioString);
                visitaGuiada.setHoraInicio(timeInicio);
                String timeFinString = rs.getString("visita_hora_fin");
                Time timeFin = Time.valueOf(timeFinString);
                visitaGuiada.setHoraFin(timeFin);
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("visita_responsable_id"));
                visitaGuiada.setResponsable(empleado);
                visitaGuiada.setActivo(rs.getBoolean("visita_activo"));
                compraVisitaGuiada.setVisitaGuiada(visitaGuiada);

                comprasVisitasGuiadas.add(compraVisitaGuiada);

            }
        } catch (Exception e) {
            System.out.println("Error al mostrar los datos del listado de clientes de cada visita guiada");
            System.out.println(e.getMessage());
        }
        comprasVisitasGuiadas.forEach(System.out::println);
    }

    private static void listarEmpleadosDeLaAgencia(String baseDeDatos) {
        String SQL = "SELECT * FROM EMPLEADO";
        ResultSet rs = null;
        try {
            rs = BBDD.Generic.consultar(SQL, baseDeDatos);
            int contador = 1;
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("id"));
                empleado.setDni(rs.getString("dni"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                //String fecha nacimiento
                String fechaNaString = rs.getString("fecha_nacimiento");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNa = formatter.parse(fechaNaString);
                empleado.setFechaNacimiento(fechaNa);
                //String fecha contratacion
                String fechaConString = rs.getString("fecha_contratacion");
                Date fechaCon = formatter.parse(fechaConString);
                empleado.setFechaContratacion(fechaCon);
                empleado.setNacionalidad(rs.getString("nacionalidad"));
                empleado.setCargo(rs.getString("cargo"));
                empleado.setActivo(rs.getBoolean("activo"));
                System.out.println(contador + "- " + empleado.toString());
                contador++;
                ResultSet rs2 = null;
                String SQL2 = "SELECT * FROM VISITAGUIADA WHERE responsable_id = " + empleado.getId();
                rs2 = BBDD.Generic.consultar(SQL2, baseDeDatos);
                while (rs2.next()) {
                    VisitaGuiada visitaGuiada = new VisitaGuiada();
                    visitaGuiada.setId(rs2.getInt("id"));
                    visitaGuiada.setNombre(rs2.getString("nombre"));
                    visitaGuiada.setNumClientesMax(rs2.getInt("num_clientes_max"));
                    visitaGuiada.setDireccionPuntoPartida(rs2.getString("direccion_punto_partida"));
                    visitaGuiada.setTematica(rs2.getString("tematica"));
                    visitaGuiada.setLugar(rs2.getString("lugar"));
                    visitaGuiada.setPrecio(rs2.getDouble("precio"));
                    //String fecha visita
                    String fechaVisitaString = rs2.getString("fecha");
                    Date fechaVisita = formatter.parse(fechaVisitaString);
                    visitaGuiada.setFecha(fechaVisita);
                    //String hora inicio
                    String timeInicioString = rs2.getString("hora_inicio");
                    Time timeInicio = Time.valueOf(timeInicioString);
                    visitaGuiada.setHoraInicio(timeInicio);
                    //String hora fin
                    String timeFinString = rs2.getString("hora_fin");
                    Time timeFin = Time.valueOf(timeFinString);
                    visitaGuiada.setHoraFin(timeFin);
                    visitaGuiada.setResponsable(empleado);
                    visitaGuiada.setActivo(rs2.getBoolean("activo"));
                    System.out.println(visitaGuiada);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar los datos de los empleados");
            System.out.println(e.getMessage());
        }
    }

    private static void altaDeUnNuevoEmpleado(String baseDeDatos) {
        //Pedir datos
        Empleado empleado = new Empleado();
        Scanner sc = new Scanner(System.in);
        //DNI
        System.out.println("Introduce el dni del empleado:");
        String dni = sc.nextLine();
        //Comprobar DNI con regex
        while (!comprobarDNI(dni)) {
            System.out.println("El dni introducido no es correcto, vuelve a introducirlo:");
            dni = sc.nextLine();
        }
        empleado.setDni(dni);
        //Nombre
        System.out.println("Introduce el nombre del empleado:");
        String nombre = sc.nextLine();
        empleado.setNombre(nombre);
        //Apellido
        System.out.println("Introduce el apellido del empleado:");
        String apellido = sc.nextLine();
        empleado.setApellido(apellido);
        //Fecha nacimiento
        System.out.println("Introduce la fecha de nacimiento del empleado (yyyy-MM-dd):");
        String fechaNaString = sc.nextLine();
        while (!comprobarFecha(fechaNaString)) {
            System.out.println("La fecha introducida no es correcta, vuelve a introducirla (yyyy-MM-dd):");
            fechaNaString = sc.nextLine();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNa = null;
        try {
            fechaNa = formatter.parse(fechaNaString);
        } catch (ParseException e) {
            System.out.println("Error al convertir la fecha de nacimiento");
            System.out.println(e.getMessage());
        }
        empleado.setFechaNacimiento(fechaNa);
        //Fecha contratacion
        System.out.println("Introduce la fecha de contratacion del empleado (yyyy-MM-dd):");
        String fechaConString = sc.nextLine();
        while (!comprobarFecha(fechaConString)) {
            System.out.println("La fecha introducida no es correcta, vuelve a introducirla (yyyy-MM-dd):");
            fechaConString = sc.nextLine();
        }
        Date fechaCon = null;
        try {
            fechaCon = formatter.parse(fechaConString);
        } catch (ParseException e) {
            System.out.println("Error al convertir la fecha de contratacion");
            System.out.println(e.getMessage());
        }
        empleado.setFechaContratacion(fechaCon);
        //Nacionalidad
        System.out.println("Introduce la nacionalidad del empleado:");
        String nacionalidad = sc.nextLine();
        empleado.setNacionalidad(nacionalidad);
        //Cargo
        System.out.println("Introduce el cargo del empleado:");
        String cargo = sc.nextLine();
        empleado.setCargo(cargo);
        empleado.setActivo(true);

        //Ver cual es el id del ultimo empleado
        int id = 0;
        String SQL = "SELECT MAX(id) FROM EMPLEADO";
        ResultSet rs = BBDD.Generic.consultar(SQL, baseDeDatos);
        try {
            while (rs.next()) {
                id = rs.getInt("MAX(id)");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el id del ultimo empleado");
            System.out.println(e.getMessage());
        }

        empleado.setId(id + 1);

        //Insertar en BBDD
        String SQL2 = "INSERT INTO EMPLEADO (id, dni, nombre, apellido, fecha_nacimiento, fecha_contratacion, nacionalidad, cargo, activo) VALUES ("+empleado.getId()+", '" + empleado.getDni() + "', '" + empleado.getNombre() + "', '" + empleado.getApellido() + "', '" + formatter.format(empleado.getFechaNacimiento()) + "', '" + formatter.format(empleado.getFechaContratacion()) + "', '" + empleado.getNacionalidad() + "', '" + empleado.getCargo() + "', " + empleado.isActivo() + ")";
        BBDD.Generic.insertar(SQL2, baseDeDatos);
        System.out.println(empleado.toString());
    }

    private static boolean comprobarFecha(String fechaNaString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaNa = formatter.parse(fechaNaString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean comprobarDNI(String dni) {
        String regex = "^[0-9]{8}[A-Z]$";
        return dni.matches(regex);
    }

    private static void altaDeUnaNuevaVisitaGuiada(String baseDeDatos) {
        //Pedir los datos
        VisitaGuiada visitaGuiada = new VisitaGuiada();
        Scanner sc = new Scanner(System.in);
        //Nombre
        System.out.println("Introduce el nombre de la visita guiada:");
        String nombre = sc.nextLine();
        visitaGuiada.setNombre(nombre);
        //NumClientesMax
        System.out.println("Introduce el numero maximo de clientes de la visita guiada:");
        String numClientesMax = sc.nextLine();
        while (!comprobarNumero(numClientesMax)) {
            System.out.println("El numero de clientes maximo introducido no es correcto, vuelve a introducirlo:");
            numClientesMax = sc.nextLine();
        }
        visitaGuiada.setNumClientesMax(Integer.parseInt(numClientesMax));
        //Direccion
        System.out.println("Introduce la direccion de la visita guiada:");
        String direccion = sc.nextLine();
        visitaGuiada.setDireccionPuntoPartida(direccion);
        //Temática
        System.out.println("Introduce la tematica de la visita guiada:");
        String tematica = sc.nextLine();
        visitaGuiada.setTematica(tematica);
        //Lugar
        System.out.println("Introduce el lugar de la visita guiada:");
        String lugar = sc.nextLine();
        visitaGuiada.setLugar(lugar);
        //Precio
        System.out.println("Introduce el precio de la visita guiada:");
        String precio = sc.nextLine();
        while (!comprobarNumeroDecimal(precio)) {
            System.out.println("El precio introducido no es correcto, vuelve a introducirlo:");
            precio = sc.nextLine();
        }
        visitaGuiada.setPrecio(Float.parseFloat(precio));
        //Fecha
        System.out.println("Introduce la fecha de la visita guiada (yyyy-MM-dd):");
        String fechaString = sc.nextLine();
        while (!comprobarFecha(fechaString)) {
            System.out.println("La fecha introducida no es correcta, vuelve a introducirla (yyyy-MM-dd):");
            fechaString = sc.nextLine();
        }
        Date fecha = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fecha = formatter.parse(fechaString);
        } catch (ParseException e) {
            System.out.println("Error al convertir la fecha de la visita guiada");
            System.out.println(e.getMessage());
        }
        visitaGuiada.setFecha(fecha);
        //Hora Inicio
        System.out.println("Introduce la hora de inicio de la visita guiada (hh:mm):");
        String horaInicioString = sc.nextLine();
        while (!comprobarHora(horaInicioString)) {
            System.out.println("La hora de inicio introducida no es correcta, vuelve a introducirla (hh:mm):");
            horaInicioString = sc.nextLine();
        }
        Time horaInicio = null;
        SimpleDateFormat formatter2 = new SimpleDateFormat("hh:mm");
        try {
            horaInicio = new Time(formatter2.parse(horaInicioString).getTime());
        } catch (ParseException e) {
            System.out.println("Error al convertir la hora de inicio de la visita guiada");
            System.out.println(e.getMessage());
        }
        visitaGuiada.setHoraInicio(horaInicio);
        //Hora Fin
        System.out.println("Introduce la hora de fin de la visita guiada (hh:mm):");
        String horaFinString = sc.nextLine();
        while (!comprobarHora(horaFinString)) {
            System.out.println("La hora de fin introducida no es correcta, vuelve a introducirla (hh:mm):");
            horaFinString = sc.nextLine();
        }
        Time horaFin = null;
        SimpleDateFormat formatter3 = new SimpleDateFormat("hh:mm");
        try {
            horaFin = new Time(formatter3.parse(horaFinString).getTime());
        } catch (ParseException e) {
            System.out.println("Error al convertir la hora de fin de la visita guiada");
            System.out.println(e.getMessage());
        }
        visitaGuiada.setHoraFin(horaFin);
        //Responsable
        boolean encontrado = false;
        while (!encontrado){
            System.out.println("Selecciona el id del responsable de la visita guiada:");
            ArrayList<Empleado> empleados = new ArrayList<>();
            ResultSet rs = BBDD.Generic.consultar("SELECT * FROM EMPLEADO", baseDeDatos);
            try {
                while (rs.next()) {
                    Empleado empleado = new Empleado();
                    empleado.setId(rs.getInt("id"));
                    empleado.setDni(rs.getString("dni"));
                    empleado.setNombre(rs.getString("nombre"));
                    empleado.setApellido(rs.getString("apellido"));
                    String fechaNacimientoString = rs.getString("fecha_nacimiento");
                    Date fechaNacimiento = null;
                    SimpleDateFormat formatter4 = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        fechaNacimiento = formatter4.parse(fechaNacimientoString);
                    } catch (ParseException e) {
                        System.out.println("Error al convertir la fecha de nacimiento del empleado");
                        System.out.println(e.getMessage());
                    }
                    empleado.setFechaNacimiento(fechaNacimiento);
                    String fechaContratacionString = rs.getString("fecha_contratacion");
                    Date fechaContratacion = null;
                    SimpleDateFormat formatter5 = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        fechaContratacion = formatter5.parse(fechaContratacionString);
                    } catch (ParseException e) {
                        System.out.println("Error al convertir la fecha de contratacion del empleado");
                        System.out.println(e.getMessage());
                    }
                    empleado.setFechaContratacion(fechaContratacion);
                    empleado.setNacionalidad(rs.getString("nacionalidad"));
                    empleado.setCargo(rs.getString("cargo"));
                    empleado.setActivo(rs.getBoolean("activo"));
                    empleados.add(empleado);
                }
            } catch (SQLException e) {
                System.out.println("Error al consultar los empleados");
                System.out.println(e.getMessage());
            }
            empleados.forEach(empleado -> System.out.println(empleado.getId() + " - " + empleado.getNombre() + " " + empleado.getApellido()));
            String idResponsable = sc.nextLine();
            while (!comprobarNumero(idResponsable)) {
                System.out.println("El id introducido no es correcto, vuelve a introducirlo:");
                idResponsable = sc.nextLine();
            }

            for (Empleado empleado : empleados) {
                if (empleado.getId() == Integer.parseInt(idResponsable)) {
                    visitaGuiada.setResponsable(empleado);
                    encontrado = true;
                }
            }
        }
        //Marcar como activa la visita guiada
        visitaGuiada.setActivo(true);

        //Último id de visitaguiada
        int id = 0;
        String select = "SELECT max(id) FROM VISITAGUIADA";
        ResultSet rs = BBDD.Generic.consultar(select, baseDeDatos);
        try {
            while (rs.next()) {
                id = rs.getInt("max(id)");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar el último id de visitaguiada");
            System.out.println(e.getMessage());
        }
        visitaGuiada.setId(id + 1);

        //Insertar visita guiada
        String SQL = "INSERT INTO VISITAGUIADA VALUES (" + visitaGuiada.getId() + ", '" + visitaGuiada.getNombre() + "', "
                + visitaGuiada.getNumClientesMax() + ", '" + visitaGuiada.getDireccionPuntoPartida() + "', '" + visitaGuiada.getTematica() + "', '"
                + visitaGuiada.getLugar() + "', " + visitaGuiada.getPrecio() + ", '" + formatter.format(visitaGuiada.getFecha()) + "', '" +
                formatter2.format(visitaGuiada.getHoraInicio()) + "', '" + formatter2.format(visitaGuiada.getHoraFin()) + "', " + visitaGuiada.getResponsable().getId() + ", " + visitaGuiada.isActivo() + ")";

        BBDD.Generic.insertar(SQL, baseDeDatos);
        System.out.println(visitaGuiada.toString());
    }

    private static boolean comprobarHora(String horaInicioString) {
        // Time SQL format check
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            sdf.setLenient(false);
            sdf.parse(horaInicioString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static boolean comprobarNumeroDecimal(String precio) {
        String regex = "^[0-9]+([.][0-9]+)?$";
        return precio.matches(regex);
    }

    private static boolean comprobarNumero(String numClientesMax) {
        String regex = "^[0-9]+$";
        return numClientesMax.matches(regex);
    }

    private static void altaDeUnNuevoCliente(String baseDeDatos) {
        //Crear cliente
        Cliente cliente = new Cliente();
        Scanner sc = new Scanner(System.in);

        //DNI
        System.out.println("Introduce el DNI del cliente:");
        String dni = sc.nextLine();
        while (!comprobarDNI(dni)) {
            System.out.println("El DNI introducido no es correcto, vuelve a introducirlo:");
            dni = sc.nextLine();
        }
        cliente.setDni(dni);
        //nombre
        System.out.println("Introduce el nombre del cliente:");
        String nombre = sc.nextLine();
        cliente.setNombre(nombre);
        //apellidos
        System.out.println("Introduce los apellidos del cliente:");
        String apellidos = sc.nextLine();
        cliente.setApellidos(apellidos);
        //edad
        System.out.println("Introduce la edad del cliente:");
        String edad = sc.nextLine();
        while (!comprobarNumero(edad)) {
            System.out.println("La edad introducida no es correcta, vuelve a introducirla:");
            edad = sc.nextLine();
        }
        cliente.setEdad(Integer.parseInt(edad));
        //profesion
        System.out.println("Introduce la profesión del cliente:");
        String profesion = sc.nextLine();
        cliente.setProfesion(profesion);
        //activo
        cliente.setActivo(true);

        //Último id de cliente
        int id = 0;
        String select = "SELECT max(id) FROM CLIENTE";
        ResultSet rs = BBDD.Generic.consultar(select, baseDeDatos);
        try {
            while (rs.next()) {
                id = rs.getInt("max(id)");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar el último id de cliente");
            System.out.println(e.getMessage());
        }
        cliente.setId(id + 1);

        //Insertar cliente
        String SQL = "INSERT INTO CLIENTE VALUES (" + cliente.getId() + ", '" + cliente.getDni() + "', '" + cliente.getNombre() + "', '" + cliente.getApellidos() + "', " + cliente.getEdad() + ", '" + cliente.getProfesion() + "', " + cliente.isActivo() + ")";
        BBDD.Generic.insertar(SQL, baseDeDatos);
        System.out.println(cliente.toString());
    }

    private static void bajaDeUnaVisitaGuiada(String baseDeDatos) {
        String select = "SELECT * FROM VISITAGUIADA";
        ResultSet rs = BBDD.Generic.consultar(select, baseDeDatos);
        ArrayList<VisitaGuiada> visitasGuiadas = new ArrayList<>();
        try {
            while (rs.next()) {
                VisitaGuiada visitaGuiada = new VisitaGuiada();
                visitaGuiada.setId(rs.getInt("id"));
                visitaGuiada.setNombre(rs.getString("nombre"));
                visitaGuiada.setNumClientesMax(rs.getInt("num_clientes_max"));
                visitaGuiada.setDireccionPuntoPartida(rs.getString("direccion_punto_partida"));
                visitaGuiada.setTematica(rs.getString("tematica"));
                visitaGuiada.setLugar(rs.getString("lugar"));
                visitaGuiada.setPrecio(rs.getDouble("precio"));
                String fechaString = rs.getString("fecha");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(fechaString);
                visitaGuiada.setFecha(date);
                String horaString = rs.getString("hora_inicio");
                SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
                Date hora = formatter2.parse(horaString);
                Time timeInicio = new Time(hora.getTime());
                visitaGuiada.setHoraInicio(timeInicio);
                String horaFinString = rs.getString("hora_fin");
                SimpleDateFormat formatterHoraFin = new SimpleDateFormat("HH:mm:ss");
                Date horaFin = formatterHoraFin.parse(horaFinString);
                Time timeFin = new Time(horaFin.getTime());
                visitaGuiada.setHoraFin(timeFin);
                visitaGuiada.setActivo(rs.getBoolean("activo"));
                visitasGuiadas.add(visitaGuiada);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar las visitas guiadas");
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Visitas guiadas, selecciona la que quieres borrar o eliminar:");
        visitasGuiadas.forEach(System.out::println);
        boolean existe = false;
        int idInt = 0;
        while (!existe){
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();
            while (!comprobarNumero(id)) {
                System.out.println("El id introducido no es correcto, vuelve a introducirlo:");
                id = sc.nextLine();
            }
            idInt = Integer.parseInt(id);
            for (VisitaGuiada visitaGuiada : visitasGuiadas) {
                if (visitaGuiada.getId() == idInt) {
                    existe = true;
                }
            }
        }
        String SQL = "DELETE FROM VISITAGUIADA WHERE id = " + idInt;
        BBDD.Generic.eliminar(SQL, baseDeDatos);
    }

    private static void bajaDeUnEmpleado(String baseDeDatos) {
        String select = "SELECT * FROM EMPLEADO";
        ResultSet rs = BBDD.Generic.consultar(select, baseDeDatos);
        ArrayList<Empleado> empleados = new ArrayList<>();
        try {
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("id"));
                empleado.setDni(rs.getString("dni"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                //String fecha nacimiento
                String fechaNaString = rs.getString("fecha_nacimiento");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNa = formatter.parse(fechaNaString);
                empleado.setFechaNacimiento(fechaNa);
                //String fecha contratacion
                String fechaConString = rs.getString("fecha_contratacion");
                Date fechaCon = formatter.parse(fechaConString);
                empleado.setFechaContratacion(fechaCon);
                empleado.setNacionalidad(rs.getString("nacionalidad"));
                empleado.setCargo(rs.getString("cargo"));
                empleado.setActivo(rs.getBoolean("activo"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar los empleados");
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Empleados, selecciona el que quieres borrar o eliminar:");
        empleados.forEach(System.out::println);
        boolean existe = false;
        int idInt = 0;
        while (!existe){
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();
            while (!comprobarNumero(id)) {
                System.out.println("El id introducido no es correcto, vuelve a introducirlo:");
                id = sc.nextLine();
            }
            idInt = Integer.parseInt(id);
            for (Empleado empleado : empleados) {
                if (empleado.getId() == idInt) {
                    existe = true;
                }
            }
        }
        String SQL = "DELETE FROM EMPLEADO WHERE id = " + idInt;
        BBDD.Generic.eliminar(SQL, baseDeDatos);
    }

    private static void bajaDeUnCliente(String baseDeDatos) {
        String select = "SELECT * FROM CLIENTE";
        ResultSet rs = BBDD.Generic.consultar(select, baseDeDatos);
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setEdad(rs.getInt("edad"));
                cliente.setProfesion(rs.getString("profesion"));
                cliente.setActivo(rs.getBoolean("activo"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar los clientes");
            System.out.println(e.getMessage());
        }
        System.out.println("Clientes, selecciona el que quieres borrar o eliminar:");
        clientes.forEach(System.out::println);
        boolean existe = false;
        int idInt = 0;
        while (!existe){
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();
            while (!comprobarNumero(id)) {
                System.out.println("El id introducido no es correcto, vuelve a introducirlo:");
                id = sc.nextLine();
            }
            idInt = Integer.parseInt(id);
            for (Cliente cliente : clientes) {
                if (cliente.getId() == idInt) {
                    existe = true;
                }
            }
        }
        String SQL = "DELETE FROM CLIENTE WHERE id = " + idInt;
        BBDD.Generic.eliminar(SQL, baseDeDatos);

    }

    private static void modificarDatosDeUnCliente(String baseDeDatos) {
        String select = "SELECT * FROM CLIENTE";
        ResultSet rs = BBDD.Generic.consultar(select, baseDeDatos);
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setEdad(rs.getInt("edad"));
                cliente.setProfesion(rs.getString("profesion"));
                cliente.setActivo(rs.getBoolean("activo"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar los clientes");
            System.out.println(e.getMessage());
        }
        System.out.println("Clientes, selecciona el que quieres modificar:");
        clientes.forEach(System.out::println);
        boolean existe = false;
        int idInt = 0;
        while (!existe){
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();
            while (!comprobarNumero(id)) {
                System.out.println("El id introducido no es correcto, vuelve a introducirlo:");
                id = sc.nextLine();
            }
            idInt = Integer.parseInt(id);
            for (Cliente cliente : clientes) {
                if (cliente.getId() == idInt) {
                    existe = true;
                }
            }
        }
        int opcion = 0;
        while (opcion != 7){
            //Ir de uno en uno preguntando que se quiere modificar
            System.out.println("¿Qué quieres modificar?");
            System.out.println("1. DNI");
            System.out.println("2. Nombre");
            System.out.println("3. Apellidos");
            System.out.println("4. Edad");
            System.out.println("5. Profesión");
            System.out.println("7. Salir");
            Scanner sc = new Scanner(System.in);
            String opcionString = sc.nextLine();
            while (!comprobarNumero(opcionString)) {
                System.out.println("La opción introducida no es correcta, vuelve a introducirlo:");
                opcionString = sc.nextLine();
            }
            opcion = Integer.parseInt(opcionString);
            switch (opcion){
                case 1:
                    System.out.println("Introduce el nuevo DNI:");
                    String dni = sc.nextLine();
                    while (!comprobarDNI(dni)) {
                        System.out.println("El DNI introducido no es correcto, vuelve a introducirlo:");
                        dni = sc.nextLine();
                    }
                    String SQL = "UPDATE CLIENTE SET dni = '" + dni + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 2:
                    System.out.println("Introduce el nuevo nombre:");
                    String nombre = sc.nextLine();
                    SQL = "UPDATE CLIENTE SET nombre = '" + nombre + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 3:
                    System.out.println("Introduce los nuevos apellidos:");
                    String apellidos = sc.nextLine();
                    SQL = "UPDATE CLIENTE SET apellidos = '" + apellidos + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 4:
                    System.out.println("Introduce la nueva edad:");
                    String edad = sc.nextLine();
                    while (!comprobarNumero(edad)) {
                        System.out.println("La edad introducida no es correcta, vuelve a introducirlo:");
                        edad = sc.nextLine();
                    }
                    SQL = "UPDATE CLIENTE SET edad = " + edad + " WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 5:
                    System.out.println("Introduce la nueva profesión:");
                    String profesion = sc.nextLine();
                    SQL = "UPDATE CLIENTE SET profesion = '" + profesion + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("La opción introducida no es correcta, vuelve a introducirlo:");
                    break;
            }
        }

    }

    private static void modificarDatosDeUnaVisitaGuiada(String baseDeDatos) {
        String select = "SELECT * FROM VISITAGUIADA";
        ResultSet rs = BBDD.Generic.consultar(select, baseDeDatos);
        ArrayList<VisitaGuiada> visitasGuiadas = new ArrayList<>();
        try {
            while (rs.next()) {
                VisitaGuiada visitaGuiada = new VisitaGuiada();
                visitaGuiada.setId(rs.getInt("id"));
                visitaGuiada.setNombre(rs.getString("nombre"));
                visitaGuiada.setNumClientesMax(rs.getInt("num_clientes_max"));
                visitaGuiada.setDireccionPuntoPartida(rs.getString("direccion_punto_partida"));
                visitaGuiada.setTematica(rs.getString("tematica"));
                visitaGuiada.setLugar(rs.getString("lugar"));
                visitaGuiada.setPrecio(rs.getDouble("precio"));
                String fechaString = rs.getString("fecha");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(fechaString);
                visitaGuiada.setFecha(date);
                String horaString = rs.getString("hora_inicio");
                SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
                Date hora = formatter2.parse(horaString);
                Time timeInicio = new Time(hora.getTime());
                visitaGuiada.setHoraInicio(timeInicio);
                String horaFinString = rs.getString("hora_fin");
                SimpleDateFormat formatterHoraFin = new SimpleDateFormat("HH:mm:ss");
                Date horaFin = formatterHoraFin.parse(horaFinString);
                Time timeFin = new Time(horaFin.getTime());
                visitaGuiada.setHoraFin(timeFin);
                visitaGuiada.setActivo(rs.getBoolean("activo"));
                visitasGuiadas.add(visitaGuiada);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar las visitas guiadas");
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Visitas guiadas, selecciona la que quieres modificar:");
        visitasGuiadas.forEach(System.out::println);
        boolean existe = false;
        int idInt = 0;
        while (!existe){
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();
            while (!comprobarNumero(id)) {
                System.out.println("El id introducido no es correcto, vuelve a introducirlo:");
                id = sc.nextLine();
            }
            idInt = Integer.parseInt(id);
            for (VisitaGuiada visitaGuiada : visitasGuiadas) {
                if (visitaGuiada.getId() == idInt) {
                    existe = true;
                }
            }
        }
        int opcion = 0;
        while (opcion != 11) {
            System.out.println("¿Qué quieres modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Número de clientes máximos");
            System.out.println("3. Dirección del punto de partida");
            System.out.println("4. Temática");
            System.out.println("5. Lugar");
            System.out.println("6. Precio");
            System.out.println("7. Fecha");
            System.out.println("8. Hora de inicio");
            System.out.println("9. Hora de fin");
            System.out.println("10. Elegir responsable");
            System.out.println("11. Salir");
            Scanner sc = new Scanner(System.in);
            String opcionString = sc.nextLine();
            while (!comprobarNumero(opcionString)) {
                System.out.println("La opción introducida no es correcta, vuelve a introducirlo:");
                opcionString = sc.nextLine();
            }
            opcion = Integer.parseInt(opcionString);
            switch (opcion){
                case 1:
                    System.out.println("Introduce el nuevo nombre:");
                    String nombre = sc.nextLine();
                    String SQL = "UPDATE VISITAGUIADA SET nombre = '" + nombre + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 2:
                    System.out.println("Introduce el nuevo número de clientes máximos:");
                    String numClientesMax = sc.nextLine();
                    while (!comprobarNumero(numClientesMax)) {
                        System.out.println("El número de clientes máximos introducido no es correcto, vuelve a introducirlo:");
                        numClientesMax = sc.nextLine();
                    }
                    SQL = "UPDATE VISITAGUIADA SET num_clientes_max = " + numClientesMax + " WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 3:
                    System.out.println("Introduce la nueva dirección del punto de partida:");
                    String direccionPuntoPartida = sc.nextLine();
                    SQL = "UPDATE VISITAGUIADA SET direccion_punto_partida = '" + direccionPuntoPartida + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 4:
                    System.out.println("Introduce la nueva temática de la visita guiada:");
                    String tematica = sc.nextLine();
                    SQL = "UPDATE VISITAGUIADA SET tematica = '" + tematica + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 5:
                    System.out.println("Introduce el nuevo lugar de la visita guiada:");
                    String lugar = sc.nextLine();
                    SQL = "UPDATE VISITAGUIADA SET lugar = '" + lugar + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 6:
                    System.out.println("Introduce el nuevo precio de la visita guiada:");
                    String precio = sc.nextLine();
                    while (!comprobarNumeroDecimal(precio)) {
                        System.out.println("El precio introducido no es correcto, vuelve a introducirlo:");
                        precio = sc.nextLine();
                    }
                    Double precioDouble = Double.parseDouble(precio);
                    SQL = "UPDATE VISITAGUIADA SET precio = " + precioDouble + " WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 7:
                    System.out.println("Introduce la nueva fecha de la visita guiada (yyyy-mm-dd):");
                    String fecha = sc.nextLine();
                    while (!comprobarFecha(fecha)) {
                        System.out.println("La fecha introducida no es correcta, vuelve a introducirlo:");
                        fecha = sc.nextLine();
                    }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaDate = null;
                    try {
                        fechaDate = formatter.parse(fecha);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                    SQL = "UPDATE VISITAGUIADA SET fecha = '" + formatter.format(fechaDate) + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 8:
                    System.out.println("Introduce la nueva hora de inicio de la visita guiada (HH:mm:ss):");
                    String horaInicio = sc.nextLine();
                    while (!comprobarHora(horaInicio)) {
                        System.out.println("La hora de inicio introducida no es correcta, vuelve a introducirlo (HH:mm:ss):");
                        horaInicio = sc.nextLine();
                    }
                    //To time
                    SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
                    Time timeInicio = null;
                    try {
                        timeInicio = new Time(formatter2.parse(horaInicio).getTime());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                    SQL = "UPDATE VISITAGUIADA SET hora_inicio = '" + formatter2.format(timeInicio) + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 9:
                    System.out.println("Introduce la nueva hora de fin de la visita guiada:");
                    String horaFin = sc.nextLine();
                    while (!comprobarHora(horaFin)) {
                        System.out.println("La hora de fin introducida no es correcta, vuelve a introducirlo:");
                        horaFin = sc.nextLine();
                    }
                    //Parse to time from string
                    SimpleDateFormat formatter3 = new SimpleDateFormat("HH:mm:ss");

                    Time timeFin = null;
                    try {
                        timeFin = new Time(formatter3.parse(horaFin).getTime());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                    SQL = "UPDATE VISITAGUIADA SET hora_fin = '" + formatter3.format(timeFin) + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 10:
                    System.out.println("Introduce el nuevo responsable de la visita guiada:");
                    String seleccionarResponsable = "SELECT * FROM EMPLEADO";
                    ResultSet rs2 = BBDD.Generic.consultar(seleccionarResponsable, baseDeDatos);
                    ArrayList<Empleado> empleados = new ArrayList<>();
                    try {
                        while (rs2.next()) {
                            Empleado empleado = new Empleado();
                            empleado.setId(rs2.getInt("id"));
                            empleado.setDni(rs2.getString("dni"));
                            empleado.setNombre(rs2.getString("nombre"));
                            empleado.setApellido(rs2.getString("apellido"));
                            //String fecha nacimiento
                            String fechaNaString = rs2.getString("fecha_nacimiento");
                            formatter = new SimpleDateFormat("yyyy-MM-dd");
                            Date fechaNa = formatter.parse(fechaNaString);
                            empleado.setFechaNacimiento(fechaNa);
                            //String fecha contratacion
                            String fechaConString = rs2.getString("fecha_contratacion");
                            Date fechaCon = formatter.parse(fechaConString);
                            empleado.setFechaContratacion(fechaCon);
                            empleado.setNacionalidad(rs2.getString("nacionalidad"));
                            empleado.setCargo(rs2.getString("cargo"));
                            empleado.setActivo(rs2.getBoolean("activo"));
                            empleados.add(empleado);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    int idResposanbleInt = 0;
                    boolean existeEmpleado = false;
                    while (!existeEmpleado){
                        for (Empleado empleado : empleados) {
                            System.out.println(empleado);
                        }
                        System.out.println("Introduce el id del empleado que quieres que sea el responsable de la visita guiada:");
                        String idResponsable = sc.nextLine();
                        while (!comprobarNumero(idResponsable)) {
                            System.out.println("El id introducido no es correcto, vuelve a introducirlo:");
                            idResponsable = sc.nextLine();
                        }
                        idResposanbleInt = Integer.parseInt(idResponsable);
                        //Comprobar que existe el empleado
                        for (Empleado empleado : empleados) {
                            if (empleado.getId() == idResposanbleInt){
                                existeEmpleado = true;
                                break;
                            }
                        }
                    }

                    SQL = "UPDATE VISITAGUIADA SET responsable_id = " + idResposanbleInt + " WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 11:
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
        }
    }

    private static void modificarDatosDeUnEmpleado(String baseDeDatos) {
        String select = "SELECT * FROM EMPLEADO";
        ResultSet rs = BBDD.Generic.consultar(select, baseDeDatos);
        ArrayList<Empleado> empleados = new ArrayList<>();
        try {
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("id"));
                empleado.setDni(rs.getString("dni"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                //String fecha nacimiento
                String fechaNaString = rs.getString("fecha_nacimiento");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNa = formatter.parse(fechaNaString);
                empleado.setFechaNacimiento(fechaNa);
                //String fecha contratacion
                String fechaConString = rs.getString("fecha_contratacion");
                Date fechaCon = formatter.parse(fechaConString);
                empleado.setFechaContratacion(fechaCon);
                empleado.setNacionalidad(rs.getString("nacionalidad"));
                empleado.setCargo(rs.getString("cargo"));
                empleado.setActivo(rs.getBoolean("activo"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        empleados.forEach(System.out::println);
        System.out.println("Introduce el id del empleado que quieres modificar:");
        int idInt = 0;
        boolean existeEmpleado = false;
        while (!existeEmpleado) {
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();
            while (!comprobarNumero(id)) {
                System.out.println("El id introducido no es correcto, vuelve a introducirlo:");
                id = sc.nextLine();
            }
            idInt = Integer.parseInt(id);
            //Comprobar que existe el empleado
            for (Empleado empleado : empleados) {
                if (empleado.getId() == idInt) {
                    existeEmpleado = true;
                    break;
                }
            }
        }
        int opcion = 0;
        while (opcion != 8) {
            System.out.println("¿Qué quieres modificar del empleado con id " + idInt + "?");
            System.out.println("1. DNI");
            System.out.println("2. Nombre");
            System.out.println("3. Apellido");
            System.out.println("4. Fecha de nacimiento");
            System.out.println("5. Fecha de contratación");
            System.out.println("6. Nacionalidad");
            System.out.println("7. Cargo");
            System.out.println("8. Salir");
            Scanner sc = new Scanner(System.in);
            String opcionString = sc.nextLine();
            while (!comprobarNumero(opcionString)) {
                System.out.println("La opción introducida no es correcta, vuelve a introducirla:");
                opcionString = sc.nextLine();
            }
            opcion = Integer.parseInt(opcionString);
            switch (opcion) {
                case 1:
                    System.out.println("Introduce el nuevo DNI:");
                    String dni = sc.nextLine();
                    while (!comprobarDNI(dni)) {
                        System.out.println("El DNI introducido no es correcto, vuelve a introducirlo:");
                        dni = sc.nextLine();
                    }
                    String SQL = "UPDATE EMPLEADO SET dni = '" + dni + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 2:
                    System.out.println("Introduce el nuevo nombre:");
                    String nombre = sc.nextLine();
                    SQL = "UPDATE EMPLEADO SET nombre = '" + nombre + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 3:
                    System.out.println("Introduce el nuevo apellido:");
                    String apellido = sc.nextLine();
                    SQL = "UPDATE EMPLEADO SET apellido = '" + apellido + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 4:
                    System.out.println("Introduce la nueva fecha de nacimiento: (yyyy-mm-dd)");
                    String fechaNa = sc.nextLine();
                    while (!comprobarFecha(fechaNa)) {
                        System.out.println("La fecha introducida no es correcta, vuelve a introducirla:");
                        fechaNa = sc.nextLine();
                    }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaNaDate = null;
                    try {
                        fechaNaDate = formatter.parse(fechaNa);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    SQL = "UPDATE EMPLEADO SET fecha_nacimiento = '" + formatter.format(fechaNaDate) + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 5:
                    System.out.println("Introduce la nueva fecha de contratación: (yyyy-mm-dd)");
                    String fechaCon = sc.nextLine();
                    while (!comprobarFecha(fechaCon)) {
                        System.out.println("La fecha introducida no es correcta, vuelve a introducirla:");
                        fechaCon = sc.nextLine();
                    }
                    Date fechaConDate = null;
                    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        fechaConDate = formatter2.parse(fechaCon);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    SQL = "UPDATE EMPLEADO SET fecha_contratacion = '" + formatter2.format(fechaConDate) + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 6:
                    System.out.println("Introduce la nueva nacionalidad:");
                    String nacionalidad = sc.nextLine();
                    SQL = "UPDATE EMPLEADO SET nacionalidad = '" + nacionalidad + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 7:
                    System.out.println("Introduce el nuevo cargo:");
                    String cargo = sc.nextLine();
                    SQL = "UPDATE EMPLEADO SET cargo = '" + cargo + "' WHERE id = " + idInt;
                    BBDD.Generic.modificar(SQL, baseDeDatos);
                    break;
                case 8:
                    break;
                default:
                    System.out.println("La opción introducida no es correcta, vuelve a introducirla:");
                    break;
            }
        }
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
        System.out.println("2. "+nombreAgencia(2)+" - SQLite");
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