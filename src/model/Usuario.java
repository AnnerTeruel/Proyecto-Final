package model;

public class Usuario {
    private int id;
    private String nombre;
    private int edad;
    private String rol;
    private String descripcionRol;
    private String password;

    public Usuario() {}

    public Usuario(int id, String nombre, int edad, String rol, String descripcionRol, String password) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.rol = rol;
        this.descripcionRol = descripcionRol;
        this.password = password;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public String getDescripcionRol() { return descripcionRol; }
    public void setDescripcionRol(String descripcionRol) { this.descripcionRol = descripcionRol; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
