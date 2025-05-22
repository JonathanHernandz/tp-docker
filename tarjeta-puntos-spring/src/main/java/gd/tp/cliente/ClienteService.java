package gd.tp.cliente;

import gd.tp.email.EmailService;
import gd.tp.nivel.Nivel;
import gd.tp.nivel.NivelRepository;
import gd.tp.tarjeta.Tarjeta;
import gd.tp.tarjeta.TarjetaRepository;
import gd.tp.verificacion.VerificacionToken;
import gd.tp.verificacion.VerificacionTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private NivelRepository nivelRepository;

    @Autowired
    private EmailService emailService; // Inyectamos el servicio de correo

    @Autowired
    private VerificacionTokenRepository verificacionTokenRepository;

    /*public Cliente crearCliente( Cliente cliente){
        Cliente newCliente = clienteRepository.save(cliente);

        Nivel nivel =  nivelRepository.findFirstByOrderByIdNivelAsc()
                .orElseThrow( ()-> new RuntimeException("No hay niveles registrados") );

        Tarjeta newTarjeta = new Tarjeta();
        newTarjeta.setCliente(newCliente);
        newTarjeta.setSaldoPuntos(50);
        newTarjeta.setFechaEmision(new Date());
        newTarjeta.setNivel(nivel);

        tarjetaRepository.save(newTarjeta);

        return newCliente;
    }*/

    public Cliente crearCliente(Cliente cliente){
        /*boolean emailExistente = clienteRepository.existsByEmail(cliente.getEmail());
        boolean numeroExistente = clienteRepository.existsByNumero(cliente.getNumero());

        if (emailExistente && numeroExistente) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este correo y número telefónico ya están registrados");
        } else if (emailExistente) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este correo ya está registrado");
        } else if (numeroExistente) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este número ya está registrado");
        }*/

        cliente.setVerificado(false);
        Cliente newCliente = clienteRepository.save(cliente);

        String token = UUID.randomUUID().toString();
        VerificacionToken verif = new VerificacionToken();
        verif.setToken(token);
        verif.setFechaExpiracion(LocalDateTime.now().plusHours(24));
        verif.setCliente(newCliente);
        verificacionTokenRepository.save(verif);

/*
        String link = "http://localhost:8080/api/cliente/verificar?token=" + token;
        String mensaje = "<p>Hola " + newCliente.getNombre() + ",</p>"
                        + "<p>Haz clic en el siguiente enlace para verificar tu cuenta:</p>"
                        + "<p><a href=\"" + link + "\">Verificar cuenta</a></p>"
                        + "<p>Este enlace expira en 24 horas.</p>";

        emailService.sendEmail(newCliente.getEmail(), "Verificacion de cuenta", mensaje);
*/
        System.out.println(cliente.getNumero());
        return newCliente;

    }

    public void verificarCuentaYCrearTarjetaa(String token){
        VerificacionToken verif = verificacionTokenRepository.findByToken(token);

        if(verif == null || verif.getFechaExpiracion().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Token inválido o expirado");
        }

        Cliente cliente = verif.getCliente();
        cliente.setVerificado(true);
        clienteRepository.save(cliente);

        verificacionTokenRepository.delete(verif);

        Nivel nivel = nivelRepository.findFirstByOrderByIdNivelAsc()
                .orElseThrow(() -> new RuntimeException("No hay niveles registrados"));

        // Obtener fecha actual
        LocalDate fechaActual = LocalDate.now();
        String aa = String.format("%02d", fechaActual.getYear() % 100); // últimos 2 dígitos del año
        String mm = String.format("%02d", fechaActual.getMonthValue()); // mes

        // Obtener últimos 4 dígitos del ID del cliente (rellenado con ceros si es necesario)
        String idClienteStr = String.format("%04d", cliente.getIdCliente() % 10000);

        // Asignar idTarjeta con formato AAMM0001XXXX
        String idTarjeta = aa + mm + "0001" + idClienteStr;


        Tarjeta tarjeta =  new Tarjeta();
        tarjeta.setIdTarjeta(idTarjeta); // ponemos el id
        tarjeta.setCliente(cliente);
        tarjeta.setSaldoPuntos(50);
        tarjeta.setFechaEmision(new Date());
        tarjeta.setNivel(nivel);

        tarjetaRepository.save(tarjeta);


        String qrUrl = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + "https://membresiasvip.miishelados.com/clienteReview?id=" + idTarjeta;

        String mensaje = "<html>"
                + "<p>Hola " + cliente.getNombre() + ",</p>"
                + "<p>🎉 Tu cuenta ha sido verificada exitosamente. 🎉</p>"
                + "<p>Ya puedes disfrutar de nuestras promociones en MIISHELADOS.</p>"
                + "<p>Este es tú numero de Tarjeta💳:" + idTarjeta + "</p>"
                + "<p>Este es tu código QR:</p>"
                + "<p><img src='" + qrUrl + "' alt='QR Code' width='150' height='150' /></p>"
                + "<p><a href=\"https://membresiasvip.miishelados.com/consulta/\" target=\"_blank\">Ir al sitio</a></p>"
                + "<p>Saludos,<br>MIISHELADOS</p>"
                + "</html>";

        emailService.sendEmail(cliente.getEmail(), "Cuenta verificada y tarjeta activada", mensaje);
        System.out.println(cliente.getNumero());
    }


    public Cliente saveCliente(Cliente cliente){
         return clienteRepository.save(cliente);
    }
    public List<Cliente> getAllCliente() {
        return clienteRepository.findAll(); // Si usas JPA
    }

    public Cliente getClienteById(Long idCliente) {
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        return cliente.orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public Cliente updateCliente(Long idCliente, Cliente clienteDetails){
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(()-> new RuntimeException("Cliente no encontrado con ID: " + idCliente));
        cliente.setNombre(clienteDetails.getNombre());
        cliente.setEmail(clienteDetails.getEmail());
        cliente.setNumero(clienteDetails.getNumero());
        cliente.setBirthdate(clienteDetails.getBirthdate());
        cliente.setThumbnail(clienteDetails.getThumbnail());

        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long idCliente){
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(()-> new RuntimeException("Cliente no encontrado con ID: " + idCliente));

        clienteRepository.delete(cliente);
    }
}
