package dao;

import model.DetalleVenta;
import config.Conexion;
import java.sql.*;

public class DetalleVentaDAO {

    public boolean insert(DetalleVenta dv) {
        String sql = "INSERT INTO detalle_venta (ventas_id_venta, productos_id_producto, cantidad, precio_unitario_venta, descuento) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, dv.getVentasIdVenta());
            ps.setInt(2, dv.getProductosIdProducto());
            ps.setInt(3, dv.getCantidad());
            ps.setDouble(4, dv.getPrecioUnitarioVenta());
            ps.setDouble(5, dv.getDescuento());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
