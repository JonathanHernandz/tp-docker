package gd.tp.recompensa;

import gd.tp.nivel.Nivel;
import gd.tp.nivel.NivelRepository;
import gd.tp.producto.Producto;
import gd.tp.producto.ProductoRepository;
import gd.tp.recompensa.dto.RecompensaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecompensaService {
    @Autowired
    private RecompensaRepository recompensaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private NivelRepository nivelRepository;


    public List<Recompensa> getAllRecompensa(){
        return recompensaRepository.findAll();
    }
    public  Recompensa saveRecompensa( Recompensa recompensa ){
        return recompensaRepository.save(recompensa);
    }

    public Recompensa crearRecompensa(RecompensaRequest recompensaRequest){
        Producto producto = productoRepository.findById(recompensaRequest.getIdProducto())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        Nivel nivel = nivelRepository.findById(recompensaRequest.getIdNivel())
                .orElseThrow(()-> new IllegalArgumentException("Nivel no encontrado"));

        boolean existe = recompensaRepository.existsByProducto_IdProductoAndNivel_IdNivel(producto.getIdProducto(), nivel.getIdNivel());

        if(existe) throw new IllegalArgumentException("Ya existe una recompensa registrada para este producto y nivel");

        Recompensa recompensa =  new Recompensa();
        recompensa.setProducto(producto);
        recompensa.setNivel(nivel);
        recompensa.setPuntosRequeridos(recompensaRequest.getPuntosRequeridos());
        recompensa.setStock(recompensaRequest.getStock());

        return recompensaRepository.save(recompensa);
    }

    public void deleteRecompensa(Long idRecompensa){
        recompensaRepository.deleteById(idRecompensa);
    }


}
