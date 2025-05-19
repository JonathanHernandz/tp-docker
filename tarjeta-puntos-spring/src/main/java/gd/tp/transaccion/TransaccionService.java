package gd.tp.transaccion;

import gd.tp.cliente.Cliente;
import gd.tp.cliente.ClienteRepository;
import gd.tp.nivel.Nivel;
import gd.tp.nivel.NivelRepository;
import gd.tp.producto.Producto;
import gd.tp.producto.ProductoRepository;
import gd.tp.recompensa.Recompensa;
import gd.tp.recompensa.RecompensaRepository;
import gd.tp.tarjeta.Tarjeta;
import gd.tp.tarjeta.TarjetaRepository;
import gd.tp.transaccion.dto.CanjeResponse;
import gd.tp.transaccion.dto.CompraItemRequest;
import gd.tp.transaccion.dto.CompraResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransaccionService {

    private final ClienteRepository clienteRepository;
    private final TarjetaRepository tarjetaRepository;
    private final ProductoRepository productoRepository;
    private final RecompensaRepository recompensaRepository;
    private final TransaccionRepository transaccionRepository;
    private final NivelRepository nivelRepository;




    public List<Transaccion> getAllTransaccion(){
        return transaccionRepository.findAll();
    }
    public Transaccion saveTransaccion( Transaccion transaccion ){
        return transaccionRepository.save(transaccion);
    }

    @Transactional
    public CanjeResponse canjearProductoNivel( String idTarjeta, Long idProducto){
        Tarjeta tarjeta = tarjetaRepository.findById(idTarjeta)
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));

        Cliente cliente = clienteRepository.findById(tarjeta.getCliente().getIdCliente())
                .orElseThrow(()-> new RuntimeException("Cliente no encontrado") );

        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));


        Recompensa recompensa = recompensaRepository.findByProducto_IdProductoAndNivel_IdNivel(producto.getIdProducto(), tarjeta.getNivel().getIdNivel())
                .orElseThrow(() -> new RuntimeException("Recompensa no disponible para este nivel") );

        if(recompensa.getStock() == null || recompensa.getStock() <= 0 ){
            throw new RuntimeException("No hay stock disponible para esta recompensa");
        }

        if(producto.getStock() == null || producto.getStock() <= 0 ){
            throw new RuntimeException("No hay stock disponible en la tienda");
        }

        int puntosNecesarios = recompensa.getPuntosRequeridos();

        if(tarjeta.getSaldoPuntos() < puntosNecesarios){
            throw new RuntimeException("Saldo de puntos insuficiente para canjear este producto.");
        }

        // Logica de stock y puntos
        producto.setStock(producto.getStock() - 1);
        recompensa.setStock(recompensa.getStock() - 1);
        tarjeta.setSaldoPuntos(tarjeta.getSaldoPuntos() - puntosNecesarios);

        productoRepository.save(producto);
        tarjetaRepository.save(tarjeta);

        // Registro de transaccion

        Transaccion transaccion =  new Transaccion();

        transaccion.setFecha(Date.from(LocalDateTime.now()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        transaccion.setMonto(0.00);
        transaccion.setPuntosGastados(puntosNecesarios);
        transaccion.setPuntosObtenidos(0);
        transaccion.setTipo("CANJE");
        transaccion.setProducto(producto);
        transaccion.setTarjeta(tarjeta);

        transaccionRepository.save(transaccion);

        return new CanjeResponse(
                "Canje realizado con éxito",
                producto.getNombre(),
                puntosNecesarios,
                tarjeta.getSaldoPuntos()
        );
    }


    @Transactional
    public CanjeResponse canjearProductoSimple( String idTarjeta, Long idProducto){
        Tarjeta tarjeta = tarjetaRepository.findById(idTarjeta)
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));

        Cliente cliente = clienteRepository.findById(tarjeta.getCliente().getIdCliente())
                .orElseThrow(()-> new RuntimeException("Cliente no encontrado") );

        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));



