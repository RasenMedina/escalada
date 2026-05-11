package controller;

import dao.escalada.EscaladorDAO;
import model.Escalador;
import view.Vista;

import java.util.List;

public class EscaladorController {

    private final EscaladorDAO dao;

    public EscaladorController(EscaladorDAO dao) {
        this.dao = dao;
    }

    public void crear(Escalador e) throws Exception {
        dao.create(e);
    }

    public Escalador obtenirPerId(int id) throws Exception {
        return dao.getById(id);
    }

    public Escalador obtenirPerDni(String dni) throws Exception {
        return dao.getByDni(dni);
    }

    public List<Escalador> obtenirTots() throws Exception {
        return dao.getAll();
    }

    public void actualitzar(Escalador e) throws Exception {
        dao.update(e);
    }

    public void eliminar(int id) throws Exception {
        dao.delete(id);
    }

    public void crearEscalador(String nom) throws Exception {

        Escalador e = new Escalador();
        e.setNom(nom);

        dao.create(e);
    }

    public void llistarEscaladors() throws Exception {
        List<Escalador> escaladors = dao.getAll();

        for (Escalador e : escaladors) {
            Vista.mostrarLn(e.toString());
        }
    }
}
