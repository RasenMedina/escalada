import controller.*;
import dao.escalada.MySQL.*;
import input.InputReader;
import model.Escola;
import model.Sector;
import view.Menus;
import view.Vista;

public class Main {

    public static void main(String[] args) {

        Vista.titol("APLICACIÓ ESCALADA");

        // Controllers
        EscolaController escolaController = new EscolaController(new EscolaDAOMySQL());
        SectorController sectorController = new SectorController(new SectorDAOMySQL());
        ViaController viaController = new ViaController(new ViaDAOMySQL());
        EscaladorController escaladorController = new EscaladorController(new EscaladorDAOMySQL());
        // Injecció de dependències

        int opcio;

        do {
            Menus.menuPrincipal();
            opcio = InputReader.llegirOpcio("Escull opció", 0, 4);

            switch (opcio) {

                case 1:
                    gestionarEscoles(escolaController);
                    break;

                case 2:
                    gestionarSectors(sectorController);
                    break;

                case 3:
                    gestionarVies(viaController);
                    break;

                case 4:
                    gestionarEscaladors(escaladorController);
                    break;

                case 0:
                    Vista.info("Sortint...");
                    break;
            }

        } while (opcio != 0);

        Vista.ok("Programa finalitzat");
    }

    // ===============================
    // MENÚS SECUNDARIS
    // ===============================

    private static void gestionarEscoles(EscolaController controller) {

        int opcio;

        do {
            Menus.menuCRUD("Escola");
            opcio = InputReader.llegirOpcio("Opció", 0, 5);

            try {
                switch (opcio) {
                    //try catch() dins del case  //funció amb try catch() //validar amb inputReader
                    case 1:
                        String nom = InputReader.llegir("Nom escola");
                        String lloc = InputReader.llegir("Lloc");

                        controller.crearEscola(nom, lloc);
                        Vista.ok("Escola creada");
                        break;

                    case 2:
                        Escola escolaModificat = Vista.modificarEscola();
                        controller.actualitzar(escolaModificat);

                    case 3:
                        String nomEscole = InputReader.llegir("Nom de l'escola per buscar");

                        controller.obtenirPerNom(nomEscole);

                    case 4:
                        controller.llistarEscoles();
                        break;

                    case 0:
                        break;
                }

            } catch (Exception e) {
                Vista.error(e.getMessage());
            }

        } while (opcio != 0);
    }

    private static void gestionarSectors(SectorController controller) {

        int opcio;

        do {
            Menus.menuCRUD("Sector");
            opcio = InputReader.llegirOpcio("Opció", 0, 5);

            try {
                switch (opcio) {

                    case 1:
                        String nom = InputReader.llegir("Nom sector");
                        controller.crearSector(nom);
                        Vista.ok("Sector creat");
                        break;

                    case 2:
                        Sector sectorEdit = Vista.modificarSector();
                        controller.actualitzar(sectorEdit);

                    case 3:
                        int idSector = InputReader.llegirInt("Id del sector per buscar");
                        controller.obtenirPerId(idSector);

                    case 4:
                        controller.llistarSectors();
                        break;
                }

            } catch (Exception e) {
                Vista.error(e.getMessage());
            }

        } while (opcio != 0);
    }

    private static void gestionarVies(ViaController controller) {

        int opcio;

        do {
            Menus.menuCRUD("Via");
            opcio = InputReader.llegirOpcio("Opció", 0, 5);

            try {
                switch (opcio) {

                    case 1:
                        String nom = InputReader.llegir("Nom via");
                        controller.crearVia(nom);
                        Vista.ok("Via creada");
                        break;

                    case 2:


                    case 4:
                        controller.llistarVies();
                        break;
                }

            } catch (Exception e) {
                Vista.error(e.getMessage());
            }

        } while (opcio != 0);
    }

    private static void gestionarEscaladors(EscaladorController controller) {

        int opcio;

        do {
            Menus.menuCRUD("Escalador");
            opcio = InputReader.llegirOpcio("Opció", 0, 5);

            try {
                switch (opcio) {

                    case 1:
                        String nom = InputReader.llegir("Nom");
                        controller.crearEscalador(nom);
                        Vista.ok("Escalador creat");
                        break;

                    case 4:
                        controller.llistarEscaladors();
                        break;
                }

            } catch (Exception e) {
                Vista.error(e.getMessage());
            }

        } while (opcio != 0);
    }
}