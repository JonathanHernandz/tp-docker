package gd.tp.recompensa;


import gd.tp.nivel.Nivel;
import gd.tp.nivel.NivelRepository;
import gd.tp.producto.Producto;
import gd.tp.producto.ProductoRepository;
import gd.tp.recompensa.dto.RecompensaRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/api/recompensa")
public class RecompensaController {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private NivelRepository nivelRepository;

    @Autowired
    private RecompensaRepository recompensaRepository;

    @Autowired
    private RecompensaService recompensaService;

    @PreAuthorize("permitAll()")
    @GetMapping
    public List<Recompensa> listar(){
        return recompensaService.getAllRecompensa();
    }

    /*@PostMapping
    public Recompensa crear(@RequestBody Recompensa recompensa){
        return recompensaService.saveRecompensa(recompensa);
    }*/
    @PostMapping("/crear")
    public ResponseEntity<?> crearRecompensa(@RequestBody RecompensaRequest recompensaRequest){
        try {
            Recompensa recompensa = recompensaService.crearRecompensa(recompensaRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(recompensa);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al crear la recompensa.");
        }
    }

    @DeleteMapping("/delete/{idRecompensa}")
    public ResponseEntity<Void> deleteRecompensa(@PathVariable Long idRecompensa){
        recompensaService.deleteRecompensa(idRecompensa);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll(){
        recompensaRepository.deleteAll();
        return ResponseEntity.ok("Todas las recompensas han sido eliminadas exitosamente.");

    }

    @PutMapping("/{idRecompensa}")
    public ResponseEntity<?> actualizarRecompensa(@PathVariable Long idRecompensa, @RequestBody RecompensaRequest recompensaRequest) {
        Recompensa recompensaExistente = recompensaRepository.findById(idRecompensa)
                .orElseThrow(() -> new RuntimeException("Recompensa no encontrada con id: " + idRecompensa));

        Producto producto = productoRepository.findById(recompensaRequest.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Nivel nivel = nivelRepository.findById(recompensaRequest.getIdNivel())
                .orElseThrow(() -> new RuntimeException("Nivel no encontrado"));

        // Verificar que no exista otra recompensa con el mismo producto y nivel (que no sea esta misma)
        boolean existeDuplicado = recompensaRepository.existsByProducto_IdProductoAndNivel_IdNivelAndIdRecompensaNot(
                producto.getIdProducto(), nivel.getIdNivel(), idRecompensa);

        if (existeDuplicado) {
            return ResponseEntity.badRequest().body("Ya existe una recompensa para este producto y nivel");
        }

        recompensaExistente.setProducto(producto);
        recompensaExistente.setNivel(nivel);
        recompensaExistente.setPuntosRequeridos(recompensaRequest.getPuntosRequeridos());
        recompensaExistente.setStock(recompensaRequest.getStock());
        recompensaRepository.save(recompensaExistente);


        return ResponseEntity.ok(recompensaExistente);
    }


}