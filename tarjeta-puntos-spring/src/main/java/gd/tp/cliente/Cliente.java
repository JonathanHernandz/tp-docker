package gd.tp.cliente;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import gd.tp.tarjeta.Tarjeta;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
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


}
