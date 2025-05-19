package gd.tp.recuperacion.dto;


import lombok.Data;

@Data
public class VerificarCodigoRequest {
    private String email;
    private String code;
}
