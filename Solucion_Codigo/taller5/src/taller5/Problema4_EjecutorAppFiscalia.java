
public class Problema4_EjecutorAppFiscalia {

    public static void main(String[] args) {
        // Fecha de inicio: 20/05/2025
        CasoCorrupcion caso = new CasoCorrupcion("Caso ABC", 20, 5, 2025);

        PersonaImplicada p1 = new PersonaImplicada("Luis", 40, "Funcionario", "acusado");
        p1.colabora = true;
        p1.danoEconomico = 50000;
        p1.sentencia = 0.5;

        PersonaImplicada p2 = new PersonaImplicada("Maria", 35, "Testigo", "testigo");

        caso.agregarPersona(p1);
        caso.agregarPersona(p2);

        // Fecha actual: 30/05/2025 (10 días después)
        caso.actualizarEstado(30, 5, 2025);

        System.out.println("Estado del caso: " + caso.estado);

        caso.mostrarPersonas();

        if (p1.nivelImplicacion.equalsIgnoreCase("acusado") && p1.colabora) {
            System.out.println(p1.nombre + " puede acogerse a reducción de pena.");
            if (p1.sentencia < 1) {
                double fianzaMax = p1.danoEconomico * 0.5;
                System.out.println("Puede pagar fianza hasta: $" + fianzaMax);
            }
        }
    }
}

class PersonaImplicada {

    String nombre;
    int edad;
    String ocupacion;
    String nivelImplicacion; // "acusado", "testigo", etc.
    boolean colabora;
    double danoEconomico;
    double sentencia;

    public PersonaImplicada(String nombre, int edad, String ocupacion, String nivelImplicacion) {
        this.nombre = nombre;
        this.edad = edad;
        this.ocupacion = ocupacion;
        this.nivelImplicacion = nivelImplicacion;
        this.colabora = false;
        this.danoEconomico = 0;
        this.sentencia = 0;
    }
}

class CasoCorrupcion {

    String nombreCaso;
    int diaInicio;
    int mesInicio;
    int anioInicio;
    String estado; // "Iniciado", "Alerta", "Urgente"
    PersonaImplicada[] personas;
    int numPersonas;

    public CasoCorrupcion(String nombreCaso, int dia, int mes, int anio) {
        this.nombreCaso = nombreCaso;
        this.diaInicio = dia;
        this.mesInicio = mes;
        this.anioInicio = anio;
        this.estado = "Iniciado";
        this.personas = new PersonaImplicada[10]; // máximo 10 personas
        this.numPersonas = 0;
    }

    public void agregarPersona(PersonaImplicada p) {
        if (numPersonas < personas.length) {
            personas[numPersonas] = p;
            numPersonas++;
        }
    }

    // Método simple para calcular los días pasados desde inicio (solo diferencia de días)
    public int calcularDias(int diaActual, int mesActual, int anioActual) {
        int diasInicio = diaInicio + mesInicio * 30 + anioInicio * 365;
        int diasActual = diaActual + mesActual * 30 + anioActual * 365;
        return diasActual - diasInicio;
    }

    public void actualizarEstado(int diaActual, int mesActual, int anioActual) {
        int dias = calcularDias(diaActual, mesActual, anioActual);
        if (dias > 14) {
            estado = "Urgente";
        } else if (dias > 7) {
            estado = "Alerta";
        } else {
            estado = "Iniciado";
        }
    }

    public void mostrarPersonas() {
        for (int i = 0; i < numPersonas; i++) {
            PersonaImplicada p = personas[i];
            System.out.println(p.nombre + " - " + p.nivelImplicacion + " - Colabora: " + p.colabora);
        }
    }
}
