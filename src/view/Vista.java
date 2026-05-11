package view;

import input.InputReader;
import model.Escola;
import model.Sector;
import model.Via;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

/**
 * Classe per gestionar la sortida de dades
 */
public class Vista {

    public static final String MODE_CONSOLA = "CONSOLA";
    public static final String MODE_HTML = "HTML";

    private static String mode = MODE_CONSOLA;

    /** Fitxer de sortida opcional */
    private static String outputFile = null;

    public static void setMode(String m) {
        if (m != null) {
            mode = m.toUpperCase();
        }
    }

    public static void setOutputFile(String path) {
        outputFile = path;
    }

    private static String format(String text) {
        if (text == null) return "";

        if (mode.equals(MODE_HTML)) {
            return "<p>" + text + "</p>";
        }

        return text;
    }

    private static void writeToFile(String text) {
        if (outputFile == null) return;

        try (FileWriter fw = new FileWriter(outputFile, true)) {
            fw.write(text + "\n");
        } catch (IOException e) {
            System.out.println("Error escrivint a fitxer");
        }
    }

    public static void mostrar(String text) {
        String formatted = format(text);
        System.out.print(formatted);
        writeToFile(formatted);
    }

    public static void mostrarLn(String text) {
        String formatted = format(text);
        System.out.println(formatted);
        writeToFile(formatted);
    }

    public static void error(String text) {
        mostrarLn("❌ ERROR: " + text);
    }

    public static void info(String text) {
        mostrarLn("ℹ️ " + text);
    }

    public static void ok(String text) {
        mostrarLn("✅ " + text);
    }

    public static void titol(String titol) {
        mostrarLn("\n===== " + titol.toUpperCase() + " =====");
    }

    public static void separador() {
        mostrarLn("----------------------------------");
    }

    public static void mostrarMenu(String[] opcions) {
        for (int i = 0; i < opcions.length - 1; i++) {
            mostrarLn((i + 1) + ". " + opcions[i]);
        }
        mostrarLn("0. " + opcions[opcions.length - 1]);
    }

    public static Sector modificarSector(){
        int id = InputReader.llegirInt("ID del sector a modifier : ");
        int idEscola = InputReader.llegirInt("ID de l'escola a modifier : ");

        String nomNou = InputReader.llegir("Nou nom : ");
        double longiNou = InputReader.llegirDouble("Nova longitud : ");
        double latiNou = InputReader.llegirDouble("Nova latitud : ");
        String aproxNou = InputReader.llegir("Nova aproximació : ");
        String popuNou = InputReader.llegir("Nova popularitat : ");
        boolean gel = InputReader.llegirBoolean("És de gel? (true/false) : ");

        Sector sectorEdit = new Sector(id, idEscola, nomNou, longiNou, latiNou, aproxNou, popuNou, gel);
        return sectorEdit;
    }

    public static Escola modificarEscola(){
        int idEscola = InputReader.llegirInt("Id de l'escola per modificar");

        String nomMod = InputReader.llegir("Nom de l'escola");
        String llocMod = InputReader.llegir("Lloc de l'escole");
        String aprMod = InputReader.llegir("Aproximacio de l'escole");
        String popMod = InputReader.llegir("Lloc de l'escole");

        Escola escolaModificat = new Escola(idEscola,nomMod,llocMod,aprMod,popMod);
        return escolaModificat;
    }

    public static Via modificarVies(){
        int idVia = InputReader.llegirInt("Id de l'escola per modificar");
        int idSector = InputReader.llegirInt("Id de l'escola per modificar");
        String nomM = InputReader.llegir("Nom de l'escola");
        int escaladorCreador = InputReader.llegirInt("Id de l'escola per modificar");
        LocalDate dataCreacio = // pour le terminer de main
        //es la funcion que pide info del usuario para modificar via



    }
}