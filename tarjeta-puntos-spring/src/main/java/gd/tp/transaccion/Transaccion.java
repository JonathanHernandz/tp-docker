package gd.tp.transaccion;

import gd.tp.producto.Producto;
import gd.tp.tarjeta.Tarjeta;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.Date;

@Entity
@Table(name = "Transaccion")
public class Transaccion {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idTransaccion;

    @ManyToOne
    @JoinColumn( name = "idTarjeta", nullable = false)
    private Tarjeta tarjeta;

    @ManyToOne
    @JoinColumn( name = "idProducto", nullable = false)
    private Producto producto;

    @Temporal( TemporalType.TIMESTAMP )
    private Date Fecha;

    private String tipo;
    private Double monto;
    private double puntosObtenidos;
    private double puntosGastados;

    private Integer cantidad;

    public Long getIdTransaccion() {
        return idTransaccion;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        this.Fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public double getPuntosObtenidos() {
        return puntosObtenidos;
    }

    public void setPuntosObtenidos(double puntosObtenidos) {
        this.puntosObtenidos = puntosObtenidos;
    }

    public double getPuntosGastados() {
        return puntosGastados;
    }

    public void setPuntosGastados(double puntosGastados) {
        this.puntosGastados = puntosGastados;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "idTransaccion=" + idTransaccion +
                ", fecha=" + Fecha +
                ", tipo='" + tipo + '\'' +
                ", monto=" + monto +
                ", puntosObtenidos=" + puntosObtenidos +
                ", puntosGastados=" + puntosGastados +
                ", cantidad=" + cantidad +
                '}';
    }
}
