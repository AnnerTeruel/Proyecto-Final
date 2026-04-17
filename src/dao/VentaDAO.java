package dao;

import model.Venta;
import config.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    public int insert(Venta v) {
        String sql = "INSERT INTO venta (fecha_venta, total, clientes_id_cliente, usuarios_id_usuario) VALUES (GETDATE(), ?, ?, ?)";
        try (Connection con = Conexion.connect();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setDouble(1, v.getTotal());
            ps.setInt(2, v.getClientesIdCliente());
            ps.setInt(3, v.getUsuariosIdUsuario());
            
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

    public List<Venta> getAll() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM venta ORDER BY fecha_venta DESC";
        try (Connection con = Conexion.connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ventas.add(new Venta(
                    rs.getInt("id_venta"),
                    rs.getTimestamp("fecha_venta"),
                    rs.getDouble("total"),
                    rs.getInt("clientes_id_cliente"),
                    rs.getInt("usuarios_id_usuario")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }
}
