package gd.tp.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(  name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;
    private String correo;
    private String telefono;
    private String username;

    @Column(nullable = false)
   /* @Size(min = 8, max = 8, message = "La contraseña debe tener exactamente 8 caracteres")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8}$",
            message = "La contraseña debe tener 8 caracteres, incluyendo al menos una mayúscula, una minúscula, un número y un carácter especial (@#$%^&+=!)"
    )*/
    private String password;

    private String rol;  // Admin o Cajero
    private String estado;  // Activo/Inactivo
    private String thumbnail;

}
