package input;

import view.Vista;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Classe utilitària per llegir dades des de teclat
 */
public class InputReader {

    /** Scanner únic per tota l'aplicació */
    private static final Scanner scan = new Scanner(System.in);

    /**
     * Llegeix un String no buit
     */
    public static String llegir(String missatge) {
        while (true) {
            Vista.mostrar(missatge + ": ");
            String text = scan.nextLine();

            if (text != null) {
                text = text.trim();
                if (!text.isEmpty()) return text;
            }

            Vista.error("El text no pot estar buit.");
        }
    }

    /**
     * Llegeix un boolean (S/N)
     */
    public static boolean llegirBoolean(String missatge) {
        while (true) {
            String resposta = llegir(missatge + " (S/N)").toUpperCase();

            if (resposta.equals("S")) return true;
            if (resposta.equals("N")) return false;

            Vista.error("Resposta no vàlida. Escriu S o N.");
        }
    }

    /**
     * Llegeix un enter
     */
    public static int llegirInt(String missatge) {
        while (true) {
            try {
                return Integer.parseInt(llegir(missatge));
            } catch (NumberFormatException e) {
                Vista.error("Has d'introduir un enter vàlid.");
            }
        }
    }

    /**
     * Llegeix un double
     */
    public static double llegirDouble(String missatge) {
        while (true) {
            try {
                return Double.parseDouble(llegir(missatge));
            } catch (NumberFormatException e) {
                Vista.error("Has d'introduir un decimal vàlid.");
            }
        }
    }

    /**
     * Llegeix una opció dins un rang
     */
    public static int llegirOpcio(String missatge, int min, int max) {
        while (true) {
            int opcio = llegirInt(missatge);

            if (opcio >= min && opcio <= max) return opcio;

            Vista.error("Opció fora de rang (" + min + " - " + max + ")");
        }
    }

    /**
     * Llegeix una data yyyy-MM-dd
     */
    public static LocalDate llegirData(String missatge) {
        while (true) {
            try {
                String text = llegir(missatge);

                if (!text.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    Vista.error("Format incorrecte. Usa yyyy-MM-dd");
                    continue;
                }

                return LocalDate.parse(text);

            } catch (Exception e) {
                Vista.error("Data no vàlida.");
            }
        }
    }
}