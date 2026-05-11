package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe que representa un Assoliment dins del sistema d'escalada
 */
public class Assoliment {

    /** ATRIBUTS */

    /** id que identifica l'escalador (PK) */
    private int idAssoliment;
    /** id que identifica l'escalador (FK + NN) */
    private int idEscalador;
    /** id que identifica la via (FK + NN) */
    private int idVia;
    /** data de l'assoliment */
    private LocalDate dataAssoliment;

    /**
     * Constructor buit
     */
    public Assoliment() {
    }

    /**
     * Constructor complet
     */
    public Assoliment(int idAssoliment, int idEscalador, int idVia, LocalDate dataAssoliment) {
        inicialitzar(idAssoliment, idEscalador, idVia, dataAssoliment);
    }

    /**
     * Constructor còpia
     */
    public Assoliment(Assoliment altre) {
        inicialitzar(altre.idAssoliment, altre.idEscalador, altre.idVia, altre.dataAssoliment);
    }

    /**
     * Mètode d'inicialització
     */
    private void inicialitzar(int idAssoliment, int idEscalador, int idVia, LocalDate dataAssoliment) {
        setIdAssoliment(idAssoliment);
        setIdEscalador(idEscalador);
        setIdVia(idVia);
        setDataAssoliment(dataAssoliment);
    }

    /** SETTERS amb validació */

    public void setIdAssoliment (int idAssoliment) {
        if (idAssoliment < 0)
            throw new IllegalArgumentException("ID assoliment no vàlid");
        this.idAssoliment = idAssoliment;
    }


    public void setIdEscalador(int idEscalador) {
        if (idEscalador < 0)
            throw new IllegalArgumentException("ID escalador no vàlid");
        this.idEscalador = idEscalador;
    }

    public void setIdVia(int idVia) {
        if (idVia < 0)
            throw new IllegalArgumentException("ID via no vàlid");
        this.idVia = idVia;
    }

    public void setDataAssoliment(LocalDate dataAssoliment) {
        if (dataAssoliment != null && dataAssoliment.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Data d'assoliment no vàlida");
        this.dataAssoliment = dataAssoliment;
    }

    /** GETTERS */

    public int getIdAssoliment() {
        return idAssoliment;
    }

    public int getIdEscalador() {
        return idEscalador;
    }

    public int getIdVia() {
        return idVia;
    }

    public LocalDate getDataAssoliment() {
        return dataAssoliment;
    }

    /** toString */
    @Override
    public String toString() {
        return  "Assoliment: " + idAssoliment + "\n" +
                "Escalador: " + idEscalador + "\n" +
                "Via: " + idVia + "\n" +
                "Data: " + dataAssoliment + "\n";
    }

    /** equals */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assoliment)) return false;
        Assoliment that = (Assoliment) o;
        return idAssoliment == that.idAssoliment;
    }

    /** hashCode */
    @Override
    public int hashCode() {
        return Objects.hash(idAssoliment);
    }
}
