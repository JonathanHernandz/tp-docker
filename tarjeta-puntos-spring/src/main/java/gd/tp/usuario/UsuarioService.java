package gd.tp.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;


    private final PasswordEncoder passwordEncoder;


    public List<Usuario> getAllUsuario(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id){
        return usuarioRepository.findById(id);
    }

    public Usuario saveUsuario( Usuario usuario ){
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario( Long id ){
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> getUsuarioByEmail(String correo){
        return usuarioRepository.findByCorreo(correo);
    }

    public void validarPassword(String password){
        if (!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")) {
            throw new IllegalArgumentException("La contraseña debe tener mínimo 8 caracteres, incluyendo mayúscula, minúscula, número y carácter especial");
        }
    }

    public Usuario guardarUsuario(Usuario usuario){
        // Validar la contraseña sin cifrar
        validarPassword(usuario.getPassword());
        String passwordCifrada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordCifrada);
        return usuarioRepository.save(usuario);
    }

    public Usuario findByUserName(String username){
        return usuarioRepository.findByUsername(username);
    }

}
