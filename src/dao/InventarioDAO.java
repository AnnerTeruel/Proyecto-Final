package dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Inventario;

public class InventarioDAO {

    public Inventario consultarStock(int productoId) {
        Inventario inv = null;
        String sql = "SELECT * FROM inventarios WHERE producto_id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    inv = new Inventario();
                    inv.setId(rs.getInt("id"));
                    inv.setProductoId(rs.getInt("producto_id"));
                    inv.setCantidad(rs.getInt("cantidad"));
                    inv.setFechaEntrada(rs.getTimestamp("fecha_entrada"));
                    inv.setFechaSalida(rs.getTimestamp("fecha_salida"));
                }
            }
        } catch (SQLException e) { System.err.println(e); }
        return inv;
    }

    public boolean agregarStock(int productoId, int cantidadAgregada) {
        String sql = "UPDATE inventarios SET cantidad = cantidad + ?, fecha_entrada = NOW() WHERE producto_id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cantidadAgregada);
            ps.setInt(2, productoId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
    
    // Método para MenuPrincipal (Buscar todo)
    public List<Inventario> mostrarTodo() {
        List<Inventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM inventarios";
        try (Connection con = Conexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
             while (rs.next()) {
                 Inventario inv = new Inventario();
                 inv.setId(rs.getInt("id"));
                 inv.setProductoId(rs.getInt("producto_id"));
                 inv.setCantidad(rs.getInt("cantidad"));
                 lista.add(inv);
             }
        } catch (SQLException e) { System.err.println(e); }
        return lista;
    }
}
