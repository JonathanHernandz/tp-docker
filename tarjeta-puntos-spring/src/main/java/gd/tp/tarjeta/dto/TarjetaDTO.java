package gd.tp.tarjeta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import gd.tp.cliente.dto.ClienteDTO;
import gd.tp.nivel.Nivel;
import lombok.Data;

import java.util.Date;

@Data
public class TarjetaDTO {
    private String idTarjeta;
    private Double saldoPuntos;
    private Date fechaEmision;
    private boolean estado;
    @JsonProperty("cliente")
    private ClienteDTO clienteDTO;
    private Nivel nivel;

}
