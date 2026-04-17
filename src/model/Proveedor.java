package model;

public class Proveedor {
    private int idProveedor;
    private String nombreProveedor;
    private String telefonoProveedor;
    private String direccionProveedor;
    private String rtn;

    public Proveedor() {}

    public Proveedor(int idProveedor, String nombreProveedor, String telefonoProveedor, String direccionProveedor, String rtn) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.telefonoProveedor = telefonoProveedor;
        this.direccionProveedor = direccionProveedor;
        this.rtn = rtn;
    }

    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }
    public String getNombreProveedor() { return nombreProveedor; }
    public void setNombreProveedor(String nombreProveedor) { this.nombreProveedor = nombreProveedor; }
    public String getTelefonoProveedor() { return telefonoProveedor; }
    public void setTelefonoProveedor(String telefonoProveedor) { this.telefonoProveedor = telefonoProveedor; }
    public String getDireccionProveedor() { return direccionProveedor; }
    public void setDireccionProveedor(String direccionProveedor) { this.direccionProveedor = direccionProveedor; }
    public String getRtn() { return rtn; }
    public void setRtn(String rtn) { this.rtn = rtn; }

    @Override
    public String toString() {
        return nombreProveedor;
    }
}
