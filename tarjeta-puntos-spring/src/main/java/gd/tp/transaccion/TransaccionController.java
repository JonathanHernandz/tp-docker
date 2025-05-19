package gd.tp.transaccion;

import gd.tp.nivel.Nivel;
import gd.tp.nivel.NivelRepository;
import gd.tp.transaccion.dto.CanjeRequest;
import gd.tp.transaccion.dto.CanjeResponse;
import gd.tp.transaccion.dto.CompraRequest;
import gd.tp.transaccion.dto.CompraResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@PreAuthorize("hasAnyRole('CAJERO', 'ADMIN')")
@RestController
@RequestMapping("/api/transaccion")
public class TransaccionController {
    @Autowired
    private TransaccionService transaccionService;




    @GetMapping
    public List<Transaccion> listar(){
        return transaccionService.getAllTransaccion();
    }

    /*@PostMapping
    public Transaccion crear(@RequestBody Transaccion transaccion){
        return transaccionService.saveTransaccion(transaccion);
    }*/

    @PostMapping("/canje-por-nivel")
    public ResponseEntity<?> canjearNivel(@RequestBody CanjeRequest request){
        try{
            CanjeResponse response = transaccionService.canjearProductoNivel(request.getIdTarjeta(), request.getIdProducto());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/canje-simple")
    public ResponseEntity<?> canjearSimple(@RequestBody CanjeRequest request){
        try{
            CanjeResponse response = transaccionService.canjearProductoSimple(request.getIdTarjeta(), request.getIdProducto());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/compra")
    public ResponseEntity<?> comprar (@RequestBody CompraRequest request){
        try{
            CompraResponse response = transaccionService.compraProductos(request.getIdTarjeta(), request.getProductos());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()  ));
        }
    }
}


