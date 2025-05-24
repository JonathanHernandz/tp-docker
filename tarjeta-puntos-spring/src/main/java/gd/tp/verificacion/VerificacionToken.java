package gd.tp.verificacion;

import gd.tp.cliente.Cliente;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class VerificacionToken {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String token;
    private LocalDateTime fechaExpiracion;

    @OneToOne
    private Cliente cliente;

    public VerificacionToken() {
    }

    public VerificacionToken(String token, LocalDateTime fechaExpiracion, Cliente cliente) {
        this.token = token;
        this.fechaExpiracion = fechaExpiracion;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(fechaExpiracion);
    }

    @Override
    public String toString() {
        return "VerificacionToken{" +
	    "id=" + id +
	    ", token='[PROTECTED]'" +
	    ", fechaExpiracion=" + fechaExpiracion +
	    ", clienteId=" + (cliente != null ? cliente.getId() : null) +
	    '}';
    }
}
