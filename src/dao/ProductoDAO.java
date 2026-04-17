package dao;

import model.Producto;
import config.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public List<Producto> getAll() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT p.*, pr.nombre_proveedor, b.nombre as nombre_bodega, c.nombre as nombre_categoria " +
                     "FROM producto p " +
                     "JOIN proveedor pr ON p.proveedores_id_proveedor = pr.id_proveedor " +
                     "JOIN bodega b ON p.bodegas_id_bodega = b.id_bodega " +
                     "JOIN categoria c ON p.categorias_id_categoria = c.id_categoria";
        try (Connection con = Conexion.connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                productos.add(extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public boolean insert(Producto p) {
        String sql = "INSERT INTO producto (nombre, proveedores_id_proveedor, bodegas_id_bodega, fecha_creacion, " +
                     "fecha_vencimiento, categorias_id_categoria, inventarios_id_inventario, precio) " +
                     "VALUES (?, ?, ?, GETDATE(), ?, ?, ?, ?)";
        
        try (Connection con = Conexion.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getProveedoresIdProveedor());
            ps.setInt(3, p.getBodegasIdBodega());
            ps.setTimestamp(4, new Timestamp(p.getFechaVencimiento().getTime()));
            ps.setInt(5, p.getCategoriasIdCategoria());
            ps.setInt(6, p.getInventariosIdInventario());
            ps.setDouble(7, p.getPrecio());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Producto p) {
        String sql = "UPDATE producto SET nombre = ?, proveedores_id_proveedor = ?, bodegas_id_bodega = ?, " +
                     "fecha_vencimiento = ?, categorias_id_categoria = ?, precio = ? WHERE id_producto = ?";
        try (Connection con = Conexion.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getProveedoresIdProveedor());
            ps.setInt(3, p.getBodegasIdBodega());
            ps.setTimestamp(4, new Timestamp(p.getFechaVencimiento().getTime()));
            ps.setInt(5, p.getCategoriasIdCategoria());
            ps.setDouble(6, p.getPrecio());
            ps.setInt(7, p.getIdProducto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM producto WHERE id_producto = ?";
        try (Connection con = Conexion.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Producto getById(int id) {
        String sql = "SELECT p.*, pr.nombre_proveedor, b.nombre as nombre_bodega, c.nombre as nombre_categoria " +
                     "FROM producto p " +
                     "JOIN proveedor pr ON p.proveedores_id_proveedor = pr.id_proveedor " +
                     "JOIN bodega b ON p.bodegas_id_bodega = b.id_bodega " +
                     "JOIN categoria c ON p.categorias_id_categoria = c.id_categoria " +
                     "WHERE p.id_producto = ?";
        try (Connection con = Conexion.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Producto extractFromResultSet(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setIdProducto(rs.getInt("id_producto"));
        p.setNombre(rs.getString("nombre"));
        p.setProveedoresIdProveedor(rs.getInt("proveedores_id_proveedor"));
        p.setBodegasIdBodega(rs.getInt("bodegas_id_bodega"));
        p.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
        p.setFechaVencimiento(rs.getTimestamp("fecha_vencimiento"));
        p.setCategoriasIdCategoria(rs.getInt("categorias_id_categoria"));
        p.setInventariosIdInventario(rs.getInt("inventarios_id_inventario"));
        p.setPrecio(rs.getDouble("precio"));
        
        // Nombres cargados vía JOIN
        p.setNombreProveedor(rs.getString("nombre_proveedor"));
        p.setNombreBodega(rs.getString("nombre_bodega"));
        p.setNombreCategoria(rs.getString("nombre_categoria"));
        
        return p;
    }
}
