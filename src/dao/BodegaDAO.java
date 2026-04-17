package dao;

import model.Bodega;
import config.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BodegaDAO {

    public List<Bodega> getAll() {
        List<Bodega> bodegas = new ArrayList<>();
        String sql = "SELECT * FROM bodega";
        try (Connection con = Conexion.connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                bodegas.add(new Bodega(
                    rs.getInt("id_bodega"),
                    rs.getString("nombre"),
                    rs.getString("direccion_bodega")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bodegas;
    }

    public boolean update(Bodega bodega) {
        String sql = "UPDATE bodega SET nombre = ?, direccion_bodega = ? WHERE id_bodega = ?";
        try (Connection con = Conexion.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bodega.getNombre());
            ps.setString(2, bodega.getDireccionBodega());
            ps.setInt(3, bodega.getIdBodega());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM bodega WHERE id_bodega = ?";
        try (Connection con = Conexion.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
