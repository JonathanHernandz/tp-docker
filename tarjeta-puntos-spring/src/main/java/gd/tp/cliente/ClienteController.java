package gd.tp.cliente;


import gd.tp.email.EmailService;
import gd.tp.nivel.NivelRepository;
import gd.tp.verificacion.VerificacionTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VerificacionTokenRepository verificacionTokenRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private NivelRepository nivelRepository;


    @GetMapping
    public List<Cliente> getAllQuotes() {
        return clienteService.getAllCliente(); // Asegúrate de que este método exista en tu servicio
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long idCliente) {
        Cliente user = clienteService.getClienteById(idCliente);
        return ResponseEntity.ok(user);
    }

/*    @PostMapping
    public Cliente createQuote (@RequestBody Cliente cliente){
        //quoteService.saveQuote(quote);
        //Cliente savedCliente = clienteService.saveCliente(cliente);
        Cliente crearCliente = clienteService.crearCliente(cliente);

        String qrUrl = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + cliente.getNumero();

        // Enviar correo al usuario
        String subject = "Gracias por tu solicitud, " + cliente.getNombre();

        String message = "<html>"
                + "<p>Hola " + cliente.getNombre() + ",</p>"
                + "<p>Hemos recibido tu solicitud. Aquí están los datos que registraste:</p>"
                + "<ul>"
                + "<li><strong>Nombre:</strong> " + cliente.getNombre() + "</li>"
                + "<li><strong>Correo:</strong> " + cliente.getEmail() + "</li>"
                + "<li><strong>Número:</strong> " + cliente.getNumero() + "</li>"
                + "</ul>"
                + "<p>Pronto nos pondremos en contacto contigo.</p>"
                + "<p><strong>Escanea este código QR para más información:</strong></p>"
                + "<p><img src='" + qrUrl + "' alt='QR Code' width='150' height='150' /></p>"
                + "<p><a href=\"https://miishelados.com/\" target=\"_blank\" rel=\"noopener\">&iexcl;Clic aqu&iacute;!</a></p>"
                + "<p>Saludos,<br>MIISHELADOS</p>"
                + "</html>";

        emailService.sendEmail(cliente.getEmail(), subject, message);

        return crearCliente;
    }*/

    @PreAuthorize("permitAll()")
    @PostMapping("/formulario")
    public ResponseEntity<?> registrarCliente(@RequestBody Cliente cliente){
        boolean emailExistente = clienteRepository.existsByEmail(cliente.getEmail());
        boolean numeroExistente = clienteRepository.existsByNumero(cliente.getNumero());

        if (emailExistente && numeroExistente) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", "Este correo y número telefónico ya están registrados"));
        } else if (emailExistente) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", "Este correo ya está registrado"));
        } else if (numeroExistente) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", "Este número ya está registrado"));
        }
        Cliente nuevoCliente = clienteService.crearCliente(cliente);

        String linkVerificacion = "https://api-helados.arashi.solutions/api/cliente/verificar?token="
                + verificacionTokenRepository.findByCliente(nuevoCliente).getToken();

        String mensaje = "<html>"
                + "<p>Hola " + cliente.getNombre() + ",</p>"
                + "<p>Gracias por registrarte en MIISHELADOS.</p>"
                + "<p>Para completar tu registro, por favor haz clic en el siguiente enlace para verificar tu cuenta:</p>"
                + "<p><a href=\"" + linkVerificacion + "\">Verificar cuenta</a></p>"
                + "<p>Este enlace expirará en 24 horas.</p>"
                + "<p>Saludos,<br>MIISHELADOS</p>"
                + "</html>";

        String asunto = "Verificación de cuenta - MISSHELADOS";

        emailService.sendEmail(cliente.getEmail(), asunto, mensaje);

        return ResponseEntity.ok("Registro exitoso. Por favor verifica tu cuenta desde tu correo");

    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long idCliente, @RequestBody Cliente clienteDetails){
        Cliente updateCliente =  clienteService.updateCliente(idCliente, clienteDetails);
        return ResponseEntity.ok(updateCliente);

    }

    @DeleteMapping("/delete/{idCliente}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long idCliente){
        throw new UnsupportedOperationException("La eliminación de clientes está deshabilitada por ahora.");
        //clienteService.deleteCliente(idCliente);
        //return ResponseEntity.noContent().build();
    }

/*    @GetMapping("/verificar")
    public ResponseEntity<String> verificarCuenta(@RequestParam("token") String token){

        try{
            clienteService.verificarCuentaYCrearTarjetaa(token);

            return ResponseEntity.ok().body("<html><body><h3>Cuenta verificada con éxito. ¡Gracias!</h3><a href='https://miishelados.com/'>Ir al sitio</a></body></html>");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("<html><body><h3>" + e.getMessage() + "</h3></body></html>");
        }
    }*/

    @PreAuthorize("permitAll()")
    @GetMapping("/verificar")
    public ResponseEntity<String> verificarCuenta(@RequestParam("token") String token){

        try{
            clienteService.verificarCuentaYCrearTarjetaa(token);
            String html = """
            <html>
                    <head>
                    <meta http-equiv="refresh" content="5; URL='https://miishelados.com/'" />
                    </head>
                    <body>
                    <h3>Cuenta verificada con éxito. Serás redirigido en 5 segundos...</h3>
                    <a href='https://miishelados.com/'>Haz clic aquí si no eres redirigido</a>
                    </body>
                    </html>""";
                    return ResponseEntity.ok().body(html);
                }catch (RuntimeException e){
                    return ResponseEntity.badRequest().body("<html><body><h3>" + e.getMessage() + "</h3></body></html>");
                }
            }

        }
