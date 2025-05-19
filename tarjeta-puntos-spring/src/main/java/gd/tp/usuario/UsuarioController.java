package gd.tp.usuario;

import gd.tp.seguridad.JwtUtil;
import gd.tp.usuario.dto.LoginRequest;
import gd.tp.usuario.dto.LoginResponse;
import gd.tp.usuario.dto.UsuarioDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;


@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public List<Usuario> listarUsuario(){
        return usuarioService.getAllUsuario();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/registro")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario, BindingResult bindingResult){
    // Si hay errores de validación
            if (bindingResult.hasErrors()) {
                List<String> errores = bindingResult.getFieldErrors()
                        .stream()
                        .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        .toList();

                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("errores", errores));
            }

            // Validaciones personalizadas (ejemplo)
            // if (usuarioService.existsByUsername(usuario.getUsername())) {
            //     return ResponseEntity.badRequest().body(Map.of("message", "El nombre de usuario ya existe"));
            // }

            // Si todo está OK, guardar el usuario
            try {
                Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario);
                return ResponseEntity.ok(usuarioGuardado);
            } catch (Exception e) {
                return ResponseEntity
                        .badRequest()
                        .body(Map.of("message", "Error al guardar el usuario: " + e.getMessage()));
            }
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = usuarioService.findByUserName(loginRequest.getUsername());


        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Usuario no encontrado="));
        }
        boolean passworOK = passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword());

        if (!passworOK) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Contraseña incorrecta"));
        }

        // Generar token
        String token = jwtUtil.generateToken(usuario.getUsername(), usuario.getRol() );
        UsuarioDto usuarioDto = new UsuarioDto(usuario);
        LoginResponse response =  new LoginResponse(token, usuarioDto);
        //return ResponseEntity.ok(Collections.singletonMap("Mensaje", "Inicio de sesión exitoso"));
        //return ResponseEntity.ok(Collections.singletonMap("token", token));

        return ResponseEntity.ok(response);
    }

}
