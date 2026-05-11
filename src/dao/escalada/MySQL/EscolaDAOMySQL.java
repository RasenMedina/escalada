package dao.escalada.MySQL;

import dao.escalada.EscolaDAO;
import model.Escola;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscolaDAOMySQL implements EscolaDAO {

    @Override
    public void create(Escola e) throws Exception {

        String sql = "INSERT INTO escola (nom, lloc, aproximacio, popularitat) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getNom());
            ps.setString(2, e.getLloc());
            ps.setString(3, e.getAproximacio());
            ps.setString(4, e.getPopularitat());

            ps.executeUpdate();
        }
    }

    @Override
    public Escola getById(int id) throws Exception {

        String sql = "SELECT * FROM escola WHERE id_escola=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return map(rs);
        }

        return null;
    }

    @Override
    public List<Escola> getAll() throws Exception {

        List<Escola> llista = new ArrayList<>();
        String sql = "SELECT * FROM escola";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) llista.add(map(rs));
        }

        return llista;
    }

    @Override
    public void update(Escola e) throws Exception {

        String sql = "UPDATE escola SET nom=?, lloc=?, aproximacio=?, popularitat=? WHERE id_escola=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getNom());
            ps.setString(2, e.getLloc());
            ps.setString(3, e.getAproximacio());
            ps.setString(4, e.getPopularitat());
            ps.setInt(5, e.getIdEscola());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {

        String sql = "DELETE FROM escola WHERE id_escola=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Escola getByNom(String nom) throws Exception {

        String sql = "SELECT * FROM escola WHERE nom=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nom);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return map(rs);
        }

        return null;
    }

    private Escola map(ResultSet rs) throws SQLException {
        return new Escola(
                rs.getInt("id_escola"),
                rs.getString("nom"),
                rs.getString("lloc"),
                rs.getString("aproximacio"),
                rs.getString("popularitat")
        );
    }
}
