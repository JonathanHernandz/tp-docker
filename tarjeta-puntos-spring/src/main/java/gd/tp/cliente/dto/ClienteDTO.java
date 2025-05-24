package gd.tp.cliente.dto;

public class ClienteDTO {

    private Long idCliente;
    private String nombre;
    private String email;
    private String numero;
    private String thumbnail;

    public ClienteDTO() {
    }

    public ClienteDTO(Long idCliente, String nombre, String email) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
    }

    public ClienteDTO(Long idCliente, String nombre, String email, String numero, String thumbnail) {
        this(idCliente, nombre, email);
        this.numero = numero;
        this.thumbnail = thumbnail;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
	    "idCliente=" + idCliente +
	    ", nombre='" + nombre + '\'' +
	    ", email='" + email + '\'' +
	    ", numero='" + numero + '\'' +
	    ", thumbnail='" + thumbnail + '\'' +
	    '}';
    }
}
