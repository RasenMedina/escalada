import controller.*;
import dao.escalada.MySQL.*;
import input.InputReader;
import view.Menus;
import view.Vista;
import model.Escola;
import model.Sector;
import model.Via;
import model.Escalador;
import model.Llarg;
import model.Assoliment;

import java.util.List;

/**
 * Classe principal que executa el bucle de l'aplicació.
 * Gestiona la navegació entre els diferents mòduls del sistema d'escalada.
 */
public class Main {

    public static void main(String[] args) {
        // Configuració inicial de la vista
        Vista.setMode(Vista.MODE_CONSOLA);
        Vista.titol("BENVINGUTS A L'APLICACIÓ D'ESCALADA JDBC");

        // Inicialització dels DAOs i Controladors
        EscolaController escolaCtrl = new EscolaController(new EscolaDAOMySQL());
        SectorController sectorCtrl = new SectorController(new SectorDAOMySQL());
        ViaController viaCtrl = new ViaController(new ViaDAOMySQL());
        EscaladorController escaladorCtrl = new EscaladorController(new EscaladorDAOMySQL());
        LlargController llargCtrl = new LlargController(new LlargDAOMySQL());
        AssolimentController assolimentCtrl = new AssolimentController(new AssolimentDAOMySQL());

        int opcioPrincipal;

        do {
            Menus.menuPrincipal();
            opcioPrincipal = InputReader.llegirOpcio("Selecciona una secció", 0, 6);

            try {
                switch (opcioPrincipal) {
                    case 1 -> gestionarEscoles(escolaCtrl);
                    case 2 -> gestionarSectors(sectorCtrl);
                    case 3 -> gestionarVies(viaCtrl);
                    case 4 -> gestionarEscaladors(escaladorCtrl);
                    case 5 -> gestionarLlargs(llargCtrl);
                    case 6 -> gestionarAssoliments(assolimentCtrl);
                    case 7 -> gestionarConsultesAvancades(escolaCtrl, viaCtrl, sectorCtrl, escaladorCtrl);
                    case 0 -> Vista.info("Tancant connexions i sortint del sistema...");
                }
            } catch (Exception e) {
                Vista.error("Error inesperat: " + e.getMessage());
            }

        } while (opcioPrincipal != 0);

        Vista.ok("Programa finalitzat correctament. Bona escalada!");
    }

    // --- MÈTODES DE GESTIÓ DE SUB-MENÚS ---

    private static void gestionarEscoles(EscolaController ctrl) {
        int opcio;
        do {
            Menus.menuEscoles(ctrl);
            opcio = InputReader.llegirOpcio("Escull una operació", 0, 7);

            try {
                switch (opcio) {
                    case 1 -> {
                        // Crear Escola
                        Escola nova = InputReader.llegirEscola();
                        ctrl.crear(nova);
                        Vista.ok("Escola creada correctament.");
                    }
                    case 2 -> {
                        // Modificar Escola
                        int id = InputReader.llegirInt("ID de l'escola a modificar");
                        Escola existent = InputReader.llegirEscola();
                        existent.setIdEscola(id);
                        ctrl.actualitzar(existent);
                        Vista.ok("Escola actualitzada.");
                    }
                    case 3 -> {
                        // Llistar una Escola (per ID)
                        int id = InputReader.llegirInt("ID de l'escola");
                        Escola e = ctrl.obtenirPerId(id);
                        if (e != null) {
                            Vista.mostrarLn(e.toString());
                        } else {
                            Vista.error("No s'ha trobat cap escola amb aquest ID.");
                        }
                    }
                    case 4 -> {
                        // Llistar totes les Escoles
                        Vista.titol("LLISTAT D'ESCOLES");
                        ctrl.obtenirTotes().forEach(e -> Vista.mostrarLn(e.toString()));
                    }
                    case 5 -> {
                        // Eliminar Escola
                        int id = InputReader.llegirInt("ID de l'escola a eliminar");
                        if (InputReader.llegirBoolean("Estàs segur?")) {
                            ctrl.eliminar(id);
                            Vista.ok("Escola eliminada.");
                        }
                    }

                    case 0 -> Vista.info("Tornant al menú principal...");
                }
            } catch (Exception e) {
                // Gestion des exceptions provenant du DAO ou du Controller
                Vista.error("Error en la gestió d'escoles: " + e.getMessage());
            }
        } while (opcio != 0);
    }

