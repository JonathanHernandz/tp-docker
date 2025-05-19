package gd.tp.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping( "/api/producto" )
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PreAuthorize("hasAnyRole('CAJERO', 'ADMIN')")
    @GetMapping
    public List<Producto> listar(){
        return productoService.getAllProducto();
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto){
        return productoService.saveProducto(producto);
    }


    @PutMapping("/{idProducto}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long idProducto, @RequestBody Producto productoDetails){
        Producto updateProducto = productoService.updateProducto(idProducto, productoDetails);
        return ResponseEntity.ok(updateProducto);
    }

    @PreAuthorize("hasAnyRole('CAJERO', 'ADMIN')")
    @GetMapping("/{idProducto}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long idProducto){
        Producto producto = productoService.getProductoById(idProducto);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/delete/{idProducto}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long idProducto){
        productoService.deleteProducto(idProducto);
        return ResponseEntity.noContent().build();
    }



}
