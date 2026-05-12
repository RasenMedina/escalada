package view;

import controller.EscolaController;

/**
 * Classe encarregada de mostrar tots els menús de l'aplicació.
 *
 * Aquesta classe pertany a la capa View del patró MVC i
 * centralitza totes les opcions de navegació disponibles
 * per a l'usuari.
 *
 * Cada mètode mostra un menú específic relacionat amb
 * una entitat o funcionalitat del sistema.
 *
 * Projecte: Aplicació Escalada JDBC
 * Autors: Hajar Rahmani i Rasen Medinia
 */
public class Menus {

    /**
     * Mostra el menú principal de l'aplicació.
     */
    // --- MENU PRINCIPAL ---
    public static void menuPrincipal() {
        String[] opcions = {
                "Gestionar Escoles",
                "Gestionar Sectors",
                "Gestionar Vies",
                "Gestionar Escaladors",
                "Gestionar Llargs",
                "Gestionar Assoliments",
                "Consultes Avançades",

                "Sortir"
        };

        Vista.titol("MENU PRINCIPAL - APLICACIÓ ESCALADA");
        Vista.mostrarMenu(opcions);
    }

    /*public static void menuCRUD(String entitat) {
        String[] opcions = {
                "Crear " + entitat,
                "Modificar " + entitat,
                "Llistar un (per ID)",
                "Llistar tots els " + entitat + "s",
                "Eliminar " + entitat,

                "Tornar al menú principal"
        };

        Vista.titol("GESTIÓ DE " + entitat.toUpperCase());
        Vista.mostrarMenu(opcions);
    }*/

    /**
     *  Mostra el menú de gestió d'escoles.
     */
    // --- MENUS ESPECÍFICS (personalitzar el CRUD) ---
    public static void menuEscoles(EscolaController controller) {
        String[] opcions = {
                "Crear Escola",
                "Modificar Escola",
                "Llistar una Escola (per ID)",
                "Llistar totes les Escoles",
                "Eliminar Escola",

                "Tornar al menú principal"
        };
        Vista.titol("GESTIÓ D'ESCOLES");
        Vista.mostrarMenu(opcions);
    }

    /**
     *  Mostra el menú de gestió de sectors.
     */
    public static void menuSectors() {
        String[] opcions = {
                "Crear Sector",
                "Modificar Sector",
                "Llistar un Sector (per ID)",
                "Llistar tots els Sectors",
                "Eliminar Sector",

                "Tornar al menú principal"
        };

        Vista.titol("GESTIÓ DE SECTORS");
        Vista.mostrarMenu(opcions);
    }


    /**
     *  Mostra el menú de gestió de vies.
     */
    public static void menuVies() {
        String[] opcions = {
                "Crear Via",
                "Modificar Via",
                "Llistar una Via (per ID)",
                "Llistar totes les Vies",
                "Eliminar Via",

                "Tornar al menú principal"
        };
        Vista.titol("GESTIÓ DE VIES");
        Vista.mostrarMenu(opcions);
    }


    /**
     *  Mostra el menú de gestió d'escaladors.
     */
    public static void menuEscaladors() {
        String[] opcions = {
                "Crear Escalador",
                "Modificar Escalador",
                "Llistar un Escalador (per ID)",
                "Llistar tots els Escaladors",
                "Eliminar Escalador",
                "Mostrar escaladors amb el mateix nivell", // Requeriment avançat
                "Filtrar per estil preferit",              // Basat en l'atribut de la BD

                "Tornar al menú principal"
        };

        Vista.titol("GESTIÓ D'ESCALADORS");
        Vista.mostrarMenu(opcions);
    }


    /**
     *  Mostra el menú de gestió de llargs.
     */
    public static void menuLlargs() {
        String[] opcions = {
                "Crear Llarg",
                "Modificar Llarg",
                "Llistar un Llarg (per ID)",
                "Llistar tots els Llargs",
                "Eliminar Llarg",
                "Veure llargs d'una Via",

                "Tornar al menú principal"
        };

        Vista.titol("GESTIÓ DE LLARGS");
        Vista.mostrarMenu(opcions);
    }

    /**
     * Mostrar el menu assoliments
     */
    public static void menuAssoliments() {
        String[] opcions = {
                "Registrar nou assoliment",
                "Modificar dades d'un assoliment",
                "Llistar assoliments d'un escalador",
                "Llistar assoliments d'una via",
                "Eliminar registre d'assoliment",

                "Tornar al menú principal"
        };

        Vista.titol("GESTIÓ D'ASSOLIMENTS");
        Vista.mostrarMenu(opcions);
    }


    /**
     * Mostra el menú de consultes avançades.
     *
     * Conté consultes complexes relacionades amb:
     * - vies disponibles
     * - dificultats
     * - restriccions
     * - estadístiques
     * - filtres avançats
     */
    // --- MENU CONSULTES AVANÇADES ---
    public static void menuConsultesAvancades() {
        String[] opcions = {
                "Vies disponibles d'una escola determinada",
                "Cercar vies per rang de dificultat",
                "Cercar vies segons estat (Apte, Construcció, Tancada)",
                "Consultar escoles amb restriccions actives",
                "Sectors amb més de X vies disponibles",
                "Escaladors amb el mateix nivell màxim assolit",
                "Vies que han passat a 'Apte' recentment (últims 30 dies)",
                "Les vies més llargues d'una escola (5 més llargues)",

                "Tornar al menú principal"
        };

        Vista.titol("CONSULTES I FILTRES AVANÇATS");
        Vista.mostrarMenu(opcions);
    }
}