    private static void gestionarSectors(SectorController ctrl) {
        int opcio;
        do {
            Menus.menuSectors();
            opcio = InputReader.llegirOpcio("Escull una operació", 0, 6);

            try {
                switch (opcio) {
                    case 1 -> { // Crear Sector
                        // InputReader s'encarrega de demanar ID escola, nom, lat/long, etc.
                        Sector nou = InputReader.llegirSector();
                        ctrl.crear(nou);
                        Vista.ok("Sector creat correctament.");
                    }
                    case 2 -> { // Modificar Sector
                        int id = InputReader.llegirInt("ID del sector a modificar");
                        Sector existent = InputReader.llegirSector();
                        existent.setIdSector(id);
                        ctrl.actualitzar(existent);
                        Vista.ok("Sector actualitzat.");
                    }
                    case 3 -> { // Llistar un Sector (per ID)
                        int id = InputReader.llegirInt("ID del sector");
                        Sector s = ctrl.obtenirPerId(id);
                        if (s != null) {
                            Vista.mostrarLn(s.toString());
                        } else {
                            Vista.error("No s'ha trobat cap sector amb aquest ID.");
                        }
                    }
                    case 4 -> {
                        // Llistar tots els Sectors
                        Vista.titol("LLISTAT DE SECTORS");
                        ctrl.obtenirTots().forEach(s -> Vista.mostrarLn(s.toString()));
                    }
                    case 5 -> {
                        // Eliminar Sector
                        int id = InputReader.llegirInt("ID del sector a eliminar");
                        if (InputReader.llegirBoolean("Estàs segur d'eliminar aquest sector?")) {
                            ctrl.eliminar(id);
                            Vista.ok("Sector eliminat.");
                        }
                    }

                    case 0 -> Vista.info("Tornant al menú principal...");
                }
            } catch (Exception e) {
                // Captura errors de JDBC o regles de negoci (com sectors de gel)
                Vista.error("Error en la gestió de sectors: " + e.getMessage());
            }
        } while (opcio != 0);
    }

    private static void gestionarVies(ViaController ctrl) {
        int opcio;
        do {
            Menus.menuVies();
            opcio = InputReader.llegirOpcio("Escull una operació", 0, 6);

            try {
                switch (opcio) {
                    case 1 -> {
                        // Crear Via
                        // InputReader.llegirVia() ja valida tipus, orientació i estat
                        Via nova = InputReader.llegirVia();
                        ctrl.crear(nova);
                        Vista.ok("Via creada amb èxit.");
                    }
                    case 2 -> {
                        // Modificar Via
                        int id = InputReader.llegirInt("ID de la via a modificar");
                        Via existent = InputReader.llegirVia();
                        existent.setIdVia(id);
                        ctrl.actualitzar(existent);
                        Vista.ok("Via actualitzada correctament.");
                    }
                    case 3 -> {
                        // Llistar una Via (per ID)
                        int id = InputReader.llegirInt("ID de la via");
                        Via v = ctrl.obtenirPerId(id);
                        if (v != null) {
                            Vista.mostrarLn(v.toString());
                        } else {
                            Vista.error("No s'ha trobat cap via amb l'ID " + id);
                        }
                    }
                    case 4 -> {
                        // Llistar totes les Vies
                        Vista.titol("LLISTAT GENERAL DE VIES");
                        ctrl.obtenirTotes().forEach(v -> Vista.mostrarLn(v.toString()));
                    }
                    case 5 -> {
                        // Eliminar Via
                        int id = InputReader.llegirInt("ID de la via a eliminar");
                        if (InputReader.llegirBoolean("Confirmes que vols eliminar la via?")) {
                            ctrl.eliminar(id);
                            Vista.ok("Via eliminada del sistema.");
                        }
                    }
                    case 0 -> Vista.info("Tornant al menú principal...");
                }
            } catch (Exception e) {
                // Captura errors com la violació de sectors de gel o noms duplicats
                Vista.error("Error en la gestió de vies: " + e.getMessage());
            }
        } while (opcio != 0);
    }

