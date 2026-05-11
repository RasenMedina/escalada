package controller;

import dao.escalada.SectorDAO;
import model.Sector;
import view.Vista;

import java.util.List;

public class SectorController {

    private final SectorDAO dao;

    public SectorController(SectorDAO dao) {
        this.dao = dao;
    }

    public void crear(Sector s) throws Exception {
        dao.create(s);
    }

    public Sector obtenirPerId(int id) throws Exception {
        return dao.getById(id);
    }

    public List<Sector> obtenirPerEscola(int idEscola) throws Exception {
        return dao.getByEscola(idEscola);
    }

    public List<Sector> obtenirTots() throws Exception {
        return dao.getAll();
    }

    public void actualitzar(Sector s) throws Exception {
        dao.update(s);
    }

    public void eliminar(int id) throws Exception {
        dao.delete(id);
    }

    public void crearSector(String nom) throws Exception {

        Sector s = new Sector();
        s.setNom(nom);

        dao.create(s);
    }

    public void llistarSectors() throws Exception {
        List<Sector> sectors = dao.getAll();

        for (Sector s : sectors) {
            Vista.mostrarLn(s.toString());
        }
    }
}
