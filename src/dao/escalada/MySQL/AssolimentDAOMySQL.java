package dao.escalada.MySQL;

import dao.escalada.AssolimentDAO;
import model.Assoliment;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssolimentDAOMySQL implements AssolimentDAO {

    @Override
    public void create(Assoliment a) throws Exception {

        String sql = "INSERT INTO assoliment (id_escalador, id_via, data) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, a.getIdEscalador());
            ps.setInt(2, a.getIdVia());
            ps.setDate(3, a.getData() != null ? Date.valueOf(a.getData()) : null);

            ps.executeUpdate();
        }
    }

    @Override
    public Assoliment getById(int id) {
        return null; // no aplica (clau composta)
    }

    @Override
    public List<Assoliment> getAll() throws Exception {

        List<Assoliment> llista = new ArrayList<>();
        String sql = "SELECT * FROM assoliment";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                llista.add(map(rs));
            }
        }

        return llista;
    }

    @Override
    public void update(Assoliment a) throws Exception {

        String sql = "UPDATE assoliment SET data=? WHERE id_escalador=? AND id_via=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, a.getData() != null ? Date.valueOf(a.getData()) : null);
            ps.setInt(2, a.getIdEscalador());
            ps.setInt(3, a.getIdVia());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) {
        // no aplica
    }

    @Override
    public List<Assoliment> getByEscalador(int idEscalador) throws Exception {

        List<Assoliment> llista = new ArrayList<>();
        String sql = "SELECT * FROM assoliment WHERE id_escalador=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEscalador);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                llista.add(map(rs));
            }
        }

        return llista;
    }

    @Override
    public List<Assoliment> getByVia(int idVia) throws Exception {

        List<Assoliment> llista = new ArrayList<>();
        String sql = "SELECT * FROM assoliment WHERE id_via=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idVia);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                llista.add(map(rs));
            }
        }

        return llista;
    }

    @Override
    public boolean exists(int idEscalador, int idVia) throws Exception {

        String sql = "SELECT 1 FROM assoliment WHERE id_escalador=? AND id_via=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEscalador);
            ps.setInt(2, idVia);

            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    private Assoliment map(ResultSet rs) throws SQLException {
        return new Assoliment(
                rs.getInt("id_escalador"),
                rs.getInt("id_via"),
                rs.getDate("data") != null ? rs.getDate("data").toLocalDate() : null
        );
    }
}
