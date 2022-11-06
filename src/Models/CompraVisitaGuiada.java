package Models;

import java.util.Date;

public class CompraVisitaGuiada {
    private int id;
    private Date fecha;
    private Cliente cliente;
    private VisitaGuiada visitaGuiada;

    public CompraVisitaGuiada(int id, Date fecha, Cliente cliente, VisitaGuiada visitaGuiada) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.visitaGuiada = visitaGuiada;
    }

    public CompraVisitaGuiada() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public VisitaGuiada getVisitaGuiada() {
        return visitaGuiada;
    }

    public void setVisitaGuiada(VisitaGuiada visitaGuiada) {
        this.visitaGuiada = visitaGuiada;
    }

    @Override
    public String toString() {
        return "CompraVisitaGuiada{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", cliente=" + cliente +
                ", visitaGuiada=" + visitaGuiada +
                '}';
    }
}
