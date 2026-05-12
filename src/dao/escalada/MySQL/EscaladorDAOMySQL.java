package dao.escalada.MySQL;

import dao.escalada.EscaladorDAO;
import model.Escalador;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementació DAO per MySQL
 */
public class EscaladorDAOMySQL implements EscaladorDAO {

    /**
     * CREATE
     */
    @Override
    public void create(Escalador e) throws Exception {

        String sql = "INSERT INTO escalador (dni, nom, cognoms, alias, data_naix, estil_preferit) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getDni());
            ps.setString(2, e.getNom());
            ps.setString(3, e.getCognoms());
            ps.setString(4, e.getAlias());
            ps.setDate(5, e.getDataNaix() != null ? Date.valueOf(e.getDataNaix()) : null);
            ps.setString(6, e.getEstilPref());

            ps.executeUpdate();
        }
    }

    /**
     * GET BY ID
     */
    @Override
    public Escalador getById(int id) throws Exception {

        String sql = "SELECT * FROM escalador WHERE id_escalador = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapEscalador(rs);
            }
        }

        return null;
    }

    /**
     * GET ALL
     */
    @Override
    public List<Escalador> getAll() throws Exception {

        List<Escalador> llista = new ArrayList<>();

        String sql = "SELECT * FROM escalador";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                llista.add(mapEscalador(rs));
            }
        }

        return llista;
    }

    /**
     * UPDATE
     */
    @Override
    public void update(Escalador e) throws Exception {

        String sql = "UPDATE escalador SET nom=?, cognoms=?, alias=?, data_naix=?, estil_pref=? WHERE id_escalador=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getNom());
            ps.setString(2, e.getCognoms());
            ps.setString(3, e.getAlias());
            ps.setDate(4, e.getDataNaix() != null ? Date.valueOf(e.getDataNaix()) : null);
            ps.setString(5, e.getEstilPref());
            ps.setInt(6, e.getIdEscalador());

            ps.executeUpdate();
        }
    }

    /**
     * DELETE
     */
    @Override
    public void delete(int id) throws Exception {

        String sql = "DELETE FROM escalador WHERE id_escalador=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /**
     * EXTRA: getByDni
     */
    @Override
    public Escalador getByDni(String dni) throws Exception {

        String sql = "SELECT * FROM escalador WHERE dni=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dni);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapEscalador(rs);
            }
        }

        return null;
    }



    /**
     * MÈTODE PRIVAT PER CONVERTIR RESULTSET → OBJECTE
     */
    private Escalador mapEscalador(ResultSet rs) throws SQLException {

        Escalador e = new Escalador();

        e.setIdEscalador(rs.getInt("id_escalador"));
        e.setDni(rs.getString("dni"));
        e.setNom(rs.getString("nom"));
        e.setCognoms(rs.getString("cognoms"));
        e.setAlias(rs.getString("alias"));

        Date data = rs.getDate("data_naix");

        if (data != null) {
            e.setDataNaix(data.toLocalDate());
        }

        e.setEstilPref(rs.getString("estil_pref"));

        return e;

    }

    @Override
    public List<Escalador> getByGrauDificultat(String grauDificultat) throws Exception {

        List<Escalador> llista = new ArrayList<>();

        String sql = """
        SELECT DISTINCT e.*
        FROM escalador e
        INNER JOIN assoliment a ON e.id_escalador = a.id_escalador
        INNER JOIN via v ON a.id_via = v.id_via
        WHERE v.grau_dificultat = ?
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, grauDificultat);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                llista.add(mapEscalador(rs));
            }
        }

        return llista;
    }

    @Override
    public List<Escalador> getByEstil(String estil) throws SQLException {

        List<Escalador> llista = new ArrayList<>();

        String sql = "SELECT * FROM escalador WHERE estil_pref = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estil);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                llista.add(mapEscalador(rs));
            }
        }

        return llista;
    }

}
