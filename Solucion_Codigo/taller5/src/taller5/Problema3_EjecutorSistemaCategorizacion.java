
import java.util.Scanner;

public class Problema3_EjecutorSistemaCategorizacion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese nombre de la empresa: ");
        String nombreEmpresa = sc.nextLine();

        System.out.print("Ingrese RUC de la empresa: ");
        String ruc = sc.nextLine();

        System.out.print("Ingrese dirección de la empresa: ");
        String direccion = sc.nextLine();

        Departamento[] departamentos = new Departamento[3];

        for (int i = 0; i < 3; i++) {
            System.out.println("\nDepartamento " + (i + 1));
            System.out.print("Nombre: ");
            String nombreDep = sc.nextLine();

            System.out.print("Número de empleados: ");
            int numEmpleados = sc.nextInt();

            System.out.print("Producción anual: ");
            double produccionAnual = sc.nextDouble();
            sc.nextLine(); // limpiar buffer

            departamentos[i] = new Departamento(nombreDep, numEmpleados, produccionAnual);
        }

        Empresa empresa = new Empresa(nombreEmpresa, ruc, direccion, departamentos);
        empresa.mostrarDepartamentos();

        sc.close();
    }
}

class Empresa {

    private String nombre;
    private String ruc;
    private String direccion;
    private Departamento[] departamentos;

    public Empresa(String nombre, String ruc, String direccion, Departamento[] departamentos) {
        this.nombre = nombre;
        this.ruc = ruc;
        this.direccion = direccion;
        this.departamentos = departamentos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public Departamento[] getDepartamentos() {
        return departamentos;
    }

    public void mostrarDepartamentos() {
        System.out.println("Empresa: " + nombre + " - RUC: " + ruc + " - Dirección: " + direccion);
        System.out.println("Departamentos y sus categorías:\n");
        for (Departamento d : departamentos) {
            System.out.println(d);
        }
    }
}

class Departamento {

    private String nombre;
    private int numEmpleados;
    private double produccionAnual;
    private char categoria;

    public Departamento(String nombre, int numEmpleados, double produccionAnual) {
        this.nombre = nombre;
        this.numEmpleados = numEmpleados;
        this.produccionAnual = produccionAnual;
        this.categoria = determinarCategoria();
    }

    private char determinarCategoria() {
        if (numEmpleados > 20 && produccionAnual > 1_000_000) {
            return 'A';
        } else if (numEmpleados == 20 && produccionAnual == 1_000_000) {
            return 'B';
        } else if (numEmpleados == 10 && produccionAnual == 500_000) {
            return 'C';
        } else {
            // Si no cumple exactamente, podemos asignar categoría D (u otra) o dejar en blanco
            return 'D';
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumEmpleados() {
        return numEmpleados;
    }

    public double getProduccionAnual() {
        return produccionAnual;
    }

    public char getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Departamento: " + nombre
                + "\nNúmero de empleados: " + numEmpleados
                + "\nProducción anual: " + produccionAnual
                + "\nCategoría: " + categoria + "\n";
    }
}
