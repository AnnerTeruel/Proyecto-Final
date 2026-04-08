package dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Cliente;

public class ClienteDAO {

    public boolean insertarCliente(Cliente c) {
        String sql = "INSERT INTO clientes (nombre, identidad, edad) VALUES (?, ?, ?)";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Conexion.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, c.getNombre());
                ps.setString(2, c.getIdentidad());
                ps.setInt(3, c.getEdad());
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar cliente: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return false;
    }
    
    // Aquí puedes añadir más métodos CRUD para Cliente
}
