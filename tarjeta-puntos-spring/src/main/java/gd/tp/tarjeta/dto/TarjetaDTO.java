package gd.tp.tarjeta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import gd.tp.cliente.dto.ClienteDTO;
import gd.tp.nivel.Nivel;

import java.util.Date;

public class TarjetaDTO {
    private String idTarjeta;
    private Double saldoPuntos;
    private Date fechaEmision;
    private boolean estado;
    @JsonProperty("cliente")
    private ClienteDTO clienteDTO;
    private Nivel nivel;

    public TarjetaDTO() {
    }

    public TarjetaDTO(String idTarjeta, Double saldoPuntos, Date fechaEmision, boolean estado) {
        this.idTarjeta = idTarjeta;
        this.saldoPuntos = saldoPuntos;
        this.fechaEmision = fechaEmision;
        this.estado = estado;
    }
    
    public TarjetaDTO(String idTarjeta, Double saldoPuntos, Date fechaEmision, 
		      boolean estado, ClienteDTO clienteDTO, Nivel nivel) {
        this(idTarjeta, saldoPuntos, fechaEmision, estado);
        this.clienteDTO = clienteDTO;
        this.nivel = nivel;
    }

    // Getters y Setters
    public String getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(String idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public Double getSaldoPuntos() {
        return saldoPuntos;
    }

    public void setSaldoPuntos(Double saldoPuntos) {
        this.saldoPuntos = saldoPuntos;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

    public void setClienteDTO(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "TarjetaDTO{" +
	    "idTarjeta='" + idTarjeta + '\'' +
	    ", saldoPuntos=" + saldoPuntos +
	    ", fechaEmision=" + fechaEmision +
	    ", estado=" + estado +
	    '}';
    }
}
