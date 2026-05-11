package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe que representa una Via dins del sistema d'escalada
 */
public class Via {

    /** ATRIBUTS */

    /** id que identifica la Via (PK) */
    private int idVia;
    /** id del sector al qual pertany la via (FK + NN) */
    private int idSector;
    /** nom de la via (NN) */
    private String nom;
    /** id de l'escalador que ha creat la via (FK + NN) */
    private int idEscaladorCreador;
    /** data de creació de la via */
    private LocalDate dataCreacio;
    /** tipus de via (esportiva, clàssica, gel) (NN) */
    private String tipusVia;
    /** [N,NE,NO,SE,SO,E,O,S] */
    private String orientacio;
    /**  [4,4+,5,5+,6a,6a+,6b,6b+,6c,6c+,7a,7a+,7b,7b+,7c,7c+,8a,8a+,8b,8b+,8c,8c+,9a,9a+,9b,9b+,9c,9c+] */
    private String grauDificultat;
    /** [conglomerat, granit, calcaria, arenisca, altres] */
    private String tipusRoca;
    /** [friends, tascons, bagues, pitons, Tricams, BigBros, spits, parabolts, químics] */
    private String ancoratge;
    /** [Apte, construcció, tancada] (NN) */
    private String estat;
    /** breu descripció de per què no està apte */
    private String motiuNoApte;
    /** data a partir de la qual la via no està apte */
    private LocalDate dataIniciNoApte;
    /** data fins a la qual la via no està apte */
    private LocalDate dataFiNoApte;

    /**
     * Constructor buit
     */
    public Via() {
    }

    /**
     * Constructor complet
     */
    public Via(int idVia, int idSector, String nom, int idEscaladorCreador, LocalDate dataCreacio, String tipusVia, String orientacio, String grauDificultat, String tipusRoca, String ancoratge, String estat, String motiuNoApte, LocalDate dataIniciNoApte, LocalDate dataFiNoApte) {
        inicialitzar(idVia, idSector, nom, idEscaladorCreador, dataCreacio, tipusVia, orientacio, grauDificultat, tipusRoca, ancoratge, estat, motiuNoApte, dataIniciNoApte, dataFiNoApte);
    }

    /**
     * Constructor còpia
     */
    public Via(Via altre) {
        inicialitzar(altre.idVia, altre.idSector, altre.nom, altre.idEscaladorCreador, altre.dataCreacio, altre.tipusVia, altre.orientacio, altre.grauDificultat, altre.tipusRoca, altre.ancoratge, altre.estat, altre.motiuNoApte, altre.dataIniciNoApte, altre.dataFiNoApte);
    }

    /**
     * Mètode d'inicialització
     */
    private void inicialitzar(int idVia, int idSector, String nom, int idEscaladorCreador, LocalDate dataCreacio, String tipusVia, String orientacio, String grauDificultat, String tipusRoca, String ancoratge, String estat, String motiuNoApte, LocalDate dataIniciNoApte, LocalDate dataFiNoApte){
        setIdVia(idVia);
        setIdSector(idSector);
        setNom(nom);
        setIdEscaladorCreador(idEscaladorCreador);
        setDataCreacio(dataCreacio);
        setTipusVia(tipusVia);
        setOrientacio(orientacio);
        setGrauDificultat(grauDificultat);
        setTipusRoca(tipusRoca);
        setAncoratge(ancoratge);
        setEstat(estat);
        setMotiuNoApte(motiuNoApte);
        setDataIniciNoApte(dataIniciNoApte);
        setDataFiNoApte(dataFiNoApte);
    }

    /** SETTERS amb validació */

    private void setIdVia(int idVia) {
        if (idVia < 0) throw new IllegalArgumentException("ID via no vàlid");
        this.idVia = idVia;
    }

    public void setIdSector(int idSector) {
        if (idSector < 0) throw new IllegalArgumentException("ID sector no vàlid");
        this.idSector = idSector;
    }

    public void setNom(String nom) {
        if (nom == null || nom.isBlank())
            throw new IllegalArgumentException("Nom no vàlid");
        this.nom = nom.trim(); //normalitzem nom
    }

    public void setIdEscaladorCreador(int idEscaladorCreador) {
        if (idEscaladorCreador < 0) throw new IllegalArgumentException("ID escalador creador no vàlid");
        this.idEscaladorCreador = idEscaladorCreador;
    }

