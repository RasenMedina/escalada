package dao.escalada;

import dao.DAO;
import model.Sector;

import java.util.List;

/**
 * DAO específic per Sector
 */
public interface SectorDAO extends DAO<Sector> {

    /**
     * Obtenir sectors d'una escola
     */
    List<Sector> getByEscola(int idEscola) throws Exception;

    /**
     * Cercar sector per nom dins una escola
     */
    Sector getByNomAndEscola(String nom, int idEscola) throws Exception;
}