/*        Recompensa recompensa = recompensaRepository.findByProducto_IdProductoAndNivel_IdNivel(producto.getIdProducto(), tarjeta.getNivel().getIdNivel())
                .orElseThrow(() -> new RuntimeException("Recompensa no disponible para este nivel") );*/

        Recompensa recompensa = recompensaRepository.findByProducto_IdProducto(producto.getIdProducto())
                .orElseThrow( () -> new RuntimeException("Recompensa no disponible para este producto") );

        if(recompensa.getStock() == null || recompensa.getStock() <= 0 ){
            throw new RuntimeException("No hay stock disponible para esta recompensa");
        }

        if(producto.getStock() == null || producto.getStock() <= 0 ){
            throw new RuntimeException("No hay stock disponible en la tienda");
        }

        int puntosNecesarios = recompensa.getPuntosRequeridos();

        if(tarjeta.getSaldoPuntos() < puntosNecesarios){
            throw new RuntimeException("Saldo de puntos insuficiente para canjear este producto.");
        }

        // Logica de stock y puntos
        producto.setStock(producto.getStock() - 1);
        recompensa.setStock(recompensa.getStock() - 1);
        tarjeta.setSaldoPuntos(tarjeta.getSaldoPuntos() - puntosNecesarios);

        productoRepository.save(producto);
        tarjetaRepository.save(tarjeta);

        // Registro de transaccion

        Transaccion transaccion =  new Transaccion();

        transaccion.setFecha(Date.from(LocalDateTime.now()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        transaccion.setMonto(0.00);
        transaccion.setPuntosGastados(puntosNecesarios);
        transaccion.setPuntosObtenidos(0);
        transaccion.setTipo("CANJE");
        transaccion.setProducto(producto);
        transaccion.setTarjeta(tarjeta);

        transaccionRepository.save(transaccion);

        return new CanjeResponse(
                "Canje realizado con éxito",
                producto.getNombre(),
                puntosNecesarios,
                tarjeta.getSaldoPuntos()
        );
    }

    @Transactional
    public CompraResponse compraProductos (String idTarjeta, List<CompraItemRequest> productosAComprar ){

        Tarjeta tarjeta = tarjetaRepository.findById(idTarjeta)
                .orElseThrow( ()-> new RuntimeException("Tarjeta no encontrada"));

        double totalPuntosGanados = 0;
        List<CompraResponse.DetalleCompra> detalles = new ArrayList<>();

        for (CompraItemRequest item : productosAComprar){
            Producto producto = productoRepository.findById(item.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: ID " + item.getIdProducto()));

            if(producto.getStock() < item.getCantidad()){
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            double montoTotalProducto = producto.getPrecio().doubleValue() * item.getCantidad();
            double puntosObtenidos = montoTotalProducto * (tarjeta.getNivel().getPorcentajeNivel()/100);

            // Actualizar stock y saldo
            producto.setStock(producto.getStock() - item.getCantidad());
            //tarjeta.setSaldoPuntos(tarjeta.getSaldoPuntos() + puntosObtenidos);

            productoRepository.save(producto);

            //Registrar transaccion individuala por producto
            Transaccion transaccion = new Transaccion();

            transaccion.setFecha(new Date());
            transaccion.setMonto(montoTotalProducto);
            //transaccion.setPuntosObtenidos(montoTotalProducto);
            transaccion.setPuntosObtenidos(puntosObtenidos);
            transaccion.setPuntosGastados(0);
            transaccion.setTipo("COMPRA");
            transaccion.setProducto(producto);
            transaccion.setTarjeta(tarjeta);
            transaccion.setCantidad(item.getCantidad());

            transaccionRepository.save(transaccion);

            totalPuntosGanados+= puntosObtenidos;

            detalles.add(new CompraResponse.DetalleCompra(
                    producto.getNombre(),
                    item.getCantidad(),
                    montoTotalProducto,
                    puntosObtenidos
            ));

        }
        tarjeta.setSaldoPuntos(tarjeta.getSaldoPuntos() + totalPuntosGanados);
        // validacion si sube de nivel o no.
        List<Nivel> siguienteNivel = nivelRepository.findNextNivel(tarjeta.getNivel().getPuntosMinimo());

        if(!siguienteNivel.isEmpty()){
            Nivel proximo = siguienteNivel.get(0);
            if(tarjeta.getSaldoPuntos() >= proximo.getPuntosMinimo()){
                tarjeta.setNivel(proximo);
            }
        }


        tarjetaRepository.save(tarjeta);
        return new CompraResponse(
                "Compra realizada con éxito",
                totalPuntosGanados,
                tarjeta.getSaldoPuntos(),
                detalles
        );
    }
}
