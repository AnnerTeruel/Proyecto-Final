package model;

public class Cliente {
    private int identidad;
    private String nombre;
    private int edad;

    public Cliente() {}

    public Cliente(int identidad, String nombre, int edad) {
        this.identidad = identidad;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getIdentidad() { return identidad; }
    public void setIdentidad(int identidad) { this.identidad = identidad; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
}
