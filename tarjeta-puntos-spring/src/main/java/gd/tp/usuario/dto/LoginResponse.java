package gd.tp.usuario.dto;

import gd.tp.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private UsuarioDto usuarioDto;

}
