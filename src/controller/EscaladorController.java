package controller;

import dao.escalada.EscaladorDAO;
import model.Escalador;
import view.Vista;

import java.util.List;
import java.util.Collections;

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

    /**
     * Obté una llista d'escaladors que tenen un nivell màxim assolit específic.
     * @param grauDificultat El nivell a cercar (ex: "6a", "7b+")
     * @return Llista d'objectes Escalador
     * @throws Exception Si hi ha un error en la consulta a la BD
     */
    public List<Escalador> getEscaladorsPerNivell(String grauDificultat) throws Exception {
        // Validació bàsica
        if (grauDificultat == null || grauDificultat.trim().isEmpty()) {
            throw new Exception("El nivell no pot estar buit.");
        }

        // Crida al mètode del DAO
        return dao.getByGrauDificultat(grauDificultat);
    }


    /**
     * Obté una llista d'escaladors filtrats pel seu estil preferit.
     * @param estil El nom de l'estil (ex: "Esportiva", "Gel")
     * @return Llista d'escaladors que prefereixen aquest estil
     * @throws Exception Si hi ha un error en la comunicació amb la BD
     */
    public List<Escalador> getByEstil(String estil) throws Exception {
        if (estil == null || estil.trim().isEmpty()) {
            throw new Exception("L'estil de cerca no pot estar buit.");
        }

        // Crida al mètode del DAO
        return dao.getByEstil(estil);
    }
}
