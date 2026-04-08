package dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentaLogic {

    public boolean realizarVenta(int productoId, int cantidadVendida) {
        String sql = "UPDATE inventarios SET cantidad = cantidad - ?, fecha_salida = NOW() WHERE producto_id = ?";
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexion.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, cantidadVendida);
                ps.setInt(2, productoId);
                int affectedRows = ps.executeUpdate();
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) { }
        }
        return false;
    }
}
