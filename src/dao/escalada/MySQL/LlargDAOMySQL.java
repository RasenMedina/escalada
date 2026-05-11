package dao.escalada.MySQL;

import dao.escalada.LlargDAO;
import model.Llarg;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LlargDAOMySQL implements LlargDAO {

    @Override
    public void create(Llarg l) throws Exception {

        String sql = "INSERT INTO llarg (id_via, num, llargada, grau) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, l.getIdVia());
            ps.setInt(2, l.getNum());
            ps.setInt(3, l.getLlargada());
            ps.setString(4, l.getGrauDificultat());

            ps.executeUpdate();
        }
    }

    @Override
    public Llarg getById(int id) throws Exception {

        String sql = "SELECT * FROM llarg WHERE id_llarg=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return map(rs);
        }

        return null;
    }

    @Override
    public List<Llarg> getAll() throws Exception {

        List<Llarg> llista = new ArrayList<>();
        String sql = "SELECT * FROM llarg";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) llista.add(map(rs));
        }

        return llista;
    }

    @Override
    public void update(Llarg l) throws Exception {

        String sql = "UPDATE llarg SET num=?, llargada=?, grau=? WHERE id_llarg=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, l.getNum());
            ps.setInt(2, l.getLlargada());
            ps.setString(3, l.getGrauDificultat());
            ps.setInt(4, l.getIdLlarg());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {

        String sql = "DELETE FROM llarg WHERE id_llarg=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Llarg> getByVia(int idVia) throws Exception {

        List<Llarg> llista = new ArrayList<>();
        String sql = "SELECT * FROM llarg WHERE id_via=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idVia);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) llista.add(map(rs));
        }

        return llista;
    }

    @Override
    public Llarg getByViaAndNum(int idVia, int num) throws Exception {

        String sql = "SELECT * FROM llarg WHERE id_via=? AND num=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idVia);
            ps.setInt(2, num);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) return map(rs);
        }

        return null;
    }

    private Llarg map(ResultSet rs) throws SQLException {
        return new Llarg(
                rs.getInt("id_llarg"),
                rs.getInt("id_via"),
                rs.getInt("num"),
                rs.getInt("llargada"),
                rs.getString("grau")
        );
    }
}
