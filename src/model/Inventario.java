package model;

import java.sql.Timestamp;

public class Inventario {
    private int idInventario;
    private int idProducto;
    private int cantidad;
    private Timestamp fechaRegistro;
    private Timestamp fechaSalida;
    
    private String nombreProducto;

    public Inventario() {}

    public Inventario(int idInventario, int idProducto, int cantidad, Timestamp fechaRegistro, Timestamp fechaSalida) {
        this.idInventario = idInventario;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.fechaRegistro = fechaRegistro;
        this.fechaSalida = fechaSalida;
    }

    public int getIdInventario() { return idInventario; }
    public void setIdInventario(int idInventario) { this.idInventario = idInventario; }
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public Timestamp getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(Timestamp fechaSalida) { this.fechaSalida = fechaSalida; }
    
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
}
