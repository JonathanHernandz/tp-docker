package gd.tp.transaccion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompraItemRequest {
    private Long idProducto;
    private int cantidad;

}
