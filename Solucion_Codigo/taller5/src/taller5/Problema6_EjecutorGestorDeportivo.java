
import java.util.ArrayList;

public class Problema6_EjecutorGestorDeportivo {

    public static void main(String[] args) {
        EventoDeportivo evento = new EventoDeportivo("Juegos Universitarios 2025");

        Disciplina natacion = new Disciplina("Natación");
        Disciplina atletismo = new Disciplina("Atletismo");

        Participante p1 = new Participante("Ana", 1);
        p1.registrarAsistencia(true);
        p1.agregarResultado(new Resultado("100m Libre", 9.5, 8.0));
        p1.agregarResultado(new Resultado("200m Libre", 8.0, 8.0));

        Participante p2 = new Participante("Carlos", 2);
        p2.registrarAsistencia(true);
        p2.agregarResultado(new Resultado("100m Libre", 7.0, 8.0));
        p2.agregarResultado(new Resultado("200m Libre", 8.5, 8.0));

        natacion.agregarParticipante(p1);
        natacion.agregarParticipante(p2);

        evento.agregarDisciplina(natacion);
        evento.agregarDisciplina(atletismo);

        evento.reporteEstadisticas();
    }
}

class Resultado {

    String nombrePrueba;
    double puntaje;
    boolean superado;

    public Resultado() {
    }

    public Resultado(String nombrePrueba, double puntaje, double umbralSuperacion) {
        this.nombrePrueba = nombrePrueba;
        this.puntaje = puntaje;
        this.superado = puntaje >= umbralSuperacion;
    }
}

class Participante {

    String nombre;
    int id;
    boolean asistencia;
    ArrayList<Resultado> resultados;

    public Participante() {
    }

    public Participante(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        this.asistencia = false;
        this.resultados = new ArrayList<>();
    }

    public void registrarAsistencia(boolean presente) {
        this.asistencia = presente;
    }

    public void agregarResultado(Resultado r) {
        resultados.add(r);
    }

    public double promedioPuntajes() {
        if (resultados.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (Resultado r : resultados) {
            suma += r.puntaje;
        }
        return suma / resultados.size();
    }

    public int contarPruebasSuperadas() {
        int count = 0;
        for (Resultado r : resultados) {
            if (r.superado) {
                count++;
            }
        }
        return count;
    }
}

class Disciplina {

    public Disciplina() {
    }

    String nombre;
    ArrayList<Participante> participantes;

    public Disciplina(String nombre) {
        this.nombre = nombre;
        this.participantes = new ArrayList<>();
    }

    public void agregarParticipante(Participante p) {
        participantes.add(p);
    }

    public Participante participanteDestacado() {
        if (participantes.isEmpty()) {
            return null;
        }
        Participante destacado = participantes.get(0);
        for (Participante p : participantes) {
            if (p.promedioPuntajes() > destacado.promedioPuntajes()) {
                destacado = p;
            }
        }
        return destacado;
    }

    public double promedioRendimiento() {
        if (participantes.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (Participante p : participantes) {
            suma += p.promedioPuntajes();
        }
        return suma / participantes.size();
    }
}

class EventoDeportivo {

    public EventoDeportivo() {
    }

    String nombreEvento;
    ArrayList<Disciplina> disciplinas;

    public EventoDeportivo(String nombreEvento) {
        this.nombreEvento = nombreEvento;
        this.disciplinas = new ArrayList<>();
    }

    public void agregarDisciplina(Disciplina d) {
        disciplinas.add(d);
    }

    public void reporteEstadisticas() {
        System.out.println("Reporte Evento: " + nombreEvento);
        for (Disciplina d : disciplinas) {
            System.out.println("Disciplina: " + d.nombre);
            System.out.println("Promedio rendimiento: " + d.promedioRendimiento());
            Participante destacado = d.participanteDestacado();
            if (destacado != null) {
                System.out.println("Participante destacado: " + destacado.nombre
                        + " con promedio " + destacado.promedioPuntajes());
            }
            System.out.println();
        }
    }
}
