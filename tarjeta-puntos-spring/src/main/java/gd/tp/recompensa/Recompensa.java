package gd.tp.recompensa;


import gd.tp.nivel.Nivel;
import gd.tp.producto.Producto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.NoRepositoryBean;

@Entity
@Table( name = "Recompensa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Recompensa {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idRecompensa;

    @ManyToOne
    @JoinColumn( name = "idProducto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn( name = "idNivel", nullable = false)
    private Nivel nivel;

    private Integer puntosRequeridos;

    @Column
    private Integer stock;

}
