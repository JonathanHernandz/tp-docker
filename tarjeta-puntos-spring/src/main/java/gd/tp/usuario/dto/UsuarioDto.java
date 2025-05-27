package gd.tp.usuario.dto;

import gd.tp.usuario.Usuario;

public class UsuarioDto {

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    private Long idUsuario;
    private String nombre;
    private String correo;
    private String telefono;
    private String username;
    private String rol;
    private String estado;
    private String thumbnail;

    public UsuarioDto(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nombre = usuario.getNombre();
        this.correo = usuario.getCorreo();
        this.telefono = usuario.getTelefono();
        this.username = usuario.getUsername();
        this.rol = usuario.getRol();
        this.estado = usuario.getEstado();
        this.thumbnail = usuario.getThumbnail();
    }
}
