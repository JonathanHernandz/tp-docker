package gd.tp.verificacion;

import gd.tp.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificacionTokenRepository extends JpaRepository<VerificacionToken, Long> {
    VerificacionToken findByToken(String token);

    VerificacionToken findByCliente(Cliente cliente);

}
