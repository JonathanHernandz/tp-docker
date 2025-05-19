package gd.tp.transaccion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data

public class CompraResponse {
    private String mensaje;
    private double puntosGanados;
    private double saldoPuntosActual;
    private List<DetalleCompra> detalles;

    public CompraResponse(String mensaje, double puntosGanados, double saldoPuntosActual, List<DetalleCompra> detalles){
        this.mensaje = mensaje;
        this.puntosGanados = puntosGanados;
        this.saldoPuntosActual = saldoPuntosActual;
        this.detalles = detalles;
    }

    @Getter
    public static  class DetalleCompra{

        private  String producto;
        private int cantidad;
        private double monto;
        private double puntosObtenidos;

        public DetalleCompra(String producto, int cantidad, double monto, double puntosObtenidos){
            this.producto = producto;
            this.cantidad = cantidad;
            this.monto =  monto;
            this.puntosObtenidos = puntosObtenidos;
        }

    }

}
