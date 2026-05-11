package model;

import java.util.Objects;

/**
 * Classe que representa un Sector dins del sistema d'escalada
 */
public class Sector {

    /** ATRIBUTS */

    /** id que identifica el sector (PK) */
    private int idSector;
    /** id de l'escola a la qual pertany el sector (FK + NN) */
    private int idEscola;
    /** nom del sector (NN) */
    private String nom;
    /** longitud en graus del sector */
    private double longitud;
    /** latitud en graus del sector */
    private double latitud;
    /** breu descripció de com arribar */
    private String aproximacio;
    /** popularitat (baixa, mitjana, alta) */
    private String popularitat;
    /** si és un sector de gel o no (NN) */
    private boolean esGel;

    /**
     * Constructor buit
     */
    public Sector() {
    }

    /**
     * Constructor complet
     */
    public Sector(int idSector, int idEscola, String nom, double longitud, double latitud, String aproximacio, String popularitat, boolean esGel) {
        inicialitzar(idSector, idEscola, nom, longitud, latitud, aproximacio, popularitat, esGel);
    }

    /**
     * Constructor còpia
     */
    public Sector(Sector altre) {
        inicialitzar(altre.idSector, altre.idEscola, altre.nom,  altre.longitud, altre.latitud, altre.aproximacio, altre.popularitat, altre.esGel);
    }

    /**
     * Mètode d'inicialització
     */
    private void inicialitzar(int idSector,  int idEscola, String nom, double longitud, double latitud, String aproximacio, String popularitat, boolean esGel){
        setIdSector(idSector);
        setIdEscola(idEscola);
        setNom(nom);
        setLongitud(longitud);
        setLatitud(latitud);
        setAproximacio(aproximacio);
        setPopularitat(popularitat);
        setEsGel(esGel);
    }

    /** SETTERS amb validació */

    private void setIdSector(int idSector) {
        if (idSector < 0) throw new IllegalArgumentException("ID no vàlid");
        this.idSector = idSector;
    }

    public void setIdEscola(int idEscola) {
        if (idEscola < 0) throw new IllegalArgumentException("ID d'escola no vàlid");
        this.idEscola = idEscola;
    }

    public void setNom(String nom) {
        if (nom == null || nom.isBlank())
            throw new IllegalArgumentException("Nom de sector no vàlid");
        this.nom = nom.trim(); //normalitzem nom
    }

    public void setLongitud(double longitud) {
        if (longitud < -180 || longitud > 180)
            throw new IllegalArgumentException("Longitud fora de rang (-180 a 180)");
        this.longitud = longitud;
    }

    public void setLatitud(double latitud) {
        if (latitud < -90 || latitud > 90)
            throw new IllegalArgumentException("Latitud fora de rang (-90 a 90)");
        this.latitud = latitud;
    }

    public void setAproximacio(String aproximacio) {
        if (aproximacio != null && aproximacio.isBlank())
            throw new IllegalArgumentException("Aproximació no vàlida");
        this.aproximacio = aproximacio;
    }

    public void setPopularitat(String popularitat) {
        if (popularitat == null) {
            this.popularitat = null;
            return;
        }

        String p = popularitat.toLowerCase();

        if (!p.equals("baixa") && !p.equals("mitjana")
                && !p.equals("alta")) {
            throw new IllegalArgumentException("Popularitat no vàlida");
        }

        this.popularitat = p;
    }

    public void setEsGel(boolean esGel) {
        this.esGel = esGel;
    }

    /** GETTERS */

    public int getIdSector() {
        return idSector;
    }

    public int getIdEscola() {
        return idEscola;
    }

    public String getNom() {
        return nom;
    }

    public double getLongitud() {
        return longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public String getAproximacio() {
        return aproximacio;
    }

    public String getPopularitat() {
        return popularitat;
    }

    public boolean getEsGel() {
        return esGel;
    }

    /** toString */
    @Override
    public String toString() {
        return  "Sector: " + nom + "\n" +
                "Escola: " + idEscola + "\n" +
                "Popularitat: " + popularitat + "\n" +
                "És de gel: " + (esGel?"Sí":"No") + "\n";
    }

    /** equals */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sector)) return false;
        Sector that = (Sector) o;
        return idSector == that.idSector;
    }

    /** hashCode */
    @Override
    public int hashCode() {
        return Objects.hash(idSector);
    }
}
