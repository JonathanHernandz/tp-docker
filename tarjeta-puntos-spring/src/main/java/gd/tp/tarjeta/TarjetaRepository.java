package gd.tp.tarjeta;

import gd.tp.nivel.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository extends JpaRepository<Tarjeta, String> {

    boolean existsByNivel(Nivel nivel);

}
