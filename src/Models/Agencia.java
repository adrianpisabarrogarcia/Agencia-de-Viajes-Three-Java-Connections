package Models;

import java.util.Date;

public class Agencia {
    private int id;
    private String nombre;
    private Date fechaApertura;
    private String direccion;
    private String telefono;
    private String email;
    private String web;

    public Agencia(int id, String nombre, Date fechaApertura, String direccion, String telefono, String email, String web) {
        this.id = id;
        this.nombre = nombre;
        this.fechaApertura = fechaApertura;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.web = web;
    }

    public Agencia() {
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

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public String toString() {
        return "Agencia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaApertura=" + fechaApertura +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", web='" + web + '\'' +
                '}';
    }
}
