package model;

import java.util.Date;

public class Venta {
    private int idVenta;
    private Date fechaVenta;
    private double total;
    private int clientesIdCliente;
    private int usuariosIdUsuario;

    public Venta() {}

    public Venta(int idVenta, Date fechaVenta, double total, int clientesIdCliente, int usuariosIdUsuario) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.clientesIdCliente = clientesIdCliente;
        this.usuariosIdUsuario = usuariosIdUsuario;
    }

    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public Date getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(Date fechaVenta) { this.fechaVenta = fechaVenta; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public int getClientesIdCliente() { return clientesIdCliente; }
    public void setClientesIdCliente(int clientesIdCliente) { this.clientesIdCliente = clientesIdCliente; }
    public int getUsuariosIdUsuario() { return usuariosIdUsuario; }
    public void setUsuariosIdUsuario(int usuariosIdUsuario) { this.usuariosIdUsuario = usuariosIdUsuario; }
}
