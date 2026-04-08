package dao;

import model.Inventario;
import config.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {

    public List<Inventario> getAllWithProductInfo() {
        List<Inventario> inventarios = new ArrayList<>();
        String sql = "SELECT i.*, p.Nombre as NombreProducto FROM inventarios i " +
                     "JOIN productos p ON i.id_producto = p.ID";
        try (Connection con = Conexion.getConnection();
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

    public List<Inventario> searchByIdOrProductName(String searchTerm) {
        List<Inventario> inventarios = new ArrayList<>();
        String sql = "SELECT i.*, p.Nombre as NombreProducto FROM inventarios i " +
                     "JOIN productos p ON i.id_producto = p.ID " +
                     "WHERE CAST(i.id_inventario AS CHAR) = ? OR p.Nombre LIKE ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, searchTerm);
            ps.setString(2, "%" + searchTerm + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    inventarios.add(extractFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventarios;
    }

    public boolean updateStock(int idProducto, int quantityToReduce) {
        String sqlSelect = "SELECT * FROM inventarios WHERE id_producto = ?";
        String sqlUpdate = "UPDATE inventarios SET Cantidad = ?, Fecha_Salida = ? WHERE id_inventario = ?";

        try (Connection con = Conexion.getConnection()) {
            con.setAutoCommit(false); 
            
            try (PreparedStatement psSelect = con.prepareStatement(sqlSelect)) {
                psSelect.setInt(1, idProducto);
                ResultSet rs = psSelect.executeQuery();
                if (rs.next()) {
                    int idInventario = rs.getInt("id_inventario");
                    int stockActual = rs.getInt("Cantidad");
                    
                    if (stockActual >= quantityToReduce) {
                        try (PreparedStatement psUpdate = con.prepareStatement(sqlUpdate)) {
                            psUpdate.setInt(1, stockActual - quantityToReduce);
                            psUpdate.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                            psUpdate.setInt(3, idInventario);
                            psUpdate.executeUpdate();
                        }
                        con.commit();
                        return true;
                    }
                }
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Inventario extractFromResultSet(ResultSet rs) throws SQLException {
        Inventario i = new Inventario(
            rs.getInt("id_inventario"),
            rs.getInt("id_producto"),
            rs.getInt("Cantidad"),
            rs.getTimestamp("Fecha_Registro"),
            rs.getTimestamp("Fecha_Salida")
        );
        i.setNombreProducto(rs.getString("NombreProducto"));
        return i;
    }
    
    public boolean insert(int idProducto, int cantidad) {
        String sql = "INSERT INTO inventarios (id_producto, Cantidad) VALUES (?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            ps.setInt(2, cantidad);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
