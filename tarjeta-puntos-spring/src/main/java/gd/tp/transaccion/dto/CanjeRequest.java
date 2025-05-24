package gd.tp.transaccion.dto;

public class CanjeRequest {

    private String idTarjeta;
    private Long idProducto;

    public CanjeRequest() {
    }

    public CanjeRequest(String idTarjeta, Long idProducto) {
        this.idTarjeta = idTarjeta;
        this.idProducto = idProducto;
    }

    public String getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(String idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "CanjeRequest{" +
	    "idTarjeta='" + idTarjeta + '\'' +
	    ", idProducto=" + idProducto +
	    '}';
    }
}
