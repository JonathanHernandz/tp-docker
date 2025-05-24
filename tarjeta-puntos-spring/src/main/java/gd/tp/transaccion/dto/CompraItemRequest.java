package gd.tp.transaccion.dto;

public class CompraItemRequest {
    private Long idProducto;
    private int cantidad;

    public CompraItemRequest() {
    }

    public CompraItemRequest(Long idProducto, int cantidad) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "CompraItemRequest{" +
	    "idProducto=" + idProducto +
	    ", cantidad=" + cantidad +
	    '}';
    }
}
