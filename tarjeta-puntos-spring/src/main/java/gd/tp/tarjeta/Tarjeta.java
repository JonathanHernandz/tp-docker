package gd.tp.tarjeta;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import gd.tp.cliente.Cliente;
import gd.tp.nivel.Nivel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Tarjeta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


}
