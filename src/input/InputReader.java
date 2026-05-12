package input;

import model.*;
import view.Vista;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Classe utilitzada per llegir dades des de teclat
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

    /**
     * Llegeix l'estat d'una via
     */
    public static String llegirEstatVia() {
        return llegir("Estat (apte/construccio/tancada)");
    }

// -------------------- ENTITATS --------------------

    /**
     * Llegeix una escola per teclat
     */
    public static Escola llegirEscola() {
        while (true) {
            try {
                Escola e = new Escola();

                e.setIdEscola(llegirInt("ID escola"));
                e.setNom(llegir("Nom escola"));
                e.setLloc(llegir("Població"));
                e.setPopularitat(llegir("Popularitat"));
                e.setAproximacio(llegir("Aproximació"));
                return e;
            } catch (Exception e) {
                Vista.error(e.getMessage());
            }
        }
    }

    /**
     * Llegeix un sector per teclat
     */
    public static Sector llegirSector() {
        while (true) {
            try {
                Sector s = new Sector();

                s.setIdSector(llegirInt("ID sector"));
                s.setIdEscola(llegirInt("ID escola"));
                s.setNom(llegir("Nom sector"));
                s.setLatitud(llegirDouble("Latitud"));
                s.setLongitud(llegirDouble("Longitud"));
                s.setAproximacio(llegir("Aproximació"));
                s.setPopularitat(llegir("Popularitat"));
                s.setEsGel(llegirBoolean("És sector de gel?"));
                return s;
            } catch (Exception e) {
                Vista.error(e.getMessage());
            }
        }
    }

    /**
     * Llegeix una via per teclat
     */
    public static Via llegirVia() {

        while (true) {
            try {
                Via v = new Via();

                // PK
                v.setIdVia(llegirInt("ID via"));

                v.setIdSector(llegirInt("ID sector"));
                v.setIdEscaladorCreador(llegirInt("ID escalador creador"));

                v.setNom(llegir("Nom via"));

                v.setDataCreacio(llegirData("Data de creació"));


                // Només llegim string, validació al setter
                v.setTipusVia(llegir("Tipus via (esportiva/classica/gel)"));
                v.setGrauDificultat(llegir("Grau de dificultat"));
                v.setAncoratge(llegir("Ancoratge"));
                v.setOrientacio(llegir("Orientació"));
                v.setTipusRoca(llegir("Tipus roca"));

                // Estat amb lògica condicional
                String estat = llegir("Estat (apte/construccio/tancada)");
                v.setEstat(estat);

                if (!estat.equalsIgnoreCase("apte")) {
                    v.setMotiuNoApte(llegir("Motiu no apte"));
                    v.setDataIniciNoApte(llegirData("Data inici no apte"));
                    v.setDataFiNoApte(llegirData("Data fi no apte"));
                } else {
                    v.setMotiuNoApte(null);
                    v.setDataIniciNoApte(null);
                    v.setDataFiNoApte(null);
                }

                return v;

            } catch (Exception e) {
                Vista.error(e.getMessage());
            }
        }
    }

    /**
     * Llegeix un escalador per teclat
     */
    public static Escalador llegirEscalador() {
        while (true) {
            try {
                Escalador e = new Escalador();

                e.setIdEscalador(llegirInt("ID escalador"));
                e.setDni(llegir("DNI"));
                e.setNom(llegir("Nom"));
                e.setCognoms(llegir("Cognoms"));
                e.setAlias(llegir("Àlies"));
                e.setDataNaix(llegirData("Data naixement"));
                e.setEstilPref(llegir("Estil preferit"));

                return e;

            } catch (Exception ex) {
                Vista.error(ex.getMessage());
            }
        }
    }

    /**
     * Llegeix un llarg per teclat
     */
    public static Llarg llegirLlarg() {
        while (true) {
            try {
                Llarg l = new Llarg();

                l.setIdLlarg(llegirInt("ID llarg"));
                l.setIdVia(llegirInt("ID via"));
                l.setOrdre(llegirInt("Ordre"));
                l.setLlargada(llegirInt("Llargada"));
                l.setGrauDificultat(llegir("Grau dificultat"));

                return l;

            } catch (Exception e) {
                Vista.error(e.getMessage());
            }
        }
    }

    /**
     * Llegeix un assoliment per teclat
     */
    public static Assoliment llegirAssoliment() {
        while (true) {
            try {
                Assoliment a = new Assoliment();

                a.setIdAssoliment(llegirInt("ID assoliment"));
                a.setIdEscalador(llegirInt("ID escalador"));
                a.setIdVia(llegirInt("ID via"));
                a.setDataAssoliment(llegirData("Data assoliment"));

                return a;

            } catch (Exception e) {
                Vista.error(e.getMessage());
            }
        }
    }
}