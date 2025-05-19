package gd.tp.cliente;

import gd.tp.nivel.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByEmail( String email );
    boolean existsByNumero( String numero );


}
