package dao.escalada;

import dao.DAO;
import model.Escola;

/**
 * DAO específic per Escola
 */
public interface EscolaDAO extends DAO<Escola> {

    /**
     * Cercar escola per nom
     */
    Escola getByNom(String nom) throws Exception;
}
