package gd.tp.usuario.dto;

public class LoginResponse {

    private String token;
    private UsuarioDto usuarioDto;

    public LoginResponse(String token, UsuarioDto usuarioDto) {
        this.token = token;
        this.usuarioDto = usuarioDto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
	    "token='[PROTECTED]'" + // No mostramos el token real por seguridad
	    ", usuarioDto=" + usuarioDto +
	    '}';
    }

}
