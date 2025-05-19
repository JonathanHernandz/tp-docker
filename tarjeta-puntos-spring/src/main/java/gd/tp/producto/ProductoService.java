package gd.tp.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProducto(){
        return productoRepository.findAll();
    }

    public Producto saveProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public Producto getClienteById(Long idProducto){
        Optional<Producto> producto = productoRepository.findById(idProducto);
        return producto.orElseThrow(() -> new RuntimeException("Cliente no encontrado") );
    }

    public Producto updateProducto(Long idProducto, Producto productoDetails){
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID:" + idProducto) );

        producto.setNombre(productoDetails.getNombre());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setStock(producto.getStock());
        //producto.setPuntosRequeridos(producto.getPuntosRequeridos());
        producto.setThumbnail(productoDetails.getThumbnail());
        producto.setStock(productoDetails.getStock());

        return productoRepository.save(producto);
    }

    public Producto getProductoById(Long idProducto){
        Optional<Producto> producto = productoRepository.findById(idProducto);
        return producto.orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public void deleteProducto(Long id){
        productoRepository.deleteById(id);
    }

}
