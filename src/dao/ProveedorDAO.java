package dao;

import model.Proveedor;
import config.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    public List<Proveedor> getAll() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedor";
        try (Connection con = Conexion.connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                proveedores.add(new Proveedor(
                    rs.getInt("id_proveedor"),
                    rs.getString("nombre_proveedor"),
                    rs.getString("telefono_proveedor"),
                    rs.getString("direccion_proveedor"),
                    rs.getString("rtn")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores;
    }

    public boolean update(Proveedor proveedor) {
        String sql = "UPDATE proveedor SET nombre_proveedor = ?, telefono_proveedor = ?, direccion_proveedor = ?, rtn = ? WHERE id_proveedor = ?";
        try (Connection con = Conexion.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, proveedor.getNombreProveedor());
            ps.setString(2, proveedor.getTelefonoProveedor());
            ps.setString(3, proveedor.getDireccionProveedor());
            ps.setString(4, proveedor.getRtn());
            ps.setInt(5, proveedor.getIdProveedor());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM proveedor WHERE id_proveedor = ?";
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
