package gd.tp.transaccion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CanjeResponse {

    private String mensaje;
    private String producto;
    private int puntosGastados;
    private double saldoRestantes;



}
