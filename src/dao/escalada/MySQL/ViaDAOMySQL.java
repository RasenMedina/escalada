package dao.escalada.MySQL;

import dao.escalada.ViaDAO;
import model.Via;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViaDAOMySQL implements ViaDAO {

    @Override
    public void create(Via v) throws Exception {

        String sql = "INSERT INTO via (id_sector, nom, id_escalador_creador, data_creacio, tipus, orientacio, grau, tipus_roca, ancoratge, estat, restriccions, data_inici_no_apte, data_fi_no_apte) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, v.getIdSector());
            ps.setString(2, v.getNom());
            ps.setInt(3, v.getIdEscaladorCreador());
            ps.setDate(4, v.getDataCreacio() != null ? Date.valueOf(v.getDataCreacio()) : null);
            ps.setString(5, v.getTipusVia());
            ps.setString(6, v.getOrientacio());
            ps.setString(7, v.getGrauDificultat());
            ps.setString(8, v.getTipusRoca());
            ps.setString(9, v.getAncoratge());
            ps.setString(10, v.getEstat());
            ps.setString(11, v.getRestriccions());
            ps.setDate(12, v.getDataIniciNoApte() != null ? Date.valueOf(v.getDataIniciNoApte()) : null);
            ps.setDate(13, v.getDataFiNoApte() != null ? Date.valueOf(v.getDataFiNoApte()) : null);

            ps.executeUpdate();
        }
    }

    @Override
    public Via getById(int id) throws Exception {

        String sql = "SELECT * FROM via WHERE id_via=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return map(rs);
        }

        return null;
    }

    @Override
    public List<Via> getAll() throws Exception {

        List<Via> llista = new ArrayList<>();
        String sql = "SELECT * FROM via";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) llista.add(map(rs));
        }

        return llista;
    }

    @Override
    public void update(Via v) throws Exception {

        String sql = "UPDATE via SET nom=?, tipus=?, orientacio=?, grau=?, tipus_roca=?, ancoratge=?, estat=? WHERE id_via=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, v.getNom());
            ps.setString(2, v.getTipusVia());
            ps.setString(3, v.getOrientacio());
            ps.setString(4, v.getGrauDificultat());
            ps.setString(5, v.getTipusRoca());
            ps.setString(6, v.getAncoratge());
            ps.setString(7, v.getEstat());
            ps.setInt(8, v.getIdVia());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {

        String sql = "DELETE FROM via WHERE id_via=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Via> getBySector(int idSector) throws Exception {

        List<Via> llista = new ArrayList<>();
        String sql = "SELECT * FROM via WHERE id_sector=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSector);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) llista.add(map(rs));
        }

        return llista;
    }

    @Override
    public List<Via> getByTipus(String tipus) throws Exception {

        List<Via> llista = new ArrayList<>();
        String sql = "SELECT * FROM via WHERE tipus=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tipus);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) llista.add(map(rs));
        }

        return llista;
    }

    @Override
    public List<Via> getByGrau(String grau) throws Exception {

        List<Via> llista = new ArrayList<>();
        String sql = "SELECT * FROM via WHERE grau=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, grau);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) llista.add(map(rs));
        }

        return llista;
    }

    @Override
    public List<Via> getByEstat(String estat) throws Exception {

        List<Via> llista = new ArrayList<>();
        String sql = "SELECT * FROM via WHERE estat=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estat);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) llista.add(map(rs));
        }

        return llista;
    }

    @Override
    public Via getByNomAndSector(String nom, int idSector) throws Exception {

        String sql = "SELECT * FROM via WHERE nom=? AND id_sector=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nom);
            ps.setInt(2, idSector);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) return map(rs);
        }

        return null;
    }

    private Via map(ResultSet rs) throws SQLException {
        return new Via(
                rs.getInt("id_via"),
                rs.getInt("id_sector"),
                rs.getString("nom"),
                rs.getInt("id_escalador_creador"),
                rs.getDate("data_creacio") != null ? rs.getDate("data_creacio").toLocalDate() : null,
                rs.getString("tipus"),
                rs.getString("orientacio"),
                rs.getString("grau"),
                rs.getString("tipus_roca"),
                rs.getString("ancoratge"),
                rs.getString("estat"),
                rs.getString("restriccions"),
                rs.getDate("data_inici_no_apte") != null ? rs.getDate("data_inici_no_apte").toLocalDate() : null,
                rs.getDate("data_fi_no_apte") != null ? rs.getDate("data_fi_no_apte").toLocalDate() : null
        );
    }
}
