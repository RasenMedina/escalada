package dao.escalada;

import dao.DAO;
import model.Assoliment;

import java.util.List;

/**
 * DAO específic per Assoliment
 */
public interface AssolimentDAO extends DAO<Assoliment> {

    /**
     * Obtenir assoliments d'un escalador
     */
    List<Assoliment> getByEscalador(int idEscalador) throws Exception;

    /**
     * Obtenir assoliments d'una via
     */
    List<Assoliment> getByVia(int idVia) throws Exception;

    /**
     * Comprovar si existeix un assoliment
     */
    boolean exists(int idEscalador, int idVia) throws Exception;
}
