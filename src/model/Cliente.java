package model;

public class Cliente {
    private int idCliente;
    private String dni;
    private String nombre;
    private String telefono;
    private String correo;

    public Cliente() {}

    public Cliente(int idCliente, String dni, String nombre, String telefono, String correo) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    
    // Compatibilidad
    public int getIdentidad() { 
        try { return Integer.parseInt(dni); } catch (Exception e) { return 0; }
    }
}
