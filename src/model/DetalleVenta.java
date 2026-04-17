package model;

public class DetalleVenta {
    private int ventasIdVenta;
    private int productosIdProducto;
    private int cantidad;
    private double precioUnitarioVenta;
    private double descuento;

    public DetalleVenta() {}

    public DetalleVenta(int ventasIdVenta, int productosIdProducto, int cantidad, double precioUnitarioVenta, double descuento) {
        this.ventasIdVenta = ventasIdVenta;
        this.productosIdProducto = productosIdProducto;
        this.cantidad = cantidad;
        this.precioUnitarioVenta = precioUnitarioVenta;
        this.descuento = descuento;
    }

    public int getVentasIdVenta() { return ventasIdVenta; }
    public void setVentasIdVenta(int ventasIdVenta) { this.ventasIdVenta = ventasIdVenta; }
    public int getProductosIdProducto() { return productosIdProducto; }
    public void setProductosIdProducto(int productosIdProducto) { this.productosIdProducto = productosIdProducto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getPrecioUnitarioVenta() { return precioUnitarioVenta; }
    public void setPrecioUnitarioVenta(double precioUnitarioVenta) { this.precioUnitarioVenta = precioUnitarioVenta; }
    public double getDescuento() { return descuento; }
    public void setDescuento(double descuento) { this.descuento = descuento; }
}
