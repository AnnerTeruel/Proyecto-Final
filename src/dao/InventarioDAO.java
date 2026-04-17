package dao;

import model.Inventario;
import config.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {

    public List<Inventario> getAll() {
        List<Inventario> inventarios = new ArrayList<>();
        String sql = "SELECT * FROM inventario";
        try (Connection con = Conexion.connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                inventarios.add(extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventarios;
    }

    public List<Inventario> getAllWithProductInfo() {
        List<Inventario> inventarios = new ArrayList<>();
        // En el nuevo esquema, PRODUCTO apunta a INVENTARIO. 
        // Para obtener info de producto, debemos hacer el JOIN al revés.
        String sql = "SELECT i.*, p.nombre as NombreProducto FROM inventario i " +
                     "LEFT JOIN producto p ON p.inventarios_id_inventario = i.id_inventario";
        try (Connection con = Conexion.connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                inventarios.add(extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventarios;
    }

    public boolean update(int idInventario, int cantidad) {
        String sql = "UPDATE inventario SET stock_actual = ? WHERE id_inventario = ?";
        try (Connection con = Conexion.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cantidad);
            ps.setInt(2, idInventario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int insert(int cantidad) {
        String sql = "INSERT INTO inventario (stock_actual, fecha_registro, fecha_salida) VALUES (?, GETDATE(), GETDATE())";
        try (Connection con = Conexion.connect();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cantidad);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean updateStock(int idProducto, int cantidadADescontar) {
        // Primero buscamos el id_inventario asociado a ese producto
        String sqlSearch = "SELECT inventarios_id_inventario FROM producto WHERE id_producto = ?";
        String sqlUpdate = "UPDATE inventario SET stock_actual = stock_actual - ? WHERE id_inventario = ?";
        
        try (Connection con = Conexion.connect()) {
            int idInv = -1;
            try (PreparedStatement ps1 = con.prepareStatement(sqlSearch)) {
                ps1.setInt(1, idProducto);
                try (ResultSet rs = ps1.executeQuery()) {
                    if (rs.next()) idInv = rs.getInt(1);
                }
            }
            
            if (idInv != -1) {
                try (PreparedStatement ps2 = con.prepareStatement(sqlUpdate)) {
                    ps2.setInt(1, cantidadADescontar);
                    ps2.setInt(2, idInv);
                    return ps2.executeUpdate() > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Inventario extractFromResultSet(ResultSet rs) throws SQLException {
        Inventario i = new Inventario(
            rs.getInt("id_inventario"),
            rs.getInt("stock_actual"),
            rs.getTimestamp("fecha_registro"),
            rs.getTimestamp("fecha_salida")
        );
        try {
            // Intentar leer el nombre si el JOIN lo trajo
            i.setNombreProducto(rs.getString("NombreProducto"));
        } catch (SQLException e) {
            // No se trajo el nombre en esta consulta
        }
        return i;
    }
}
