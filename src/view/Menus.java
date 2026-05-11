package view;

public class Menus {
    // --- MENU PRINCIPAL ---
    public static void menuPrincipal() {
        String[] opcions = {
                "1.  Gestionar Escoles",
                "2.  Gestionar Sectors",
                "3.  Gestionar Vies",
                "4.  Gestionar Escaladors",
                "5.  Gestionar Llargs",
                "6.  Consultes Avançades",

                "0.  Sortir"
        };

        Vista.titol("MENU PRINCIPAL - APLICACIÓ ESCALADA");
        Vista.mostrarMenu(opcions);
    }

    /*public static void menuCRUD(String entitat) {
        String[] opcions = {
                "1.  Crear " + entitat,
                "2.  Modificar " + entitat,
                "3.  Llistar un (per ID)",
                "4.  Llistar tots els " + entitat + "s",
                "5.  Eliminar " + entitat,

                "0.  Tornar al menú principal"
        };

        Vista.titol("GESTIÓ DE " + entitat.toUpperCase());
        Vista.mostrarMenu(opcions);
    }*/

    // --- MENUS ESPECÍFICS (Opcionals si es vol personalitzar el CRUD) ---
    public static void menuEscoles() {
        String[] opcions = {
                "1.  Crear Escola",
                "2.  Modificar Escola",
                "3.  Llistar una Escola (per ID)",
                "4.  Llistar totes les Escoles",
                "5.  Eliminar Escola",
                "6.  Veure vies disponibles de l'escola", // Requeriment específic
                "7.  Veure les vies més llargues d'una escola", // Requeriment específic

                "0.  Tornar al menú principal"
        };
        Vista.titol("GESTIÓ D'ESCOLES");
        Vista.mostrarMenu(opcions);
    }

    public static void menuSectors() {
        String[] opcions = {
                "1.  Crear Sector",
                "2.  Modificar Sector",
                "3.  Llistar un Sector (per ID)",
                "4.  Llistar tots els Sectors",
                "5.  Eliminar Sector",
                "6.  Mostrar sectors amb més de X vies",    // Requeriment avançat

                "0.  Tornar al menú principal"
        };

        Vista.titol("GESTIÓ DE SECTORS");
        Vista.mostrarMenu(opcions);
    }


    public static void menuVies() {
        String[] opcions = {
                "1.  Crear Via",
                "2.  Modificar Via",
                "3.  Llistar una Via (per ID)",
                "4.  Llistar totes les Vies",
                "5.  Eliminar Via",
                "6.  Cercar vies per estat", // Requeriment específic

                "0.  Tornar al menú principal"
        };
        Vista.titol("GESTIÓ DE VIES");
        Vista.mostrarMenu(opcions);
    }

    public static void menuEscaladors() {
        String[] opcions = {
                "1.  Crear Escalador",
                "2.  Modificar Escalador",
                "3.  Llistar un Escalador (per ID)",
                "4.  Llistar tots els Escaladors",
                "5.  Eliminar Escalador",
                "6.  Mostrar escaladors amb el mateix nivell", // Requeriment avançat
                "7.  Filtrar per estil preferit",              // Basat en l'atribut de la BD

                "0.  Tornar al menú principal"
        };

        Vista.titol("GESTIÓ D'ESCALADORS");
        Vista.mostrarMenu(opcions);
    }

    public static void menuLlargs() {
        String[] opcions = {
                "1.  Crear Llarg",
                "2.  Modificar Llarg",
                "3.  Llistar un Llarg (per ID)",
                "4.  Llistar tots els Llargs",
                "5.  Eliminar Llarg",
                "6.  Veure llargs d'una Via",

                "0.  Tornar al menú principal"
        };

        Vista.titol("GESTIÓ DE LLARGS");
        Vista.mostrarMenu(opcions);
    }

    // --- MENU CONSULTES AVANÇADES ---
    public static void menuConsultesAvancades() {
        String[] opcions = {
                "1.  Vies disponibles d'una escola determinada",
                "2.  Cercar vies per rang de dificultat",
                "3.  Cercar vies segons estat (Apte, Construcció, Tancada)",
                "4.  Consultar escoles amb restriccions actives",
                "5.  Sectors amb més de X vies disponibles",
                "6.  Escaladors amb el mateix nivell màxim assolit",
                "7.  Vies que han passat a 'Apte' recentment",
                "8.  Les vies més llargues d'una escola",

                "0.  Tornar al menú principal"
        };

        Vista.titol("CONSULTES I FILTRES AVANÇATS");
        Vista.mostrarMenu(opcions);
    }
}
