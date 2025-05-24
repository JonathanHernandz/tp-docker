package gd.tp.nivel;

import jakarta.persistence.*;

@Entity
@Table( name = "Nivel")

public class Nivel {

    @Id
    //@GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idNivel;

    private String nombreNivel;
    private double porcentajeNivel;

    private Integer puntosMinimo;

    public Long getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Long idNivel) {
        this.idNivel = idNivel;
    }

    public String getNombreNivel() {
        return nombreNivel;
    }

    public void setNombreNivel(String nombreNivel) {
        this.nombreNivel = nombreNivel;
    }

    public double getPorcentajeNivel() {
        return porcentajeNivel;
    }

    public void setPorcentajeNivel(double porcentajeNivel) {
        this.porcentajeNivel = porcentajeNivel;
    }

    public Integer getPuntosMinimo() {
        return puntosMinimo;
    }

    public void setPuntosMinimo(Integer puntosMinimo) {
        this.puntosMinimo = puntosMinimo;
    }

    @Override
    public String toString() {
        return "Nivel{" +
	    "idNivel=" + idNivel +
	    ", nombreNivel='" + nombreNivel + '\'' +
	    ", porcentajeNivel=" + porcentajeNivel +
	    ", puntosMinimo=" + puntosMinimo +
	    '}';
    }

}
