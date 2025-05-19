package gd.tp.recompensa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecompensaRepository extends JpaRepository<Recompensa, Long> {

    public boolean existsByProducto_IdProductoAndNivel_IdNivel(Long idProducto, Long idNivel);

    public boolean existsByProducto_IdProductoAndNivel_IdNivelAndIdRecompensaNot(Long idProducto, Long idNivel, Long idRecompensa);

    Optional<Recompensa> findByProducto_IdProductoAndNivel_IdNivel(Long idProducto, Long idNivel);

    Optional<Recompensa> findByProducto_IdProducto(Long idProducto);
}
