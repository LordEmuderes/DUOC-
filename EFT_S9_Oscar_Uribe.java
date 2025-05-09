import java.util.*;

class Cliente {
    String nombre
    int edad;
    String genero
    boolean esEstudiante;

    public cliente(String nombre, int edad, String genero, boolean esEstudiante) {
this.nombre = nombre;
this.edad = edad;
this.genero = genero.toLowerCase();
this.estudiante = estudiante;
}

public double obtenerDescuento () {
    double descuento = 0;
    if(< 12) descuento = 0.10;
    if (genero.equals("mujer") && descuento < 0.20) descuento = 0.20;
    if (estudiante && descuento < 0.15) descuento = 0.15;
    if (edad >= 60 && descuento < 0.25) descuento = 0.25;
}

 }

 class asiento {
    String seccion;
    int numero;
    boolean disponible = true;

    public asiento(String seccion, int numero) {
        this.seccion = seccion;
        this.numero = numero;

    }
    
    public void asignar() {
        disponible = false;
    }

    public string toString() {
        return seccion + "#" + numero +(disponible ? "(Libre)" : "(Ocupado)");
    }

    }

    class  Entrada {
        Cliente cliente;
        Asiento asiento;
        double precioBase;
        double precioFinal;
    }
 
    public Entrada(Cliente cliente, Asiento asiento, double precioBase){
        this.cliente = cliente;
        this.asiento = asiento;
        this.precioBase = precioBase;
        double descuento = cliente.obtenerDescuento();
        this.precioFinal = precioBase - (precioBase * descuento);
        asiento.asignar(;)
    }

    import java.util.*;

class Cliente {
    String nombre;
    int edad;
    String genero;
    boolean esEstudiante;

    public Cliente(String nombre, int edad, String genero, boolean esEstudiante) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero.toLowerCase();
        this.esEstudiante = esEstudiante;
    }

    public double obtenerDescuento() {
        double descuento = 0;
        if (edad < 12) descuento = 0.10;
        if (genero.equals("mujer") && descuento < 0.20) descuento = 0.20;
        if (esEstudiante && descuento < 0.15) descuento = 0.15;
        if (edad >= 60 && descuento < 0.25) descuento = 0.25;
        return descuento;
    }
}

class Asiento {
    String seccion;
    int numero;
    boolean disponible = true;

    public Asiento(String seccion, int numero) {
        this.seccion = seccion;
        this.numero = numero;
    }

    public void asignar() {
        disponible = false;
    }

    public String toString() {
        return seccion + " #" + numero + (disponible ? " (Libre)" : " (Ocupado)");
    }
}

class Entrada {
    Cliente cliente;
    Asiento asiento;
    double precioBase;
    double precioFinal;

    public Entrada(Cliente cliente, Asiento asiento, double precioBase) {
        this.cliente = cliente;
        this.asiento = asiento;
        this.precioBase = precioBase;
        double descuento = cliente.obtenerDescuento();
        this.precioFinal = precioBase - (precioBase * descuento);
        asiento.asignar();
    }

    public void imprimirBoleta() {
        System.out.println("\n---- BOLETA ----");
        System.out.println("Nombre: " + cliente.nombre);
        System.out.println("Edad: " + cliente.edad);
        System.out.println("Género: " + cliente.genero);
        System.out.println("Sección: " + asiento.seccion);
        System.out.println("Número de Asiento: " + asiento.numero);
        System.out.println("Precio Final: $" + precioFinal);
        System.out.println("----------------\n");
    }
}

public class TeatroMoroApp {
    static Scanner scanner = new Scanner(System.in);
    static List<Asiento> asientos = new ArrayList<>();
    static List<Entrada> entradasVendidas = new ArrayList<>();

    public static void main(String[] args) {
        inicializarAsientos();

        while (true) {
            System.out.println("Bienvenido al sistema del Teatro Moro");

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Edad: ");
            int edad = Integer.parseInt(scanner.nextLine());

            System.out.print("Género (hombre/mujer): ");
            String genero = scanner.nextLine();

            System.out.print("¿Es estudiante? (true/false): ");
            boolean esEstudiante = Boolean.parseBoolean(scanner.nextLine());

            Cliente cliente = new Cliente(nombre, edad, genero, esEstudiante);

            mostrarAsientosDisponibles();
            System.out.print("Ingrese sección (vip, palco, platea baja, platea alta, galería): ");
            String seccion = scanner.nextLine().toLowerCase();

            Asiento asientoSeleccionado = seleccionarAsiento(seccion);
            if (asientoSeleccionado == null) {
                System.out.println("No hay asientos disponibles en esa sección.");
                continue;
            }

            double precioBase = obtenerPrecioBase(seccion);
            Entrada entrada = new Entrada(cliente, asientoSeleccionado, precioBase);
            entradasVendidas.add(entrada);

            entrada.imprimirBoleta();

            System.out.print("¿Desea realizar otra compra? (s/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("s")) break;
        }
    }

    static void inicializarAsientos() {
        String[] secciones = {"vip", "palco", "platea baja", "platea alta", "galería"};
        for (String seccion : secciones) {
            for (int i = 1; i <= 5; i++) {
                asientos.add(new Asiento(seccion, i));
            }
        }
    }

    static void mostrarAsientosDisponibles() {
        System.out.println("\nAsientos disponibles:");
        for (Asiento asiento : asientos) {
            if (asiento.disponible) {
                System.out.println(asiento);
            }
        }
        System.out.println();
    }

    static Asiento seleccionarAsiento(String seccionDeseada) {
        for (Asiento asiento : asientos) {
            if (asiento.seccion.equals(seccionDeseada) && asiento.disponible) {
                return asiento;
            }
        }
        return null;
    }

    static double obtenerPrecioBase(String seccion) {
        switch (seccion) {
            case "vip": return 20000;
            case "palco": return 15000;
            case "platea baja": return 12000;
            case "platea alta": return 10000;
            case "galería": return 8000;
            default: return 10000;
        }
    }
}
