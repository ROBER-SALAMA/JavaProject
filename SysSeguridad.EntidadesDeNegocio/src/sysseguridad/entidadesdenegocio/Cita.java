/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sysseguridad.entidadesdenegocio;
import java.sql.ResultSet;
import java.time.LocalDate;
/**
 *
 * @author jacqu
 */
public class Cita {
    private int Id;
    private int IdMascota;
    private int IdUsuario;
    private String Fecha;
    private String Diagnostico;
    private String Direccion;
    private String Propietario;
    private String TipoCita;
    private int top_aux;  // Agregar el atributo top_aux
    private Mascota mascota;
    private byte estatus;

    public Cita() {
    }

    public Cita(int Id, int IdMascota, int IdUsuario, String Fecha, String Diagnostico, String Direccion, String Propietario, String TipoCita) {
        this.Id = Id;
        this.IdMascota = IdMascota;
        this.IdUsuario = IdUsuario;
        this.Fecha = Fecha;
        this.Diagnostico = Diagnostico;
        this.Direccion = Direccion;
        this.Propietario = Propietario;
        this.TipoCita = TipoCita;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdMascota() {
        return IdMascota;
    }

    public void setIdMascota(int IdMascota) {
        this.IdMascota = IdMascota;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getDiagnostico() {
        return Diagnostico;
    }

    public void setDiagnostico(String Diagnostico) {
        this.Diagnostico = Diagnostico;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getPropietario() {
        return Propietario;
    }

    public void setPropietario(String Propietario) {
        this.Propietario = Propietario;
    }

    public String getTipoCita() {
        return TipoCita;
    }

    public void setTipoCita(String TipoCita) {
        this.TipoCita = TipoCita;
    }
    
    public int getTop_aux(){
        return top_aux;
    }
    
    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
    
    private static void asignarDatosResultSet(Cita cita, ResultSet resultSet, int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Mascota getMascota() {
        return mascota;
    }

    public void setRol(Mascota mascota) {
        this.mascota = mascota;
    }

    public void setMascota(Mascota mascota) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    public void setMascota(Mascota get) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public void setMascota(Mascota get) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    
    public byte getEstatus() {
        return estatus;
    }
    
    public void setEstatus(byte estatus) {
        this.estatus = estatus;
    }
    
    

}
