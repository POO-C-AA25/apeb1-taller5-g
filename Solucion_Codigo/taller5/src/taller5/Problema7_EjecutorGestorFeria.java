
public class Problema7_EjecutorGestorFeria {

    public static void main(String[] args) {
        Festival festival = new Festival(5);

        Expositor expositor1 = new Expositor("Sabores Andinos", 3);
        expositor1.agregarPlato(new Plato("Ceviche", new String[]{"Pescado", "Limón", "Cebolla"}, 50, 5.0));
        expositor1.agregarPlato(new Plato("Locro", new String[]{"Papa", "Queso", "Maíz"}, 30, 4.0));

        Expositor expositor2 = new Expositor("Delicias del Mar", 2);
        expositor2.agregarPlato(new Plato("Pulpo a la Gallega", new String[]{"Pulpo", "Pimentón", "Aceite"}, 40, 7.0));
        expositor2.agregarPlato(new Plato("Arroz con Mariscos", new String[]{"Arroz", "Mariscos", "Cilantro"}, 25, 6.5));

        festival.agregarExpositor(expositor1);
        festival.agregarExpositor(expositor2);

        // Simular ventas
        expositor1.getPlatos()[0].venderPlatos(20);  // 20 ceviches vendidos
        expositor1.getPlatos()[1].venderPlatos(10);  // 10 locros vendidos
        expositor2.getPlatos()[0].venderPlatos(15);  // 15 pulpos vendidos
        expositor2.getPlatos()[1].venderPlatos(5);   // 5 arroces vendidos

        festival.mostrarReporte();
    }
}

class Plato {

    private String nombre;
    private String[] ingredientes;
    private int cantidadDisponible;
    private int cantidadVendida;
    private double precio;

    public Plato(String nombre, String[] ingredientes, int cantidadDisponible, double precio) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.cantidadDisponible = cantidadDisponible;
        this.precio = precio;
        this.cantidadVendida = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public double getPrecio() {
        return precio;
    }

    public void venderPlatos(int cantidad) {
        if (cantidad <= cantidadDisponible) {
            cantidadVendida += cantidad;
            cantidadDisponible -= cantidad;
        } else {
            System.out.println("No hay suficiente cantidad disponible para el plato " + nombre);
        }
    }

    public double calcularIngreso() {
        return cantidadVendida * precio;
    }

    @Override
    public String toString() {
        return nombre + " (Vendidos: " + cantidadVendida + ", Disponibles: " + cantidadDisponible + ")";
    }
}

class Expositor {

    private String nombre;
    private Plato[] platos;
    private int numPlatos; // para controlar cuántos platos hay

    public Expositor(String nombre, int maxPlatos) {
        this.nombre = nombre;
        this.platos = new Plato[maxPlatos];
        this.numPlatos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarPlato(Plato plato) {
        if (numPlatos < platos.length) {
            platos[numPlatos++] = plato;
        } else {
            System.out.println("No se pueden agregar más platos para " + nombre);
        }
    }

    public Plato[] getPlatos() {
        Plato[] activos = new Plato[numPlatos];
        for (int i = 0; i < numPlatos; i++) {
            activos[i] = platos[i];
        }
        return activos;
    }

    public double calcularIngresoTotal() {
        double total = 0;
        for (int i = 0; i < numPlatos; i++) {
            total += platos[i].calcularIngreso();
        }
        return total;
    }

    @Override
    public String toString() {
        String res = "Expositor: " + nombre + "\nPlatos:\n";
        for (int i = 0; i < numPlatos; i++) {
            res += "  - " + platos[i].toString() + "\n";
        }
        res += "Ingresos totales: $" + calcularIngresoTotal();
        return res;
    }
}

class Festival {

    private Expositor[] expositores;
    private int numExpositores;

    public Festival(int maxExpositores) {
        expositores = new Expositor[maxExpositores];
        numExpositores = 0;
    }

    public void agregarExpositor(Expositor expositor) {
        if (numExpositores < expositores.length) {
            expositores[numExpositores++] = expositor;
        } else {
            System.out.println("No se pueden agregar más expositores.");
        }
    }

    public void mostrarReporte() {
        System.out.println("Reporte Festival Gastronómico\n----------------------------");
        Expositor topExpositor = null;
        Plato topPlato = null;
        int maxVentasPlato = -1;

        for (int i = 0; i < numExpositores; i++) {
            Expositor exp = expositores[i];
            System.out.println(exp);

            // Revisar platos más vendidos
            Plato[] platos = exp.getPlatos();
            for (Plato p : platos) {
                if (p.getCantidadVendida() > maxVentasPlato) {
                    maxVentasPlato = p.getCantidadVendida();
                    topPlato = p;
                }
            }

            // Revisar expositor con más ingresos
            if (topExpositor == null || exp.calcularIngresoTotal() > topExpositor.calcularIngresoTotal()) {
                topExpositor = exp;
            }
            System.out.println();
        }

        if (topPlato != null) {
            System.out.println("Plato más vendido: " + topPlato.getNombre() + " con " + maxVentasPlato + " unidades vendidas.");
        }
        if (topExpositor != null) {
            System.out.println("Expositor con mayor movimiento: " + topExpositor.getNombre() + " con ingresos de $" + topExpositor.calcularIngresoTotal());
        }
    }
}
