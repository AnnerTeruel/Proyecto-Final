package dao;

import model.Rol;
import config.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {

    public List<Rol> getAll() {
        List<Rol> roles = new ArrayList<>();
        String sql = "SELECT * FROM rol";
        try (Connection con = Conexion.connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                roles.add(new Rol(
                    rs.getInt("id_rol"),
                    rs.getString("nombre_rol"),
                    rs.getString("descripcion_rol")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public boolean update(Rol rol) {
        String sql = "UPDATE rol SET nombre_rol = ?, descripcion_rol = ? WHERE id_rol = ?";
        try (Connection con = Conexion.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, rol.getNombreRol());
            ps.setString(2, rol.getDescripcionRol());
            ps.setInt(3, rol.getIdRol());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM rol WHERE id_rol = ?";
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
