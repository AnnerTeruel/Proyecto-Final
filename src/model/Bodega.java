package model;

public class Bodega {
    private int idBodega;
    private String nombre;
    private String direccionBodega;

    public Bodega() {}

    public Bodega(int idBodega, String nombre, String direccionBodega) {
        this.idBodega = idBodega;
        this.nombre = nombre;
        this.direccionBodega = direccionBodega;
    }

    public int getIdBodega() { return idBodega; }
    public void setIdBodega(int idBodega) { this.idBodega = idBodega; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccionBodega() { return direccionBodega; }
    public void setDireccionBodega(String direccionBodega) { this.direccionBodega = direccionBodega; }

    @Override
    public String toString() {
        return nombre;
    }
}