    public void setDataCreacio(LocalDate dataCreacio) {
        if (dataCreacio != null && dataCreacio.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Data de naixement no vàlida");
        this.dataCreacio = dataCreacio;
    }

    public void setTipusVia(String tipusVia) {
        if (tipusVia == null)
            throw new IllegalArgumentException("Tipus via no pot ser null");

        String t = tipusVia.toLowerCase();

        if (!t.equals("esportiva") && !t.equals("clàssica") &&
                !t.equals("classica") && !t.equals("gel")) {
            throw new IllegalArgumentException("Tipus de via no vàlid");
        }

        if (t.equals("classica")) t = "clàssica"; //normalitzem accent
        this.tipusVia = t;

        // Revalidació (per si de cas)
        if (this.grauDificultat != null)
            setGrauDificultat(this.grauDificultat);

        if (this.ancoratge != null)
            setAncoratge(this.ancoratge);
    }

    public void setOrientacio(String orientacio) {
        if (orientacio == null){
            this.orientacio = null;
            return;
        }

        if (!orientacio.toUpperCase().matches("N|NE|NO|SE|SO|E|O|S"))
            throw new IllegalArgumentException("Orientació no vàlida");

        this.orientacio = orientacio;
    }

    public void setGrauDificultat(String grauDificultat) {
        if (grauDificultat == null){
            this.grauDificultat = null;
            return;
        }

        if (tipusVia == null)
            throw new IllegalStateException("Primer s'ha de definir el tipus de via");

        grauDificultat = grauDificultat.trim().toLowerCase();

        String regex;

        if (tipusVia.equals("esportiva")) {
            regex = "^(4|4\\+|5|5\\+|6[abc]\\+?|7[abc]\\+?|8[abc]\\+?|9[abc]\\+?)$";
        } else {
            regex = "^(4|4\\+|5|5\\+|6[abc]\\+?|7[abc]\\+?|8[ab]\\+?)$";
        }

        if (!grauDificultat.matches(regex))
            throw new IllegalArgumentException("Grau no vàlid");

        this.grauDificultat = grauDificultat;
    }

    public void setTipusRoca(String tipusRoca) {
        if (tipusRoca == null){
            this.tipusRoca = null;
            return;
        }

        String t = tipusRoca.toLowerCase();

        if (!t.equals("conglomerat") && !t.equals("granit") &&
                !t.equals("calcaria") && !t.equals("arenisca") && !t.equals("altres")) {
            throw new IllegalArgumentException("Tipus roca no vàlid");
        }

        this.tipusRoca = t;
    }

    public void setAncoratge(String ancoratge) {
        if (ancoratge == null){
            this.ancoratge = null;
            return;
        }

        if (tipusVia == null)
            throw new IllegalStateException("Primer s'ha de definir el tipus de via");

        String a = ancoratge.toLowerCase();

        boolean valid = false;

        switch (tipusVia) {
            case "esportiva":
                valid = a.equals("spits") || a.equals("parabolts") || a.equals("químics");
                break;

            case "clàssica":
            case "classica":
                valid = a.equals("friends") || a.equals("tascons") || a.equals("bagues") ||
                        a.equals("pitons") || a.equals("tricams") || a.equals("bigbros") ||
                        a.equals("spits") || a.equals("parabolts") || a.equals("químics");
                break;

            case "gel":
                valid = a.equals("friends") || a.equals("tascons") || a.equals("bagues") ||
                        a.equals("pitons") || a.equals("tricams") || a.equals("bigbros");
                break;
        }

        if (!valid)
            throw new IllegalArgumentException("Ancoratge no vàlid per aquest tipus de via");

        this.ancoratge = a;
    }

    public void setEstat(String estat) {
        if (estat == null)
            throw new IllegalArgumentException("Estat null");

        String e = estat.toLowerCase();

        if (!e.equals("apte") && !e.equals("construcció") && !e.equals("construccio") && !e.equals("tancada"))
            throw new IllegalArgumentException("Estat no vàlid");

        if (e.equals("construccio")) e="construcció"; //normalitzem accent
        this.estat = e;
    }

    public void setMotiuNoApte(String motiuNoApte) {
        if (motiuNoApte != null && motiuNoApte.isBlank())
            throw new IllegalArgumentException("Restricció no vàlida");
        this.motiuNoApte = motiuNoApte;
    }

    public void setDataIniciNoApte(LocalDate dataIniciNoApte) {
        if (dataFiNoApte != null && dataIniciNoApte != null && dataFiNoApte.isBefore(dataIniciNoApte))
            throw new IllegalArgumentException("Dates incoherents");
        this.dataIniciNoApte = dataIniciNoApte;
    }

    public void setDataFiNoApte(LocalDate dataFiNoApte) {
        if (dataFiNoApte != null && dataIniciNoApte != null && dataFiNoApte.isBefore(dataIniciNoApte))
            throw new IllegalArgumentException("Dates incoherents");
        this.dataFiNoApte = dataFiNoApte;
    }

    /** GETTERS */

    public int getIdVia() {
        return idVia;
    }

    public int getIdSector() {
        return idSector;
    }

    public String getNom() {
        return nom;
    }

    public int getIdEscaladorCreador() {
        return idEscaladorCreador;
    }

    public LocalDate getDataCreacio() {
        return dataCreacio;
    }

    public String getTipusVia() {
        return tipusVia;
    }

    public String getOrientacio() {
        return orientacio;
    }

    public String getGrauDificultat() {
        return grauDificultat;
    }

    public String getTipusRoca() {
        return tipusRoca;
    }

    public String getAncoratge() {
        return ancoratge;
    }

    public String getEstat() {
        return estat;
    }

    public String getMotiuNoApte() {
        return motiuNoApte;
    }

    public LocalDate getDataIniciNoApte() {
        return dataIniciNoApte;
    }

    public LocalDate getDataFiNoApte() {
        return dataFiNoApte;
    }

    /** toString */
    @Override
    public String toString() {
        return  "Nom: " + nom + "\n" +
                "Sector: " + idSector + "\n" +
                "Creador: " + idEscaladorCreador + "\n" +
                "Tipus: " + tipusVia + "\n" +
                "Estat: " + estat + "\n";
    }

    /** equals */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Via)) return false;
        Via that = (Via) o;
        return idVia == that.idVia;
    }

    /** hashCode */
    @Override
    public int hashCode() {
        return Objects.hash(idVia);
    }

}
