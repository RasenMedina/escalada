package controller;

import dao.escalada.LlargDAO;
import model.Escola;
import model.Llarg;

import java.util.List;

public class LlargController {

    private final LlargDAO dao;

    public LlargController(LlargDAO dao) {
        this.dao = dao;
    }

    public void afegirLlarg(Llarg l) throws Exception {
        dao.create(l);
    }

    public List<Llarg> obtenirPerVia(int idVia) throws Exception {
        return dao.getByVia(idVia);
    }

    public List<Llarg> obtenirPerId(int idLarg) throws Exception {
        return dao.getByVia(idLarg);
    }

    public void eliminar(int id) throws Exception {
        dao.delete(id);
    }

    public List<Llarg> obtenirTotes() throws Exception {
        return dao.getAll();
    }

    public void actualitzar(Llarg l) throws Exception {
        dao.update(l);
    }
}
