package gd.tp.nivel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NivelRepository extends JpaRepository<Nivel, Long> {

    Optional<Nivel> findByNombreNivel(String nombreNivel);

    //Obtener el primer nivel registrado en la bd
    Optional<Nivel> findFirstByOrderByIdNivelAsc();

    @Query("SELECT n FROM Nivel n WHERE n.puntosMinimo > :puntosActuales ORDER BY n.puntosMinimo ASC")
    List<Nivel> findNextNivel(@Param("puntosActuales") Integer puntosActuales);




}
