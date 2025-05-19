package gd.tp.nivel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "Nivel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Nivel {

    @Id
    //@GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idNivel;

    private String nombreNivel;
    private double porcentajeNivel;

    private Integer puntosMinimo;

}
