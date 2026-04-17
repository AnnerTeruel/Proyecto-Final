package model;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String correo;
    private String contrasenia;
    private int rolesIdRol;
    private String estadoCuenta;
    
    // Campo auxiliar para mostrar el nombre del rol en la tabla si es necesario
    private String nombreRol;

    public Usuario() {}

    public Usuario(int idUsuario, String nombre, String correo, String contrasenia, int rolesIdRol, String estadoCuenta) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.rolesIdRol = rolesIdRol;
        this.estadoCuenta = estadoCuenta;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }
    public int getRolesIdRol() { return rolesIdRol; }
    public void setRolesIdRol(int rolesIdRol) { this.rolesIdRol = rolesIdRol; }
    public String getEstadoCuenta() { return estadoCuenta; }
    public void setEstadoCuenta(String estadoCuenta) { this.estadoCuenta = estadoCuenta; }
    
    public String getNombreRol() { return nombreRol; }
    public void setNombreRol(String nombreRol) { this.nombreRol = nombreRol; }

    // Compatibilidad mínima con código viejo si es necesario (aunque el plan dice que el view se arreglará luego)
    public int getId() { return idUsuario; }
    public void setId(int id) { this.idUsuario = id; }
    public String getRol() { return nombreRol; }
}
