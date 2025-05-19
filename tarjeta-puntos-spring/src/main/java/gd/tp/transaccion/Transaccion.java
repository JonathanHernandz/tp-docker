package gd.tp.transaccion;

import gd.tp.producto.Producto;
import gd.tp.tarjeta.Tarjeta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.Date;

@Entity
@Table(name = "Transaccion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


}
