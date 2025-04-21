import java.util.Scanner;

public class TeatroMoroSimple {
    // Variables estáticas (Según indicaciones de paso 2)
    private static int entradasVendidas = 0;
    private static double ingresosTotales = 0;
    private static int reservasActivas = 0;
    
    // Variables de instancia (Según indicaciones de paso 2)
    private boolean[] asientos = new boolean[50]; // 50 asientos (simplificado)
    private String[] clientes = new String[50]; // Para guardar nombres
    private double[] precios = new double[50]; // Precios por asiento
    private boolean[] esReserva = new boolean[50]; // true=reserva, false=venta
    
    // Precio fijo
    private final double PRECIO_ENTRADA = 25.0;
    
    public static void main(String[] args) {
        TeatroMoroSimple teatro = new TeatroMoroSimple();
        teatro.iniciar();
    }
    
    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("\n=== TEATRO MORO ===");
            System.out.println("1. Reservar entrada");
            System.out.println("2. Comprar entrada");
            System.out.println("3. Modificar reserva/venta");
            System.out.println("4. Imprimir boleta");
            System.out.println("5. Salir");
            System.out.print("Seleccione: ");
            opcion = sc.nextInt();
            
            switch(opcion) {
                case 1: reservarEntrada(sc); break;
                case 2: comprarEntrada(sc); break;
                case 3: modificarVenta(sc); break;
                case 4: imprimirBoleta(sc); break;
                case 5: System.out.println("Gracias por usar el sistema"); break;
                default: System.out.println("Opción no válida");
            }
        } while(opcion != 5);
        sc.close();
    }
    
    private void reservarEntrada(Scanner sc) {
        System.out.println("\n--- RESERVAR ENTRADA ---");
        mostrarAsientos();
        
        System.out.print("Seleccione asiento (1-50): ");
        int numAsiento = sc.nextInt() - 1;
        sc.nextLine(); // Limpiar almacenamiento //BUFFER
        
        if(numAsiento < 0 || numAsiento >= 50) {
            System.out.println("Asiento no válido");
            return;
        }
        
        if(asientos[numAsiento]) {
            System.out.println("Asiento ocupado");
            return;
        }
        
        System.out.print("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        
        asientos[numAsiento] = true;
        clientes[numAsiento] = nombre;
        precios[numAsiento] = PRECIO_ENTRADA;
        esReserva[numAsiento] = true;
        reservasActivas++;
        
        System.out.println("Reserva exitosa! Asiento: " + (numAsiento+1));
    }
    
    private void comprarEntrada(Scanner sc) {
        System.out.println("\n--- COMPRAR ENTRADA ---");
        System.out.println("1. Nueva compra");
        System.out.println("2. Confirmar reserva");
        System.out.print("Seleccione: ");
        int op = sc.nextInt();
        sc.nextLine();
        
        if(op == 1) {
            mostrarAsientos();
            
            System.out.print("Seleccione asiento (1-50): ");
            int numAsiento = sc.nextInt() - 1;
            sc.nextLine();
            
            if(asientos[numAsiento]) {
                System.out.println("Asiento ocupado");
                return;
            }
            
            System.out.print("Ingrese su nombre: ");
            String nombre = sc.nextLine();
            
            asientos[numAsiento] = true;
            clientes[numAsiento] = nombre;
            precios[numAsiento] = PRECIO_ENTRADA;
            esReserva[numAsiento] = false;
            entradasVendidas++;
            ingresosTotales += PRECIO_ENTRADA;
            
            System.out.println("Compra exitosa!");
            imprimirBoleta(numAsiento);
        } else if(op == 2) {
            System.out.print("Ingrese número de asiento reservado: ");
            int numAsiento = sc.nextInt() - 1;
            sc.nextLine();
            
            if(!asientos[numAsiento] || !esReserva[numAsiento]) {
                System.out.println("No existe reserva para ese asiento");
                return;
            }
            
            esReserva[numAsiento] = false;
            reservasActivas--;
            entradasVendidas++;
            ingresosTotales += PRECIO_ENTRADA;
            
            System.out.println("Reserva confirmada!");
            imprimirBoleta(numAsiento);
        }
    }
    
    private void modificarVenta(Scanner sc) {
        System.out.println("\n--- MODIFICAR ---");
        System.out.print("Ingrese número de asiento: ");
        int numAsiento = sc.nextInt() - 1;
        sc.nextLine();
        
        if(!asientos[numAsiento]) {
            System.out.println("Asiento no ocupado");
            return;
        }
        
        System.out.println("1. Cambiar nombre");
        System.out.println("2. Cancelar reserva/venta");
        System.out.print("Seleccione: ");
        int op = sc.nextInt();
        sc.nextLine();
        
        if(op == 1) {
            System.out.print("Nuevo nombre: ");
            clientes[numAsiento] = sc.nextLine();
            System.out.println("Nombre actualizado");
        } else if(op == 2) {
            asientos[numAsiento] = false;
            if(esReserva[numAsiento]) {
                reservasActivas--;
            } else {
                entradasVendidas--;
                ingresosTotales -= PRECIO_ENTRADA;
            }
            System.out.println("Operación cancelada");
        }
    }
    
    private void imprimirBoleta(Scanner sc) {
        System.out.print("\nIngrese número de asiento: ");
        int numAsiento = sc.nextInt() - 1;
        imprimirBoleta(numAsiento);
    }
    
    private void imprimirBoleta(int numAsiento) {
        if(!asientos[numAsiento]) {
            System.out.println("Asiento disponible");
            return;
        }
        
        System.out.println("\n--- BOLETA TEATRO MORO ---");
        System.out.println("Asiento: " + (numAsiento+1));
        System.out.println("Cliente: " + clientes[numAsiento]);
        System.out.println("Tipo: " + (esReserva[numAsiento] ? "RESERVA" : "VENTA"));
        System.out.println("Precio: $" + precios[numAsiento]);
        System.out.println("--------------------------");
    }
    
    private void mostrarAsientos() {
        System.out.println("\nAsientos disponibles (X = ocupado):");
        for(int i = 0; i < 50; i++) {
            System.out.print((i+1) + ":" + (asientos[i] ? "X" : "O") + " ");
            if((i+1) % 10 == 0) System.out.println();
        }
    }
}