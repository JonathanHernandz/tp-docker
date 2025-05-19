package gd.tp.tarjeta;

import gd.tp.tarjeta.dto.TarjetaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/api/tarjeta")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    /*@GetMapping
    public List<Tarjeta> getAllTarjetas(){
        return tarjetaService.getAllTarjetas();
    }*/

    @GetMapping
    public List<TarjetaDTO> getAllTarjetas() {
        return tarjetaService.getAllTarjetas();
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Tarjeta> getTarjetaById(@PathVariable Long id){
        Tarjeta tarjeta = tarjetaService.getTarjetaById(id);
        return ResponseEntity.ok(tarjeta);
    }*/

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<TarjetaDTO> getTarjetaById(@PathVariable String id){
        return ResponseEntity.ok(tarjetaService.getTarjetaById(id));
    }

    @PostMapping
    public Tarjeta crearTarjeta(@RequestBody Tarjeta tarjeta){
        return tarjetaService.saveTarjeta(tarjeta);
    }


}
