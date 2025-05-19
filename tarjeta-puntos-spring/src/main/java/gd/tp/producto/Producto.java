package gd.tp.producto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table( name = "Producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Producto {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String nombre;
    private Integer stock;
    private BigDecimal precio;
    private String thumbnail;

}
