package gd.tp.recuperacion;

import gd.tp.recuperacion.dto.CambiarPasswordRequest;
import gd.tp.recuperacion.dto.SolicitarCodigoRequest;
import gd.tp.recuperacion.dto.VerificarCodigoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@PreAuthorize("permitAll()")
@RestController
@RequestMapping("/api/recuperacion")
public class PasswordResetTokenController {

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    /*@PostMapping("/solicitar-codigo")
    public ResponseEntity<?> solicitarCodigo(@RequestBody Map<String, String> body){
        String email = body.get("email");
        boolean enviado = passwordResetTokenService.enviarCodigo(email);
        // enviado ? ResponseEntity.ok("Código enviado") : ResponseEntity.notFound().build();
        return enviado ? ResponseEntity.ok("Código enviado") : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Correo no encontrado");
    }


    @PostMapping("/verificar-codigo")
    public ResponseEntity<?> verificarCodigo(@RequestBody Map<String, String> body){
        String email = body.get("email");
        String code = body.get("code");
        String nuevaPassword = body.get("nuevaPassword");

        String actualizado = passwordResetTokenService.verificarCodigoYActualizarPassword(email, code, nuevaPassword);
        if ("OK".equals(actualizado)) {
            return ResponseEntity.ok("Contraseña actualizada");
        } else {
            return ResponseEntity.badRequest().body(actualizado);
        }
    }*/

    @PostMapping("/solicitar-codigo")
    public ResponseEntity<?> solicitarCodigo(@RequestBody SolicitarCodigoRequest request){
        boolean enviado = passwordResetTokenService.enviarCodigo(request.getEmail());
        return enviado ? ResponseEntity.ok("Código enviado") : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Correo no encontrdao");
    }

    @PostMapping("/verificar-codigo")
    public ResponseEntity<?> verificarCodigo(@RequestBody VerificarCodigoRequest request){
        boolean valido = passwordResetTokenService.verificarCodigo(request.getEmail(), request.getCode());
        return valido  ? ResponseEntity.ok("Código valido") : ResponseEntity.badRequest().body("Código invalido o expirado");
    }

    @PostMapping("/cambiar-password")
    public ResponseEntity<?> cambiarPassword(@RequestBody CambiarPasswordRequest request){
        try {
            passwordResetTokenService.actualizarPassword(request.getEmail(), request.getNuevaPassword());
            return ResponseEntity.ok("Contraseña actualizada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al actualizar contraseña.");
        }

    }




}






