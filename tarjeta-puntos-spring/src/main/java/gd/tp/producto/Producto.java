package gd.tp.producto;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table( name = "Producto")

public class Producto {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String nombre;
    private Integer stock;
    private BigDecimal precio;
    private String thumbnail;

    public Long getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    // Método toString() para logging/debugging
    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }

}
