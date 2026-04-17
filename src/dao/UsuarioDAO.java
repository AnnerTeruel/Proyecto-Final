package dao;

import model.Usuario;
import config.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario login(String nombre, String password) {
        String sql = "SELECT u.*, r.nombre_rol FROM usuario u " +
                     "JOIN rol r ON u.roles_id_rol = r.id_rol " +
                     "WHERE u.nombre = ? AND u.contrasenia = ?";
        try (Connection con = Conexion.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractUserFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> getAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT u.*, r.nombre_rol FROM usuario u " +
                     "JOIN rol r ON u.roles_id_rol = r.id_rol";
        try (Connection con = Conexion.connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                usuarios.add(extractUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario getById(int id) {
        String sql = "SELECT u.*, r.nombre_rol FROM usuario u " +
                     "JOIN rol r ON u.roles_id_rol = r.id_rol " +
                     "WHERE u.id_usuario = ?";
        try (Connection con = Conexion.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractUserFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(Usuario usuario) {
        String sql = "INSERT INTO usuario (nombre, correo, contrasenia, roles_id_rol, estado_cuenta) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getContrasenia());
            ps.setInt(4, usuario.getRolesIdRol());
            ps.setString(5, usuario.getEstadoCuenta());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Usuario usuario) {
        boolean tienePassword = usuario.getContrasenia() != null && !usuario.getContrasenia().trim().isEmpty();
        String sql;
        
        if (tienePassword) {
            sql = "UPDATE usuario SET nombre = ?, correo = ?, contrasenia = ?, roles_id_rol = ?, estado_cuenta = ? WHERE id_usuario = ?";
        } else {
            sql = "UPDATE usuario SET nombre = ?, correo = ?, roles_id_rol = ?, estado_cuenta = ? WHERE id_usuario = ?";
        }

        try (Connection con = Conexion.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            
            if (tienePassword) {
                ps.setString(3, usuario.getContrasenia());
                ps.setInt(4, usuario.getRolesIdRol());
                ps.setString(5, usuario.getEstadoCuenta());
                ps.setInt(6, usuario.getIdUsuario());
            } else {
                ps.setInt(3, usuario.getRolesIdRol());
                ps.setString(4, usuario.getEstadoCuenta());
                ps.setInt(5, usuario.getIdUsuario());
            }
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection con = Conexion.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Usuario extractUserFromResultSet(ResultSet rs) throws SQLException {
        Usuario u = new Usuario(
            rs.getInt("id_usuario"),
            rs.getString("nombre"),
            rs.getString("correo"),
            rs.getString("contrasenia"),
            rs.getInt("roles_id_rol"),
            rs.getString("estado_cuenta")
        );
        u.setNombreRol(rs.getString("nombre_rol"));
        return u;
    }
}
