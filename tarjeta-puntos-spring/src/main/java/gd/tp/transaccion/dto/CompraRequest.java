package gd.tp.transaccion.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompraRequest {
    private String idTarjeta;
    private List<CompraItemRequest> productos;
}
