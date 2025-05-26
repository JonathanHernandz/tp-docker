package gd.tp.tarjeta;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import gd.tp.cliente.Cliente;
import gd.tp.nivel.Nivel;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Tarjeta")
public class Tarjeta {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 12, unique = true)
    private String idTarjeta;

    private double saldoPuntos;

    @Temporal(TemporalType.DATE)
    private Date fechaEmision;

    private boolean estado = false;



    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    @JsonBackReference // se agrega esta anotacion para evitar la serializacion recursiva
    /*@JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "idCliente")*/
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idNivel", nullable = false)
    private Nivel nivel;

    public String getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(String idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public double getSaldoPuntos() {
        return saldoPuntos;
    }

    public void setSaldoPuntos(double saldoPuntos) {
        this.saldoPuntos = saldoPuntos;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "idTarjeta='" + idTarjeta + '\'' +
                ", saldoPuntos=" + saldoPuntos +
                ", fechaEmision=" + fechaEmision +
                ", estado=" + estado +
                '}';
    }
}
