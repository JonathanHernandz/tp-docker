package gd.tp.recompensa.dto;

public class RecompensaRequest {
    private Long idProducto;
    private Long idNivel;
    private Integer puntosRequeridos;
    private Integer stock;

    public RecompensaRequest() {
    }

    public RecompensaRequest(Long idProducto, Long idNivel, Integer puntosRequeridos, Integer stock) {
        this.idProducto = idProducto;
        this.idNivel = idNivel;
        this.puntosRequeridos = puntosRequeridos;
        this.stock = stock;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Long idNivel) {
        this.idNivel = idNivel;
    }

    public Integer getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(Integer puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "RecompensaRequest{" +
	    "idProducto=" + idProducto +
	    ", idNivel=" + idNivel +
	    ", puntosRequeridos=" + puntosRequeridos +
	    ", stock=" + stock +
	    '}';
    }
}
