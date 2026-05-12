package dao.escalada;

import dao.DAO;
import model.Escola;
import model.Via;

import java.util.List;

/**
 * DAO específic per Escola
 */
public interface EscolaDAO extends DAO<Escola> {

    /**
     * Cercar escola per nom
     */
    Escola getByNom(String nom) throws Exception;

    /**
     * Vies disponibles d'una escola determinada
     */
    List<Via> getViesDisponibles(int idEscola) throws Exception;

    /**
     * Cercar escoles amb restriccions actives
     */
    List<Escola> getAmbRestriccions() throws Exception;
}
