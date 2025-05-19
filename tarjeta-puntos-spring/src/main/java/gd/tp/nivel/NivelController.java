package gd.tp.nivel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/api/nivel")
public class NivelController {
    @Autowired
    private NivelService nivelService;


    @GetMapping
    public List<Nivel> listar(){
        return nivelService.getAllNivel();
    }

    @GetMapping("/validar")
    public boolean validarNiveles(){
        return nivelService.nivelesObligatoriosCompletos();
    }

    // Agregar un solo nivel
    @PostMapping
    public Nivel crear(@RequestBody Nivel nivel){
        // Buscar todos los niveles existentes ordenados por ID
        List<Nivel> existentes = nivelService.findAll(); // Asumimos que tienes este método

        // Buscar el siguiente ID disponible
        long idDisponible = 1;
        Set<Long> usados = existentes.stream()
                .map(Nivel::getIdNivel)
                .collect(Collectors.toSet());

        while (usados.contains(idDisponible)) {
            idDisponible++;
        }

        nivel.setIdNivel(idDisponible);
        return nivelService.saveNivel(nivel);
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> crearNiveles ( @RequestBody List<Nivel> niveles ){
        if(niveles.size()==3){
            nivelService.saveAllNiveles(niveles);
            return ResponseEntity.ok("Niveles registrados correctamente.");
        }
        else{
            return ResponseEntity.badRequest().body(" Debe enviar 3 niveles. ");
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarNivel(@PathVariable Long id){
        try{
            nivelService.eliminarNivel(id);
            return ResponseEntity.ok("Nivel eliminado correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/reset")
    public ResponseEntity<String> resetearNiveles(){
        try{
            nivelService.resetearNiveles();
            return ResponseEntity.ok("Niveles reseteados correctamente. Todos los registros están asociados al nivel básico.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{idNivel}")
    public ResponseEntity<Nivel> updateNivel(@PathVariable Long idNivel, @RequestBody Nivel nivelDetails){
        Nivel updateNivel = nivelService.updateNivel(idNivel, nivelDetails);
        return ResponseEntity.ok(updateNivel);
    }


}
