package dao.escalada;

import dao.DAO;
import model.Via;

import java.util.List;

/**
 * DAO específic per Via
 */
public interface ViaDAO extends DAO<Via> {

    /**
     * Obtenir vies d'un sector
     */
    List<Via> getBySector(int idSector) throws Exception;

    /**
     * Obtenir vies per tipus
     */
    List<Via> getByTipus(String tipusVia) throws Exception;

    /**
     * Obtenir vies per dificultat
     */
    List<Via> getByGrau(String grau) throws Exception;

    /**
     * Obtenir vies per estat
     */
    List<Via> getByEstat(String estat) throws Exception;

    /**
     * Cercar via per nom dins un sector
     */
    Via getByNomAndSector(String nom, int idSector) throws Exception;
}
