package gd.tp.tarjeta;


import gd.tp.cliente.Cliente;
import gd.tp.cliente.dto.ClienteDTO;
import gd.tp.tarjeta.dto.TarjetaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaService {
    @Autowired
    private TarjetaRepository tarjetaRepository;

    /*public List<Tarjeta> getAllTarjetas(){
        returns tarjetaRepository.findAll();
    }*/

    public List<TarjetaDTO> getAllTarjetas() {
        List<Tarjeta> tarjetas = tarjetaRepository.findAll();
        return tarjetas.stream()
                .map(this::convertirAClienteDTO)
                .toList();
    }

    public Tarjeta saveTarjeta (Tarjeta tarjeta) {
        return tarjetaRepository.save(tarjeta);
    }

    /*public Tarjeta getTarjetaById( String id ){
        Optional <Tarjeta> tarjeta = tarjetaRepository.findById(id);
        return tarjeta.orElseThrow( ()-> new RuntimeException("Tarjeta no encontrada") );
    }*/

    public TarjetaDTO getTarjetaById(String id){
        Tarjeta tarjeta = tarjetaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Tarjeta no encontrada"));
        return convertirAClienteDTO(tarjeta);
    }

    private TarjetaDTO convertirAClienteDTO(Tarjeta tarjeta){
        TarjetaDTO dto = new TarjetaDTO();
        dto.setIdTarjeta(tarjeta.getIdTarjeta());
        dto.setSaldoPuntos(tarjeta.getSaldoPuntos());
        dto.setFechaEmision(tarjeta.getFechaEmision());
        dto.setEstado(tarjeta.isEstado());
        dto.setNivel(tarjeta.getNivel());

        // Convertir Cliente a ClienteDTO

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(tarjeta.getCliente().getIdCliente());
        clienteDTO.setEmail(tarjeta.getCliente().getEmail());
        clienteDTO.setNumero(tarjeta.getCliente().getNumero());
        clienteDTO.setNombre(tarjeta.getCliente().getNombre());
        clienteDTO.setThumbnail(tarjeta.getCliente().getThumbnail());

        dto.setClienteDTO(clienteDTO);

        return dto;

    }
}
