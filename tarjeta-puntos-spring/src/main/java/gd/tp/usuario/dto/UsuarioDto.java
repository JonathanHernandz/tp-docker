package gd.tp.usuario.dto;

import gd.tp.usuario.Usuario;

public class UsuarioDto {

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
