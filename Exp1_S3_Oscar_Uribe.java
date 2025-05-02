import java.util.*;

public class TeatroMoro {

    // Variables estáticas
    static int totalEntradasVendidas = 0;
    static double ingresosTotales = 0;
    static final String NOMBRE_TEATRO = "Teatro Moro";

    // Variables de instancia (listas)
    static List<String> ubicaciones = new ArrayList<>();
    static List<Double> costosFinales = new ArrayList<>();
    static List<String> descuentosAplicados = new ArrayList<>();

    // Scanner global
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- MENÚ TEATRO MORO ---");
            System.out.println("1. Venta de entradas");
            System.out.println("2. Visualizar resumen de ventas");
            System.out.println("3. Generar boleta");
            System.out.println("4. Calcular ingresos totales");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    ventaEntrada();
                    break;
                case 2:
                    resumenVentas();
                    break;
                case 3:
                    generarBoleta();
                    break;
                case 4:
                    mostrarIngresosTotales();
                    break;
                case 5:
                    System.out.println("Gracias por su compra.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    // Método venta
    public static void ventaEntrada() {
        System.out.println("\n--- Venta de Entrada ---");
        System.out.println("Ubicaciones disponibles: VIP ($100), Platea ($70), Balcón ($50)");
        System.out.print("Ingrese ubicación: ");
        String ubicacion = sc.nextLine();

        double costoBase = 0;
        switch (ubicacion.toLowerCase()) {
            case "vip":
                costoBase = 100;
                break;
            case "platea":
                costoBase = 70;
                break;
            case "balcón":
            case "balcon":
                costoBase = 50;
                break;
            default:
                System.out.println("Ubicación inválida.");
                return;
        }

        System.out.print("¿Es estudiante? (s/n): ");
        String estudiante = sc.nextLine();

        System.out.print("¿Es tercera edad? (s/n): ");
        String terceraEdad = sc.nextLine();

        double descuento = 0;
        String descuentoDesc = "Sin descuento";

        if (terceraEdad.equalsIgnoreCase("s")) {
            descuento = 0.15;
            descuentoDesc = "15%";
        } else if (estudiante.equalsIgnoreCase("s")) {
            descuento = 0.10;
            descuentoDesc = "10%";
        }

        double costoFinal = costoBase - (costoBase * descuento);

        // Guardar datos en listas
        ubicaciones.add(ubicacion.toUpperCase());
        costosFinales.add(costoFinal);
        descuentosAplicados.add(descuentoDesc);

        totalEntradasVendidas++;
        ingresosTotales += costoFinal;

        System.out.println("Entrada registrada exitosamente.");
    }

    public static void resumenVentas() {
        System.out.println("\n--- Resumen de Ventas ---");
        if (ubicaciones.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        for (int i = 0; i < ubicaciones.size(); i++) {
            System.out.println("Venta #" + (i + 1) + " | Ubicación: " + ubicaciones.get(i)
                    + " | Costo Final: $" + costosFinales.get(i)
                    + " | Descuento: " + descuentosAplicados.get(i));
        }
    }

    public static void generarBoleta() {
        System.out.println("\n--- Generar Boleta ---");
        if (ubicaciones.isEmpty()) {
            System.out.println("No hay ventas para generar boleta.");
            return;
        }

        for (int i = 0; i < ubicaciones.size(); i++) {
            String ubicacion = ubicaciones.get(i);
            double costoFinal = costosFinales.get(i);
            String descuento = descuentosAplicados.get(i);
            double costoBase = 0;

            switch (ubicacion.toLowerCase()) {
                case "vip":
                    costoBase = 100;
                    break;
                case "platea":
                    costoBase = 70;
                    break;
                case "balcón":
                case "balcon":
                    costoBase = 50;
                    break;
            }

            System.out.println("--------------------------------------------");
            System.out.println("               " + NOMBRE_TEATRO);
            System.out.println("--------------------------------------------");
            System.out.println("Ubicación: " + ubicacion);
            System.out.println("Costo Base: $" + costoBase);
            System.out.println("Descuento Aplicado: " + descuento);
            System.out.println("Costo Final: $" + costoFinal);
            System.out.println("--------------------------------------------");
            System.out.println("Gracias por su visita al " + NOMBRE_TEATRO);
            System.out.println("--------------------------------------------\n");
        }
    }

    public static void mostrarIngresosTotales() {
        System.out.println("\n--- Ingresos Totales ---");
        System.out.println("Entradas vendidas: " + totalEntradasVendidas);
        System.out.println("Ingresos totales: $" + ingresosTotales);
    }
}
