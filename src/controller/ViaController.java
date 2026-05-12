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

    public List<Via> getByRangDificultat(String min, String max) throws Exception {

        if (min == null || min.isBlank()) {
            throw new Exception("La dificultat mínima és obligatòria.");
        }

        if (max == null || max.isBlank()) {
            throw new Exception("La dificultat màxima és obligatòria.");
        }

        return dao.getByRangDificultat(min, max);
    }

    public List<Via> getByEstat(String estat) throws Exception {

        if (estat == null || estat.isBlank()) {
            throw new Exception("L'estat no pot estar buit.");
        }

        return dao.getByEstat(estat);
    }

    public List<Via> getRecentmentAptes() throws Exception {

        return dao.getRecentmentAptes();
    }

    public List<Via> getViesMesLlargues(int idEscola) throws Exception {

        if (idEscola <= 0) {
            throw new Exception("ID escola invàlid.");
        }

        return dao.getViesMesLlargues(idEscola);
    }

}