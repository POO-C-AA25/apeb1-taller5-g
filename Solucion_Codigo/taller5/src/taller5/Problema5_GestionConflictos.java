
public class Problema5_GestionConflictos {

    public static void main(String[] args) {
        String[] paises = {"EEUU", "Rusia", "China", "Francia"};
        ConflictoInternacional conflicto = new ConflictoInternacional("Conflicto XYZ", paises, 1, 1, 2023);

        Evento e1 = new Evento("Batalla de Ciudad A", 5, 3, 2023, "Ciudad A", "Fuerte enfrentamiento",
                "batalla", false, 20, "EEUU");
        Evento e2 = new Evento("Tratado de Paz", 15, 4, 2023, "Ginebra", "Acuerdo de alto al fuego",
                "tratado de paz", false, 0, "");
        Evento e3 = new Evento("Batalla nuclear en Ciudad B", 20, 5, 2023, "Ciudad B", "Uso de armas nucleares",
                "batalla", true, 60, "Francia");

        conflicto.agregarEvento(e1);
        conflicto.agregarEvento(e2);
        conflicto.agregarEvento(e3);

        System.out.println("Estado actual: " + conflicto.estadoActual);

        conflicto.consultarEventos();
    }

}

class Evento {

    String nombre;
    int dia;
    int mes;
    int anio;
    String ubicacion;
    String descripcion;
    String tipoEvento; // "batalla", "tratado de paz", etc.
    boolean usaArmasNucleares;
    double porcentajeBajasPais; // 0 si no aplica
    String paisAfectado;

    public Evento(String nombre, int dia, int mes, int anio, String ubicacion, String descripcion,
            String tipoEvento, boolean usaArmasNucleares, double porcentajeBajasPais, String paisAfectado) {
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.tipoEvento = tipoEvento.toLowerCase();
        this.usaArmasNucleares = usaArmasNucleares;
        this.porcentajeBajasPais = porcentajeBajasPais;
        this.paisAfectado = paisAfectado;
    }

    public void actualizarDescripcion(String nuevaDescripcion) {
        this.descripcion = nuevaDescripcion;
    }

    public void consultarDetalles() {
        System.out.println("Evento: " + nombre);
        System.out.println("Fecha: " + dia + "/" + mes + "/" + anio);
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Tipo: " + tipoEvento);
        if (tipoEvento.equals("batalla")) {
            System.out.println("Uso armas nucleares: " + (usaArmasNucleares ? "Sí" : "No"));
            System.out.println("País afectado: " + paisAfectado);
            System.out.println("Porcentaje bajas: " + porcentajeBajasPais + "%");
        }
    }
}

class ConflictoInternacional {

    String nombre;
    String[] paisesInvolucrados;
    int diaInicio;
    int mesInicio;
    int anioInicio;
    String estadoActual;
    Evento[] eventos;
    int numEventos;

    static final int TOTAL_PAISES_MUNDO = 195;
    static final String[] PAISES_PRIMER_MUNDO = {"EEUU", "Canadá", "Reino Unido", "Alemania", "Francia", "Japón", "Australia"}; // ejemplo

    public ConflictoInternacional(String nombre, String[] paises, int dia, int mes, int anio) {
        this.nombre = nombre;
        this.paisesInvolucrados = paises;
        this.diaInicio = dia;
        this.mesInicio = mes;
        this.anioInicio = anio;
        this.estadoActual = "Activo";
        this.eventos = new Evento[100]; // máximo 100 eventos
        this.numEventos = 0;
    }

    public void agregarEvento(Evento e) {
        if (numEventos < eventos.length) {
            eventos[numEventos] = e;
            numEventos++;
            actualizarEstadoConflicto();
        } else {
            System.out.println("No se pueden agregar más eventos.");
        }
    }

    private boolean esPaisPrimerMundo(String pais) {
        for (String p : PAISES_PRIMER_MUNDO) {
            if (p.equalsIgnoreCase(pais)) {
                return true;
            }
        }
        return false;
    }

    public void actualizarEstadoConflicto() {
        int conteoBatallasPaises = 0;
        int conteoBatallasPaisesPrimerMundo = 0;
        boolean usoNuclearesEnPrimerMundo = false;
        boolean convocaONU = false;

        // Contar en cuántos países hay batallas
        boolean[] paisesConBatallas = new boolean[TOTAL_PAISES_MUNDO];
        int contadorPaisesBatalla = 0;

        // Para simplicidad: solo contamos si hay batalla en un país del conflicto
        for (int i = 0; i < numEventos; i++) {
            Evento e = eventos[i];
            if (e.tipoEvento.equals("batalla")) {
                // Verificar si país está en paisesInvolucrados
                boolean paisInvolucrado = false;
                for (String p : paisesInvolucrados) {
                    if (p.equalsIgnoreCase(e.paisAfectado)) {
                        paisInvolucrado = true;
                        break;
                    }
                }
                if (paisInvolucrado) {
                    conteoBatallasPaises++;

                    if (esPaisPrimerMundo(e.paisAfectado)) {
                        conteoBatallasPaisesPrimerMundo++;
                        if (e.usaArmasNucleares) {
                            usoNuclearesEnPrimerMundo = true;
                        }
                    }

                    if (e.porcentajeBajasPais >= 50) {
                        convocaONU = true;
                    }
                }
            }
        }

        double porcentajePaisesBatalla = ((double) conteoBatallasPaises / TOTAL_PAISES_MUNDO) * 100;
        double porcentajePaisesPrimerMundo = ((double) conteoBatallasPaisesPrimerMundo / PAISES_PRIMER_MUNDO.length) * 100;

        if (porcentajePaisesBatalla > 50 || (porcentajePaisesPrimerMundo > 50 && usoNuclearesEnPrimerMundo)) {
            estadoActual = "Guerra Mundial";
        } else if ((porcentajePaisesBatalla >= 30 && porcentajePaisesBatalla <= 50) || convocaONU) {
            estadoActual = "Reunión urgente ONU convocada";
        } else {
            estadoActual = "Activo";
        }
    }

    public void consultarEventos() {
        System.out.println("Eventos en conflicto: " + nombre);
        for (int i = 0; i < numEventos; i++) {
            eventos[i].consultarDetalles();
            System.out.println("------------");
        }
    }
}
