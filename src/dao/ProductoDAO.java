package dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Producto;

public class ProductoDAO {

    public int insertarProducto(Producto p) {
        int idGenerado = -1;
        String sql = "INSERT INTO productos (nombre, precio, descripcion) VALUES (?, ?, ?)";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, p.getNombre());
                ps.setDouble(2, p.getPrecio());
                ps.setString(3, p.getDescripcion());
                
                int affectedRows = ps.executeUpdate();
                if (affectedRows > 0) {
                    rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        idGenerado = rs.getInt(1);
                        inicializarInventario(con, idGenerado);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return idGenerado;
    }

    private void inicializarInventario(Connection con, int productoId) {
        String sql = "INSERT INTO inventarios (producto_id, cantidad, fecha_entrada) VALUES (?, 0, NOW())";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productoId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al inicializar inventario: " + e.getMessage());
        }
    }
    
    public Producto buscarPorId(int id) {
        Producto p = null;
        String sql = "SELECT * FROM productos WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Producto();
                    p.setId(rs.getInt("id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setPrecio(rs.getDouble("precio"));
                    p.setDescripcion(rs.getString("descripcion"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
        return p;
    }

    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             while (rs.next()) {
                 Producto p = new Producto();
                 p.setId(rs.getInt("id"));
                 p.setNombre(rs.getString("nombre"));
                 p.setPrecio(rs.getDouble("precio"));
                 p.setDescripcion(rs.getString("descripcion"));
                 lista.add(p);
             }
        } catch (SQLException e) { }
        return lista;
    }
}
