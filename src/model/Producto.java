package model;

import java.util.Date;

public class Producto {
    private int idProducto;
    private String nombre;
    private int proveedoresIdProveedor;
    private int bodegasIdBodega;
    private Date fechaCreacion;
    private Date fechaVencimiento;
    private int categoriasIdCategoria;
    private int inventariosIdInventario;
    private double precio;
    
    // Campos auxiliares para nombres en tablas
    private String nombreProveedor;
    private String nombreBodega;
    private String nombreCategoria;

    public Producto() {}

    public Producto(int idProducto, String nombre, int proveedoresIdProveedor, int bodegasIdBodega, Date fechaCreacion, Date fechaVencimiento, int categoriasIdCategoria, int inventariosIdInventario, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.proveedoresIdProveedor = proveedoresIdProveedor;
        this.bodegasIdBodega = bodegasIdBodega;
        this.fechaCreacion = fechaCreacion;
        this.fechaVencimiento = fechaVencimiento;
        this.categoriasIdCategoria = categoriasIdCategoria;
        this.inventariosIdInventario = inventariosIdInventario;
        this.precio = precio;
    }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getProveedoresIdProveedor() { return proveedoresIdProveedor; }
    public void setProveedoresIdProveedor(int proveedoresIdProveedor) { this.proveedoresIdProveedor = proveedoresIdProveedor; }
    public int getBodegasIdBodega() { return bodegasIdBodega; }
    public void setBodegasIdBodega(int bodegasIdBodega) { this.bodegasIdBodega = bodegasIdBodega; }
    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public Date getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(Date fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public int getCategoriasIdCategoria() { return categoriasIdCategoria; }
    public void setCategoriasIdCategoria(int categoriasIdCategoria) { this.categoriasIdCategoria = categoriasIdCategoria; }
    public int getInventariosIdInventario() { return inventariosIdInventario; }
    public void setInventariosIdInventario(int inventariosIdInventario) { this.inventariosIdInventario = inventariosIdInventario; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getNombreProveedor() { return nombreProveedor; }
    public void setNombreProveedor(String nombreProveedor) { this.nombreProveedor = nombreProveedor; }
    public String getNombreBodega() { return nombreBodega; }
    public void setNombreBodega(String nombreBodega) { this.nombreBodega = nombreBodega; }
    public String getNombreCategoria() { return nombreCategoria; }
    public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }
    
    // Compatibilidad mínima
    public int getId() { return idProducto; }
    public void setId(int id) { this.idProducto = id; }
}
