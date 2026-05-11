package dao.escalada.MySQL;

import dao.escalada.SectorDAO;
import model.Sector;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectorDAOMySQL implements SectorDAO {

    @Override
    public void create(Sector s) throws Exception {

        String sql = "INSERT INTO sector (id_escola, nom, longitud, latitud, aproximacio, popularitat, es_gel) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, s.getIdEscola());
            ps.setString(2, s.getNom());
            ps.setDouble(3, s.getLongitud());
            ps.setDouble(4, s.getLatitud());
            ps.setString(5, s.getAproximacio());
            ps.setString(6, s.getPopularitat());
            ps.setBoolean(7, s.getEsGel());

            ps.executeUpdate();
        }
    }

    @Override
    public Sector getById(int id) throws Exception {

        String sql = "SELECT * FROM sector WHERE id_sector=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return map(rs);
        }

        return null;
    }

    @Override
    public List<Sector> getAll() throws Exception {

        List<Sector> llista = new ArrayList<>();
        String sql = "SELECT * FROM sector";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) llista.add(map(rs));
        }

        return llista;
    }

    @Override
    public void update(Sector s) throws Exception {

        String sql = "UPDATE sector SET nom=?, longitud=?, latitud=?, aproximacio=?, popularitat=?, es_gel=? WHERE id_sector=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getNom());
            ps.setDouble(2, s.getLongitud());
            ps.setDouble(3, s.getLatitud());
            ps.setString(4, s.getAproximacio());
            ps.setString(5, s.getPopularitat());
            ps.setBoolean(6, s.getEsGel());
            ps.setInt(7, s.getIdSector());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {

        String sql = "DELETE FROM sector WHERE id_sector=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Sector> getByEscola(int idEscola) throws Exception {

        List<Sector> llista = new ArrayList<>();
        String sql = "SELECT * FROM sector WHERE id_escola=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEscola);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) llista.add(map(rs));
        }

        return llista;
    }

    @Override
    public Sector getByNomAndEscola(String nom, int idEscola) throws Exception {

        String sql = "SELECT * FROM sector WHERE nom=? AND id_escola=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nom);
            ps.setInt(2, idEscola);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) return map(rs);
        }

        return null;
    }

    private Sector map(ResultSet rs) throws SQLException {
        return new Sector(
                rs.getInt("id_sector"),
                rs.getInt("id_escola"),
                rs.getString("nom"),
                rs.getDouble("longitud"),
                rs.getDouble("latitud"),
                rs.getString("aproximacio"),
                rs.getString("popularitat"),
                rs.getBoolean("es_gel")
        );
    }
}