    private static void gestionarEscaladors(EscaladorController ctrl) {
        int opcio;
        do {
            Menus.menuEscaladors();
            opcio = InputReader.llegirOpcio("Escull una operació", 0, 7);
            try {
                switch (opcio) {
                    case 1 -> {
                        // Crear Escalador
                        // InputReader s'encarrega de demanar DNI, Nom, Cognoms, etc.
                        Escalador nou = InputReader.llegirEscalador();
                        ctrl.crear(nou);
                        Vista.ok("Escalador registrat correctament.");
                    }
                    case 2 -> {
                        // Modificar Escalador
                        int id = InputReader.llegirInt("ID de l'escalador a modificar");
                        Escalador existent = InputReader.llegirEscalador();
                        existent.setIdEscalador(id);
                        ctrl.actualitzar(existent);
                        Vista.ok("Dades de l'escalador actualitzades.");
                    }
                    case 3 -> {
                        // Llistar un Escalador (per ID)
                        int id = InputReader.llegirInt("ID de l'escalador");
                        Escalador e = ctrl.obtenirPerId(id);
                        if (e != null) {
                            Vista.mostrarLn(e.toString());
                        } else {
                            Vista.error("No existeix cap escalador amb aquest ID.");
                        }
                    }
                    case 4 -> {
                        // Llistar tots els Escaladors
                        Vista.titol("LLISTAT D'ESCALADORS");
                        ctrl.obtenirTots().forEach(e -> Vista.mostrarLn(e.toString()));
                    }
                    case 5 -> {
                        // Eliminar Escalador
                        int id = InputReader.llegirInt("ID de l'escalador a eliminar");
                        if (InputReader.llegirBoolean("Estàs segur d'eliminar aquest perfil?")) {
                            ctrl.eliminar(id);
                            Vista.ok("Escalador eliminat.");
                        }
                    }
                    case 6 -> {
                        // Mostrar escaladors amb el mateix nivell (Requeriment avançat)
                        String nivell = InputReader.llegir("Introdueix el nivell a cercar (ex: 6a)");
                        Vista.titol("ESCALADORS AMB NIVELL " + nivell);
                        ctrl.getEscaladorsPerNivell(nivell).forEach(e -> Vista.mostrarLn(e.toString()));
                    }
                    case 7 -> {
                        // Filtrar per estil preferit
                        String estil = InputReader.llegir("Estil preferit (ex: Esportiva, Clàssica)");
                        Vista.titol("ESCALADORS QUE PREFEREIXEN " + estil.toUpperCase());
                        ctrl.getByEstil(estil).forEach(e -> Vista.mostrarLn(e.toString()));
                    }
                    case 0 -> Vista.info("Tornant al menú principal...");
                }
            } catch (Exception e) {
                Vista.error("Error en la gestió d'escaladors: " + e.getMessage());
            }        } while (opcio != 0);
    }

