package com.grupoadec.pm1ejercicio1_3.Models;

public class Persona {
    String nombres;
    String apellidos;
    int edad;
    String correo;
    String direccion;

    public Persona(String nombres, String apellidos, int edad, String correo, String direccion) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Persona(){};

    public String getNombres() {return nombres;}

    public void setNombres(String nombres) {this.nombres = nombres;}

    public String getApellidos() {return apellidos;}

    public void setApellidos(String apellidos) {this.apellidos = apellidos;}

    public int getEdad() {return edad;}

    public void setEdad(int edad) {this.edad = edad;}

    public String getCorreo() {return correo;}

    public void setCorreo(String correo) {this.correo = correo;}

    public String getDireccion() {return direccion;}

    public void setDireccion(String direccion) {this.direccion = direccion;}
}
