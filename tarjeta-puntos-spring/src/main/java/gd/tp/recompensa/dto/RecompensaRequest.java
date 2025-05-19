package gd.tp.recompensa.dto;

import lombok.Data;

@Data
public class RecompensaRequest {
    private Long idProducto;
    private Long idNivel;
    private Integer puntosRequeridos;
    private Integer stock;
}
