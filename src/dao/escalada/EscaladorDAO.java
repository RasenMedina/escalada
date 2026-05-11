package dao.escalada;

import dao.DAO;
import model.Escalador;

/**
 * DAO específic per Escalador
 */
public interface EscaladorDAO extends DAO<Escalador> {

    /**
     * Cercar per DNI
     */
    Escalador getByDni(String dni) throws Exception;
}
