package gd.tp.transaccion.dto;

public class CanjeResponse {

    private String mensaje;
    private String producto;
    private int puntosGastados;
    private double saldoRestantes;

    public CanjeResponse() {
    }

    public CanjeResponse(String mensaje, String producto, int puntosGastados, double saldoRestantes) {
        this.mensaje = mensaje;
        this.producto = producto;
        this.puntosGastados = puntosGastados;
        this.saldoRestantes = saldoRestantes;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getPuntosGastados() {
        return puntosGastados;
    }

    public void setPuntosGastados(int puntosGastados) {
        this.puntosGastados = puntosGastados;
    }

    public double getSaldoRestantes() {
        return saldoRestantes;
    }

    public void setSaldoRestantes(double saldoRestantes) {
        this.saldoRestantes = saldoRestantes;
    }

    @Override
    public String toString() {
        return "CanjeResponse{" +
	    "mensaje='" + mensaje + '\'' +
	    ", producto='" + producto + '\'' +
	    ", puntosGastados=" + puntosGastados +
	    ", saldoRestantes=" + saldoRestantes +
	    '}';
    }
}
