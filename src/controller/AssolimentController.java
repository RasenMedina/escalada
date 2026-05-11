package controller;

import dao.escalada.AssolimentDAO;
import model.Assoliment;

import java.util.List;

public class AssolimentController {

    private final AssolimentDAO dao;

    public AssolimentController(AssolimentDAO dao) {
        this.dao = dao;
    }

    public void registrar(Assoliment a) throws Exception {
        dao.create(a);
    }

    public List<Assoliment> perEscalador(int idEscalador) throws Exception {
        return dao.getByEscalador(idEscalador);
    }

    public List<Assoliment> perVia(int idVia) throws Exception {
        return dao.getByVia(idVia);
    }

    public void eliminar(int idEscalador, int idVia) throws Exception {
        dao.delete(idEscalador, idVia);
    }
}
