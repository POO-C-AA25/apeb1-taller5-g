
import java.util.Scanner;
public class Problema2_EjecutorSistemaCalificaciones {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese la edad del estudiante: ");
        int edad = sc.nextInt();

        Estudiante estudiante = new Estudiante(nombre, edad);

        sc.nextLine(); // limpiar buffer

        System.out.print("Ingrese el nombre de la materia: ");
        String nombreMateria = sc.nextLine();

        System.out.print("Ingrese la calificación acd (max 3.5): ");
        double acd = sc.nextDouble();

        System.out.print("Ingrese la calificación ape (max 3.5): ");
        double ape = sc.nextDouble();

        System.out.print("Ingrese la calificación aa (max 3.0): ");
        double aa = sc.nextDouble();

        Materia materia = new Materia(nombreMateria, acd, ape, aa);

        estudiante.setMateria(materia);

        estudiante.verificarAprobacion();

        sc.close();

    }
}

class Materia {

    private String nombre;
    private double acd; // máximo 3.5
    private double ape; // máximo 3.5
    private double aa;  // máximo 3.0

    public Materia(String nombre, double acd, double ape, double aa) {
        this.nombre = nombre;
        this.acd = acd;
        this.ape = ape;
        this.aa = aa;
    }

    public String getNombre() {
        return nombre;
    }

    public double getAcd() {
        return acd;
    }

    public double getApe() {
        return ape;
    }

    public double getAa() {
        return aa;
    }

    public double calcularPuntaje() {
        return acd + ape + aa;
    }

    public double calcularPorcentaje() {
        return (calcularPuntaje() / 10.0) * 100;
    }
}

class Estudiante {

    private String nombre;
    private int edad;
    private Materia materia;

    public Estudiante(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public Materia getMateria() {
        return materia;
    }

    public void verificarAprobacion() {
        if (materia == null) {
            System.out.println("No tiene ninguna materia asignada.");
            return;
        }

        double porcentaje = materia.calcularPorcentaje();

        if (porcentaje >= 70) {
            System.out.println("¡Felicidades " + nombre + "! Has aprobado la materia " + materia.getNombre() + ".");
        } else {
            double acumulado = porcentaje * 0.6;
            System.out.println("No aprobaste la materia " + materia.getNombre() + ".");
            System.out.println("Debes rendir un examen de recuperación con puntaje máximo 3.5 pts.");
            System.out.println("Tu puntaje acumulado actual es: " + String.format("%.2f", acumulado) + "% (60% de la nota total).");
        }
    }
}
