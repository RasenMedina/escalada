package controller;

import dao.escalada.EscolaDAO;
import model.Escola;
import view.Vista;

import java.util.List;

public class EscolaController {

    private final EscolaDAO dao;

    public EscolaController(EscolaDAO dao) {
        this.dao = dao;
    }

    public void crear(Escola e) throws Exception {
        dao.create(e);
    }

    public Escola obtenirPerId(int id) throws Exception {
        return dao.getById(id);
    }

    public Escola obtenirPerNom(String nom) throws Exception {
        return dao.getByNom(nom);
    }

    public List<Escola> obtenirTotes() throws Exception {
        return dao.getAll();
    }

    public void actualitzar(Escola e) throws Exception {
        dao.update(e);
    }

    public void eliminar(int id) throws Exception {
        dao.delete(id);
    }

    public void crearEscola(String nom, String lloc) throws Exception {

        Escola escola = new Escola();
        escola.setNom(nom);
        escola.setLloc(lloc);

        dao.create(escola);
    }

    public void llistarEscoles() throws Exception {
        List<Escola> escoles = dao.getAll();

        for (Escola e : escoles) {
            Vista.mostrarLn(e.toString());
        }
    }
}
