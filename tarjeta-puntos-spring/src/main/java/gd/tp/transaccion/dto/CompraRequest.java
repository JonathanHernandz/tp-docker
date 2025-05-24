package gd.tp.transaccion.dto;

import java.util.List;

public class CompraRequest {
    private String idTarjeta;
    private List<CompraItemRequest> productos;

    public CompraRequest() {
    }
    
    public CompraRequest(String idTarjeta, List<CompraItemRequest> productos) {
        this.idTarjeta = idTarjeta;
        this.productos = productos;
    }

    public String getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(String idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public List<CompraItemRequest> getProductos() {
        return productos;
    }

    public void setProductos(List<CompraItemRequest> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "CompraRequest{" +
	    "idTarjeta='" + idTarjeta + '\'' +
	    ", productos.size=" + (productos != null ? productos.size() : 0) +
	    '}';
    }
}
