package Models;

import java.sql.Time;
import java.util.Date;

public class VisitaGuiada {
    private int id;
    private String nombre;
    private int numClientesMax;
    private String direccionPuntoPartida;
    private String tematica;
    private String lugar;
    private double precio;
    private Date fecha;
    private Time horaInicio;
    private Time horaFin;
    private Empleado responsable;
    private boolean activo;

    public VisitaGuiada(int id, String nombre, int numClientesMax, String direccionPuntoPartida, String tematica, String lugar, double precio, Date fecha, Time horaInicio, Time horaFin, Empleado responsable, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.numClientesMax = numClientesMax;
        this.direccionPuntoPartida = direccionPuntoPartida;
        this.tematica = tematica;
        this.lugar = lugar;
        this.precio = precio;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.responsable = responsable;
        this.activo = activo;
    }

    public VisitaGuiada() {
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

    public int getNumClientesMax() {
        return numClientesMax;
    }

    public void setNumClientesMax(int numClientesMax) {
        this.numClientesMax = numClientesMax;
    }

    public String getDireccionPuntoPartida() {
        return direccionPuntoPartida;
    }

    public void setDireccionPuntoPartida(String direccionPuntoPartida) {
        this.direccionPuntoPartida = direccionPuntoPartida;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public Empleado getResponsable() {
        return responsable;
    }

    public void setResponsable(Empleado responsable) {
        this.responsable = responsable;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "VisitaGuiada{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", numClientesMax=" + numClientesMax +
                ", direccionPuntoPartida='" + direccionPuntoPartida + '\'' +
                ", tematica='" + tematica + '\'' +
                ", lugar='" + lugar + '\'' +
                ", precio=" + precio +
                ", fecha=" + fecha +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", responsable=" + responsable +
                ", activo=" + activo +
                '}';
    }
}
