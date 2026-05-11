package view;

public class Menus {

    public static void menuPrincipal() {
        String[] opcions = {
                "Gestionar escoles",
                "Gestionar sectors",
                "Gestionar vies",
                "Gestionar escaladors",
                "Sortir"
        };

        Vista.titol("MENU PRINCIPAL");
        Vista.mostrarMenu(opcions);
    }

    public static void menuCRUD(String entitat) {
        String[] opcions = {
                "Crear " + entitat,
                "Modificar " + entitat,
                "Llistar un",
                "Llistar tots",
                "Eliminar",
                "Tornar enrere"
        };

        Vista.titol("GESTIÓ " + entitat.toUpperCase());
        Vista.mostrarMenu(opcions);
    }
}
