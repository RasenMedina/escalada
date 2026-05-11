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

        String sql = "INSERT INTO escalador (dni, nom, cognom1, cognom2, alias, data_naix, estil) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getDni());
            ps.setString(2, e.getNom());
            ps.setString(3, e.getCognom1());
            ps.setString(4, e.getCognom2());
            ps.setString(5, e.getAlias());
            ps.setDate(6, e.getDataNaix() != null ? Date.valueOf(e.getDataNaix()) : null);
            ps.setString(7, e.getEstil());

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

        String sql = "UPDATE escalador SET nom=?, cognom1=?, cognom2=?, alias=?, data_naix=?, estil=? WHERE id_escalador=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getNom());
            ps.setString(2, e.getCognom1());
            ps.setString(3, e.getCognom2());
            ps.setString(4, e.getAlias());
            ps.setDate(5, e.getDataNaix() != null ? Date.valueOf(e.getDataNaix()) : null);
            ps.setString(6, e.getEstil());
            ps.setInt(7, e.getIdEscalador());

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

        return new Escalador(
                rs.getInt("id_escalador"),
                rs.getString("dni"),
                rs.getString("nom"),
                rs.getString("cognom1"),
                rs.getString("cognom2"),
                rs.getString("alias"),
                rs.getDate("data_naix") != null ? rs.getDate("data_naix").toLocalDate() : null,
                rs.getString("estil")
        );
    }

}
