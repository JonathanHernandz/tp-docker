package gd.tp.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(  name = "Usuario")
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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    // Método toString() (excluyendo el password por seguridad)
    @Override
    public String toString() {
        return "Usuario{" +
	    "idUsuario=" + idUsuario +
	    ", nombre='" + nombre + '\'' +
	    ", correo='" + correo + '\'' +
	    ", telefono='" + telefono + '\'' +
	    ", username='" + username + '\'' +
	    ", rol='" + rol + '\'' +
	    ", estado='" + estado + '\'' +
	    ", thumbnail='" + thumbnail + '\'' +
	    '}';
    }

}
