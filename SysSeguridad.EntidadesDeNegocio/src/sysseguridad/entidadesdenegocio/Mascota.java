/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sysseguridad.entidadesdenegocio;

/**
 *
 * @author jacqu
 */
public class Mascota {
    private int Id;
    private int IdUsuario;
    private String Nombre;
    private String Sexo;
    private String Edad;
    private String Raza;
    private String SenialesParticulares;
    private String Especie;
    private String Propietario;

    public Mascota() {
    }

    public Mascota(int Id, int IdUsuario, String Nombre, String Sexo, String Edad, String Raza, String SenialesParticulares, String Especie, String Propietario) {
        this.Id = Id;
        this.IdUsuario = IdUsuario;
        this.Nombre = Nombre;
        this.Sexo = Sexo;
        this.Edad = Edad;
        this.Raza = Raza;
        this.SenialesParticulares = SenialesParticulares;
        this.Especie = Especie;
        this.Propietario = Propietario;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String Edad) {
        this.Edad = Edad;
    }

    public String getRaza() {
        return Raza;
    }

    public void setRaza(String Raza) {
        this.Raza = Raza;
    }

    public String getSenialesParticulares() {
        return SenialesParticulares;
    }

    public void setSenialesParticulares(String SenialesParticulares) {
        this.SenialesParticulares = SenialesParticulares;
    }

    public String getEspecie() {
        return Especie;
    }

    public void setEspecie(String Especie) {
        this.Especie = Especie;
    }

    public String getPropietario() {
        return Propietario;
    }

    public void setPropietario(String Propietario) {
        this.Propietario = Propietario;
    }      
}



