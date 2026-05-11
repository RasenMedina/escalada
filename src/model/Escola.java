package model;

import java.util.Objects;

/**
 * Classe que representa una Escola dins del sistema d'escalada
 */
public class Escola {

    /** ATRIBUTS */

    /** id que identifica l'escola (PK) */
    private int idEscola;
    /** nom de l'escola (UNIQUE + NN) */
    private String nom;
    /** població on es troba l'escola */
    private String lloc;
    /** breu descripció de com arribar */
    private String aproximacio;
    /** popularitat (baixa, mitjana, alta) */
    private String popularitat;

    /**
     * Constructor buit
     */
    public Escola() {
    }

    /**
     * Constructor complet
     */
    public Escola(int idEscola, String nom, String lloc, String aproximacio, String popularitat) {
        inicialitzar(idEscola, nom, lloc, aproximacio, popularitat);
    }

    /**
     * Constructor còpia
     */
    public Escola(Escola altre) {
        inicialitzar(altre.idEscola, altre.nom, altre.lloc, altre.aproximacio, altre.popularitat);
    }

    /**
     * Mètode d'inicialització
     */
    private void inicialitzar(int idEscola, String nom, String lloc, String aproximacio, String popularitat){
        setIdEscola(idEscola);
        setNom(nom);
        setLloc(lloc);
        setAproximacio(aproximacio);
        setPopularitat(popularitat);
    }

    /** SETTERS amb validació */

    private void setIdEscola(int idEscola) {
        if (idEscola < 0) throw new IllegalArgumentException("ID escola no vàlid");
        this.idEscola = idEscola;
    }

    public void setNom(String nom) {
        if (nom == null || nom.isBlank())
            throw new IllegalArgumentException("Nom d'escola no vàlid");
        this.nom = nom.trim(); //normalitzem nom
    }

    public void setLloc(String lloc) {
        if (lloc != null && lloc.isBlank())
            throw new IllegalArgumentException("Lloc no vàlid");
        this.lloc = lloc;
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

    /** GETTERS */

    public int getIdEscola() {
        return idEscola;
    }

    public String getNom() {
        return nom;
    }

    public String getLloc() {
        return lloc;
    }

    public String getAproximacio() {
        return aproximacio;
    }

    public String getPopularitat() {
        return popularitat;
    }

    /** toString */
    @Override
    public String toString() {
        return  "Escola: " + nom + "\n" +
                "Lloc: " + lloc + "\n" +
                "Popularitat: " + popularitat + "\n";
    }

    /** equals */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Escola)) return false;
        Escola that = (Escola) o;
        return idEscola == that.idEscola;
    }

    /** hashCode */
    @Override
    public int hashCode() {
        return Objects.hash(idEscola);
    }
}
