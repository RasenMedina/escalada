package dao.escalada;

import dao.DAO;
import model.Llarg;

import java.util.List;

/**
 * DAO específic per Llarg
 */
public interface LlargDAO extends DAO<Llarg> {

    /**
     * Obtenir tots els llargs d'una via
     */
    List<Llarg> getByVia(int idVia) throws Exception;

    /**
     * Obtenir un llarg concret dins d'una via
     */
    Llarg getByViaAndNum(int idVia, int num) throws Exception;
}
