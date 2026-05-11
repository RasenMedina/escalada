import controller.*;
import dao.escalada.MySQL.*;
import input.InputReader;
import view.Menus;
import view.Vista;
import model.Escola;
import model.Sector;

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
        // AssolimentController assolimentCtrl = new AssolimentController(new AssolimentDAOMySQL());

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
                    case 6 -> gestionarConsultesAvancades(escolaCtrl, viaCtrl, sectorCtrl, escaladorCtrl);
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
                    case 4 -> { // Llistar tots els Sectors
                        Vista.titol("LLISTAT DE SECTORS");
                        ctrl.obtenirTots().forEach(s -> Vista.mostrarLn(s.toString()));
                    }
                    case 5 -> { // Eliminar Sector
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
            // Lògica per a vies...
        } while (opcio != 0);
    }

    private static void gestionarEscaladors(EscaladorController ctrl) {
        int opcio;
        do {
            Menus.menuEscaladors();
            opcio = InputReader.llegirOpcio("Escull una operació", 0, 7);
            // Lògica per a escaladors...
        } while (opcio != 0);
    }

    private static void gestionarLlargs(LlargController ctrl) {
        int opcio;
        do {
            Menus.menuLlargs();
            opcio = InputReader.llegirOpcio("Escull una operació", 0, 6);
            // Lògica per a llargs...
        } while (opcio != 0);
    }

    private static void gestionarConsultesAvancades(EscolaController e, ViaController v, SectorController s, EscaladorController esc) {
        int opcio;
        do {
            Menus.menuConsultesAvancades();
            opcio = InputReader.llegirOpcio("Escull consulta", 0, 8);
            // Implementació de les consultes complexes descrites al README
        } while (opcio != 0);
    }
}