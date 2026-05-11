package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe que representa un Escalador dins del sistema d'escalada
 */
public class Escalador {

    /** ATRIBUTS */

    /** id que identifica l'escalador (PK) */
    private int idEscalador;
    /** DNI de l'escalador (UNIQUE + NN) */
    private String dni;
    /** nom de l'escalador (NN) */
    private String nom;
    /** cognoms de l'escalador */
    private String cognoms;
    /** alias de l'escalador */
    private String alias;
    /** data de naixament de l'escalador */
    private LocalDate dataNaix;
    /** estil preferit (esportiva, clàssica, gel) */
    private String estilPref;

    /** Constants */
    private static final String REGEX_DOC =
            "^[0-9]{8}[A-Z]$" +                 // DNI
                    "|^[XYZ][0-9]{7}[A-Z]$" +          // NIE
                    "|^[A-Z0-9]{6,9}$";                // Passaport (genèric)


    /**
     * Constructor buit
     */
    public Escalador() {
    }

    /**
     * Constructor complet
     */
    public Escalador(int idEscalador, String dni, String nom, String cognoms,
                     String alias, LocalDate dataNaix, String estilPref) {
        inicialitzar(idEscalador, dni, nom, cognoms, alias, dataNaix, estilPref);
    }

    /**
     * Constructor còpia
     */
    public Escalador(Escalador altre) {
        inicialitzar(altre.idEscalador, altre.dni, altre.nom, altre.cognoms,
                altre.alias, altre.dataNaix, altre.estilPref);
    }

    /**
     * Mètode d'inicialització
     */
    private void inicialitzar(int idEscalador, String dni, String nom, String cognoms, String alias, LocalDate dataNaix, String estilPref) {
        setIdEscalador(idEscalador);
        setDni(dni);
        setNom(nom);
        setCognoms(cognoms);
        setAlias(alias);
        setDataNaix(dataNaix);
        setEstilPref(estilPref);
    }

    /** SETTERS amb validació */

    private void setIdEscalador(int idEscalador) {
        if (idEscalador < 0) throw new IllegalArgumentException("ID escalador no vàlid");
        this.idEscalador = idEscalador;
    }

    public void setDni(String dni) {
        if (dni == null || !dni.matches(REGEX_DOC))
            throw new IllegalArgumentException("Document identificatiu no vàlid");
        this.dni = dni.toUpperCase(); //normalitzem el dni
    }

    public void setNom(String nom) {
        if (nom == null || nom.isBlank())
            throw new IllegalArgumentException("Nom d'escalador no vàlid");
        this.nom = nom.trim(); //normalitzem nom
    }

    public void setCognoms(String cognoms) {
        if (cognoms != null && cognoms.isBlank())
            throw new IllegalArgumentException("Cognoms no vàlids");
        this.cognoms = cognoms;
    }

    public void setAlias(String alias) {
        if (alias != null && alias.isBlank())
            throw new IllegalArgumentException("Alias no vàlid");
        this.alias = alias;
    }

    public void setDataNaix(LocalDate dataNaix) {
        if (dataNaix != null && dataNaix.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Data de naixement no vàlida");
        this.dataNaix = dataNaix;
    }

    public void setEstilPref(String estilPref) {
        if (estilPref == null) {
            this.estilPref = null;
            return;
        }

        String e = estilPref.toLowerCase();

        if (!e.equals("esportiva") && !e.equals("clàssica")
                && !e.equals("classica") && !e.equals("gel")) {
            throw new IllegalArgumentException("Estil no vàlid");
        }
        if (e.equals("classica")) e = "clàssica"; //normalitzem accent
        this.estilPref = e;
    }

    /** GETTERS */

    public int getIdEscalador() {
        return idEscalador;
    }

    public String getDni() {
        return dni;
    }

    public String getNom() {
        return nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public String getAlias() {
        return alias;
    }

    public LocalDate getDataNaix() {
        return dataNaix;
    }

    public String getEstilPref() {
        return estilPref;
    }

    /** toString */
    @Override
    public String toString() {
        return  "Nom: " + nom + "\n" +
                "Cognoms: " + cognoms + "\n" +
                "DNI: " + dni + "\n" +
                "Alias: " + alias + "\n" +
                "Estil preferit: " + estilPref + "\n";
    }

    /** equals */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Escalador)) return false;
        Escalador that = (Escalador) o;
        return idEscalador == that.idEscalador;
    }

    /** hashCode */
    @Override
    public int hashCode() {
        return Objects.hash(idEscalador);
    }
}
