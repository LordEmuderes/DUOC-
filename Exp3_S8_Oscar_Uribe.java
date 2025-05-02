
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeatroMoro {

    final static int MAX_ENTRADAS = 100;

    static int[] idVentas = new int[MAX_ENTRADAS];
    static String[] ubicaciones = new String[MAX_ENTRADAS];
    static String[] nombresClientes = new String[MAX_ENTRADAS];
    static int[] edadesClientes = new int[MAX_ENTRADAS];
    static double[] preciosFinales = new double[MAX_ENTRADAS];

    static int contadorVentas = 0;

    static List<String> promociones = new ArrayList<>();
    static List<Reserva> reservas = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        promociones.add("Estudiante -10%");
        promociones.add("Tercera Edad -15%");

        int opcion;

        do {
            System.out.println("\n----- Teatro Moro -----");
            System.out.println("1. Vender Entrada");
            System.out.println("2. Ver Resumen de Ventas");
            System.out.println("3. Generar Boleta");
            System.out.println("4. Calcular Ingresos Totales");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    venderEntrada();
                    break;
                case 2:
                    mostrarResumen();
                    break;
                case 3:
                    generarBoleta();
                    break;
                case 4:
                    calcularIngresos();
                    break;
                case 5:
                    System.out.println("Gracias por su compra.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    public static void venderEntrada() {
        if (contadorVentas >= MAX_ENTRADAS) {
            System.out.println("No hay más entradas disponibles.");
            return;
        }

        System.out.print("Nombre del cliente: ");
        String nombre = sc.nextLine();

        System.out.print("Edad del cliente: ");
        int edad = sc.nextInt();
        sc.nextLine();

        if (!validarEdad(edad)) {
            System.out.println("Edad inválida.");
            return;
        }

        System.out.print("Ubicación (VIP/Platea/Balcón): ");
        String ubicacion = sc.nextLine();

        if (!validarUbicacion(ubicacion)) {
            System.out.println("Ubicación inválida.");
            return;
        }

        if (!asientoDisponible(ubicacion)) {
            System.out.println("Asiento no disponible.");
            return;
        }

        double precioBase = calcularPrecioBase(ubicacion);
        double descuento = aplicarDescuento(edad);
        double precioFinal = precioBase - (precioBase * descuento);

        idVentas[contadorVentas] = contadorVentas + 1;
        nombresClientes[contadorVentas] = nombre;
        edadesClientes[contadorVentas] = edad;
        ubicaciones[contadorVentas] = ubicacion;
        preciosFinales[contadorVentas] = precioFinal;

        reservas.add(new Reserva(contadorVentas + 1, contadorVentas + 100, ubicacion));

        contadorVentas++;
        System.out.println("Entrada vendida con éxito.");
    }

    public static void mostrarResumen() {
        System.out.println("\nResumen de Ventas:");
        for (int i = 0; i < contadorVentas; i++) {
            System.out.println("Venta #" + idVentas[i] + " - Cliente: " + nombresClientes[i] +
                               " - Ubicación: " + ubicaciones[i] +
                               " - Precio Final: $" + preciosFinales[i]);
        }
    }

    public static void generarBoleta() {
        System.out.print("Ingrese ID de venta para generar boleta: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < contadorVentas; i++) {
            if (idVentas[i] == id) {
                double precioBase = calcularPrecioBase(ubicaciones[i]);
                double descuentoAplicado = aplicarDescuento(edadesClientes[i]) * 100;
                System.out.println("----------------------------------------");
                System.out.println("              Teatro Moro               ");
                System.out.println("----------------------------------------");
                System.out.println("Ubicación: " + ubicaciones[i]);
                System.out.println("Costo Base: $" + precioBase);
                System.out.println("Descuento Aplicado: " + (int)descuentoAplicado + "%");
                System.out.println("Costo Final: $" + preciosFinales[i]);
                System.out.println("----------------------------------------");
                System.out.println("Gracias por su visita al Teatro Moro");
                System.out.println("----------------------------------------");
                return;
            }
        }
        System.out.println("ID de venta no encontrado.");
    }

    public static void calcularIngresos() {
        double total = 0;
        for (int i = 0; i < contadorVentas; i++) {
            total += preciosFinales[i];
        }
        System.out.println("Ingresos Totales: $" + total);
    }

    public static double calcularPrecioBase(String ubicacion) {
        switch (ubicacion.toUpperCase()) {
            case "VIP": return 100.0;
            case "PLATEA": return 70.0;
            case "BALCÓN": return 50.0;
            default: return 60.0;
        }
    }

    public static double aplicarDescuento(int edad) {
        if (edad >= 60) return 0.15;
        else if (edad >= 18 && edad <= 25) return 0.10;
        else return 0.0;
    }

    public static boolean validarEdad(int edad) {
        return edad >= 0 && edad <= 120;
    }

    public static boolean validarUbicacion(String ubicacion) {
        return ubicacion.equalsIgnoreCase("VIP") ||
               ubicacion.equalsIgnoreCase("Platea") ||
               ubicacion.equalsIgnoreCase("Balcón");
    }

    public static boolean asientoDisponible(String ubicacion) {
        for (int i = 0; i < contadorVentas; i++) {
            if (ubicaciones[i].equalsIgnoreCase(ubicacion)) {
                return true; // para simplificar, se permite múltiple por ubicación
            }
        }
        return true;
    }
}

class Reserva {
    int idReserva;
    int idCliente;
    String ubicacion;

    public Reserva(int idReserva, int idCliente, String ubicacion) {
        this.idReserva = idReserva;
        this.idCliente = idCliente;
        this.ubicacion = ubicacion;
    }
}