    private static void gestionarLlargs(LlargController ctrl) {
        int opcio;
        do {
            Menus.menuLlargs();
            opcio = InputReader.llegirOpcio("Escull una operació", 0, 6);

            try {
                switch (opcio) {
                    case 1 -> {
                        // Crear Llarg
                        // InputReader.llegirLlarg() demana: ID via, ordre, llargada i dificultat
                        Llarg nou = InputReader.llegirLlarg();
                        ctrl.afegirLlarg(nou);
                        Vista.ok("Llarg afegit correctament a la via.");
                    }
                    case 2 -> {
                        // Modificar Llarg
                        int id = InputReader.llegirInt("ID del llarg a modificar");
                        Llarg existent = InputReader.llegirLlarg();
                        existent.setIdLlarg(id);
                        ctrl.actualitzar(existent);
                        Vista.ok("Llarg actualitzat correctament.");
                    }
                    case 3 -> {
                        // Llistar un Llarg (per ID)
                        int id = InputReader.llegirInt("ID del llarg a consultar");
                        List<Llarg> l = ctrl.obtenirPerId(id);
                        if (l != null) {
                            Vista.mostrarLn(l.toString());
                        } else {
                            Vista.error("No s'ha trobat cap llarg amb aquest ID.");
                        }
                    }
                    case 4 -> {
                        // Llistar tots els Llargs
                        Vista.titol("LLISTAT GENERAL DE LLARGS");
                        ctrl.obtenirTotes().forEach(l -> Vista.mostrarLn(l.toString()));
                    }
                    case 5 -> { // Eliminar Llarg
                        int id = InputReader.llegirInt("ID del llarg a eliminar");
                        if (InputReader.llegirBoolean("Estàs segur que vols eliminar aquest llarg?")) {
                            ctrl.eliminar(id);
                            Vista.ok("Llarg eliminat.");
                        }
                    }
                    case 6 -> {
                        // Veure llargs d'una Via específica
                        int idVia = InputReader.llegirInt("Introdueix l'ID de la via");
                        Vista.titol("LLARGS DE LA VIA ID: " + idVia);
                        // Aquest mètode ha de retornar la llista filtrada des del DAO
                        ctrl.obtenirPerVia(idVia).forEach(l -> Vista.mostrarLn(l.toString()));
                    }
                    case 0 -> Vista.info("Tornant al menú principal...");
                }
            } catch (Exception e) {
                Vista.error("Error en la gestió de llargs: " + e.getMessage());
            }
        } while (opcio != 0);
    }

