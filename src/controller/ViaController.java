package controller;

import dao.escalada.ViaDAO;
import model.Via;
import view.Vista;

import java.util.List;

public class ViaController {

    private final ViaDAO dao;

    public ViaController(ViaDAO dao) {
        this.dao = dao;
    }

    public void crear(Via v) throws Exception {
        dao.create(v);
    }

    public Via obtenirPerId(int id) throws Exception {
        return dao.getById(id);
    }

    public List<Via> obtenirPerSector(int idSector) throws Exception {
        return dao.getBySector(idSector);
    }

    public List<Via> obtenirTotes() throws Exception {
        return dao.getAll();
    }

    public void actualitzar(Via v) throws Exception {
        dao.update(v);
    }

    public void eliminar(int id) throws Exception {
        dao.delete(id);
    }

    public void crearVia(String nom) throws Exception {

        Via v = new Via();
        v.setNom(nom);

        dao.create(v);
    }

    public void llistarVies() throws Exception {
        List<Via> vies = dao.getAll();

        for (Via v : vies) {
            Vista.mostrarLn(v.toString());
        }
    }

}