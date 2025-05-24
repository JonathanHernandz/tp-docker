package gd.tp.cliente;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import gd.tp.tarjeta.Tarjeta;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;


@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nombre;
    private String email;
    private String numero;
    private LocalDate birthdate;
    private String thumbnail;

    private boolean verificado = false;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference //se agrega praa que se maneje la relacion
    private List<Tarjeta> tarjetas;

    public Cliente() {
    }

    public Cliente(String nombre, String email, String numero, LocalDate birthdate) {
        this.nombre = nombre;
        this.email = email;
        this.numero = numero;
        this.birthdate = birthdate;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public Long getId() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public void addTarjeta(Tarjeta tarjeta) {
        tarjetas.add(tarjeta);
        tarjeta.setCliente(this);
    }

    public void removeTarjeta(Tarjeta tarjeta) {
        tarjetas.remove(tarjeta);
        tarjeta.setCliente(null);
    }

    @Override
    public String toString() {
        return "Cliente{" +
	    "idCliente=" + idCliente +
	    ", nombre='" + nombre + '\'' +
	    ", email='" + email + '\'' +
	    ", numero='" + numero + '\'' +
	    ", birthdate=" + birthdate +
	    ", thumbnail='" + thumbnail + '\'' +
	    ", verificado=" + verificado +
	    '}';
    }
}
