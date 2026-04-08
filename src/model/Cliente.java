package model;

public class Cliente {
    private int id;
    private String nombre;
    private String identidad;
    private int edad;

    public Cliente() {
    }

    public Cliente(int id, String nombre, String identidad, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.identidad = identidad;
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentidad() {
        return identidad;
    }

    public void setIdentidad(String identidad) {
        this.identidad = identidad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
