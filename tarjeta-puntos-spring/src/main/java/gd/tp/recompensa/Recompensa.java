package gd.tp.recompensa;


import gd.tp.nivel.Nivel;
import gd.tp.producto.Producto;
import jakarta.persistence.*;
import org.springframework.data.repository.NoRepositoryBean;

@Entity
@Table( name = "Recompensa")

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

    public Long getIdRecompensa() {
        return idRecompensa;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Integer getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(Integer puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Recompensa{" +
	    "idRecompensa=" + idRecompensa +
	    ", puntosRequeridos=" + puntosRequeridos +
	    ", stock=" + stock +
	    '}';
    }
}
