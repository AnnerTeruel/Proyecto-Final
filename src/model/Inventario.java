package model;

import java.util.Date;

public class Inventario {
    private int idInventario;
    private int stockActual;
    private Date fechaRegistro;
    private Date fechaSalida;
    
    // Campo auxiliar para nombre de producto si fuera necesario (aunque el nuevo esquema invierte la relación)
    private String nombreProducto;

    public Inventario() {}

    public Inventario(int idInventario, int stockActual, Date fechaRegistro, Date fechaSalida) {
        this.idInventario = idInventario;
        this.stockActual = stockActual;
        this.fechaRegistro = fechaRegistro;
        this.fechaSalida = fechaSalida;
    }

    public int getIdInventario() { return idInventario; }
    public void setIdInventario(int idInventario) { this.idInventario = idInventario; }
    public int getStockActual() { return stockActual; }
    public void setStockActual(int stockActual) { this.stockActual = stockActual; }
    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public Date getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(Date fechaSalida) { this.fechaSalida = fechaSalida; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    
    // Compatibilidad
    public int getCantidad() { return stockActual; }
}
