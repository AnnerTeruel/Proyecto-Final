package model;

public class Rol {
    private int idRol;
    private String nombreRol;
    private String descripcionRol;

    public Rol() {}

    public Rol(int idRol, String nombreRol, String descripcionRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.descripcionRol = descripcionRol;
    }

    public int getIdRol() { return idRol; }
    public void setIdRol(int idRol) { this.idRol = idRol; }
    public String getNombreRol() { return nombreRol; }
    public void setNombreRol(String nombreRol) { this.nombreRol = nombreRol; }
    public String getDescripcionRol() { return descripcionRol; }
    public void setDescripcionRol(String descripcionRol) { this.descripcionRol = descripcionRol; }

    @Override
    public String toString() {
        return nombreRol; // Útil para JComboBox
    }
}