    /**
     * Gestiona les interaccions del menú d'assoliments (logs de pujades).
     * @param ctrl El controlador d'assoliments injectat des del Main.
     */
    private static void gestionarAssoliments(AssolimentController ctrl) {
        int opcio;
        do {
            Menus.menuAssoliments();
            opcio = InputReader.llegirOpcio("Escull una operació", 0, 6);

            try {
                switch (opcio) {
                    case 1 -> {
                        // Registrar nou assoliment
                        // InputReader.llegirAssoliment() demana idEscalador, idVia, data, etc.
                        Assoliment nou = InputReader.llegirAssoliment();
                        ctrl.registrar(nou);
                        Vista.ok("Assoliment registrat amb èxit!");
                    }
                    case 2 -> {
                        // Modificar dades d'un assoliment
                        int id = InputReader.llegirInt("ID del registre d'assoliment a modificar");
                        Assoliment existent = InputReader.llegirAssoliment();
                        existent.setIdAssoliment(id);
                        ctrl.actualitzar(existent);
                        Vista.ok("Registre actualitzat.");
                    }
                    case 3 -> {
                        // Llistar assoliments d'un escalador
                        int idEscalador = InputReader.llegirInt("ID de l'escalador");
                        Vista.titol("ASSOLIMENTS DE L'ESCALADOR " + idEscalador);
                        ctrl.perEscalador(idEscalador).forEach(a -> Vista.mostrarLn(a.toString()));
                    }
                    case 4 -> {
                        // Llistar assoliments d'una via
                        int idVia = InputReader.llegirInt("ID de la via");
                        Vista.titol("ESCALADORS QUE HAN PUJAT LA VIA " + idVia);
                        ctrl.perVia(idVia).forEach(a -> Vista.mostrarLn(a.toString()));
                    }
                    case 5 -> {
                        // Eliminar registre
                        int id = InputReader.llegirInt("ID del registre a eliminar");
                        if (InputReader.llegirBoolean("Vols esborrar aquest registre de log?")) {
                            ctrl.eliminar(id);
                            Vista.ok("Registre eliminat.");
                        }
                    }

                    case 0 -> Vista.info("Tornant al menú principal...");
                }
            } catch (Exception e) {
                Vista.error("Error en la gestió d'assoliments: " + e.getMessage());
            }
        } while (opcio != 0);
    }
    private static void gestionarConsultesAvancades(EscolaController e, ViaController v, SectorController s, EscaladorController esc) {
        int opcio;
        do {
            Menus.menuConsultesAvancades();
            opcio = InputReader.llegirOpcio("Escull consulta", 0, 8);

            try {
                switch (opcio) {
                    case 1 -> {
                        // Vies disponibles d'una escola determinada
                        int idEscola = InputReader.llegirInt("ID de l'escola");
                        Vista.titol("VIES DISPONIBLES");
                        e.getViesDisponibles(idEscola).forEach(v -> Vista.mostrarLn(v.toString()));
                    }
                    case 2 -> {
                        // Cercar vies per rang de dificultat
                        String min = InputReader.llegir("Dificultat mínima (ex: 5c)");
                        String max = InputReader.llegir("Dificultat màxima (ex: 7a)");
                        Vista.titol("VIES ENTRE " + min + " I " + max);
                        v.getByRangDificultat(min, max).forEach(v -> Vista.mostrarLn(v.toString()));
                    }
                    case 3 -> { // Cercar vies segons estat
                        String estat = InputReader.llegirEstatVia(); // "Apte", "Construcció", "Tancada"
                        Vista.titol("VIES EN ESTAT: " + estat.toUpperCase());
                        v.getByEstat(estat).forEach(v -> Vista.mostrarLn(v.toString()));
                    }
                    case 4 -> { // Consultar escoles amb restriccions actives
                        Vista.titol("ESCOLES AMB RESTRICCIONS");
                        // Nota: Cal que el mètode estigui al controlador
                        esc.getAmbRestriccions().forEach(e -> Vista.mostrarLn(e.toString()));
                    }
                    case 5 -> { // Sectors amb més de X vies disponibles
                        int minim = InputReader.llegirInt("Mínim de vies");
                        Vista.titol("SECTORS AMB MÉS DE " + minim + " VIES");
                        s.getSectorsAmbMesDeXVies(minim).forEach(s -> Vista.mostrarLn(s.toString()));
                    }
                    case 6 -> { // Escaladors amb el mateix nivell màxim assolit
                        String nivell = InputReader.llegir("Introdueix nivell (ex: 6b)");
                        Vista.titol("ESCALADORS DE NIVELL " + nivell);
                        // Aquí fem servir la versió que calcula el nivell sense l'atribut nivellMaxim
                        esc.getEscaladorsPerNivell(nivell).forEach(e -> Vista.mostrarLn(e.toString()));
                    }
                    case 7 -> { // Vies que han passat a 'Apte' recentment
                        Vista.titol("VIES APTE RECENTMENT");
                        v.getRecentmentAptes().forEach(v -> Vista.mostrarLn(v.toString()));
                    }
                    case 8 -> { // Les vies més llargues d'una escola
                        int idEscola = InputReader.llegirInt("ID de l'escola");
                        Vista.titol("VIES MÉS LLARGUES DE L'ESCOLA");
                        esc.getViesMesLlargues(idEscola).forEach(v -> Vista.mostrarLn(v.toString()));
                    }
                    case 0 -> Vista.info("Tornant al menú principal...");
                }
            } catch (Exception e) {
                Vista.error("Error en l'execució de la consulta: " + e.getMessage());
            }
        } while (opcio != 0);
    }
}