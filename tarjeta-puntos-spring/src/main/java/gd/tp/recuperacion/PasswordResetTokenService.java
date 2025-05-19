package gd.tp.recuperacion;

import gd.tp.email.EmailService;
import gd.tp.usuario.Usuario;
import gd.tp.usuario.UsuarioRepository;
import gd.tp.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioService usuarioService;

    public boolean enviarCodigo(String email){
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreo(email);

        if(usuarioOptional.isEmpty()) return false;

        String code = String.format("%06d", new SecureRandom().nextInt(999999));

        PasswordResetToken token = new PasswordResetToken();

        token.setEmail(email);
        token.setCode(code);
        token.setExpiration(LocalDateTime.now().plusMinutes(15));
        token.setUsed(false);
        tokenRepository.save(token);

        emailService.sendResetCode(email, code);
        return true;
    }

//    /*public String verificarCodigoYActualizarPassword(String email, String code, String nuevaPassword){
//        Optional<PasswordResetToken> tokenOptional = tokenRepository.findByEmailAndCode(email, code);
//
//        if(tokenOptional.isEmpty()) return "Código inválido o expirado.";
//
//        PasswordResetToken token = tokenOptional.get();
//        if(token.isUsed()|| token.getExpiration().isBefore(LocalDateTime.now()))  return "Código inválido o expirado.";
//
//        try{
//            usuarioService.validarPassword(nuevaPassword);
//        }catch (IllegalArgumentException e){
//            return e.getMessage();
//        }
//        Usuario usuario = usuarioRepository.findByCorreo(email).orElseThrow();
//        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
//        usuarioRepository.save(usuario);
//
//        token.setUsed(true);
//        tokenRepository.save(token);
//
//        return "OK";
//    }*/

    public boolean verificarCodigo(String email, String code){
        Optional<PasswordResetToken> tokenOptional = tokenRepository.findByEmailAndCode(email, code);

        if(tokenOptional.isEmpty()) return false;

        PasswordResetToken token = tokenOptional.get();
        if(token.isUsed() || token.getExpiration().isBefore(LocalDateTime.now())) return false;

        token.setVerified(true);
        tokenRepository.save(token);

        return true;
    }

    public void actualizarPassword(String email, String nuevaPassword){

        usuarioService.validarPassword(nuevaPassword);

        Optional<PasswordResetToken> tokenOptional = tokenRepository.findFirstByEmailAndVerifiedTrueAndIsUsedFalse(email);



        Usuario usuario = usuarioRepository.findByCorreo(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        if(tokenOptional.isEmpty()){
            throw new IllegalArgumentException("No se ha verificado el código para cambiar la contraseña.");
        }

        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuarioRepository.save(usuario);

        PasswordResetToken token = tokenOptional.get();
        token.setUsed(true);
        tokenRepository.save(token);

/*        Optional<PasswordResetToken> tokenOptional = tokenRepository.findFirstByEmailAndIsUsedFalse(email);
        if (tokenOptional.isPresent()) {
            PasswordResetToken token = tokenOptional.get();
            token.setUsed(true);
            tokenRepository.save(token);
        }*/
        //return true;
    }


}
