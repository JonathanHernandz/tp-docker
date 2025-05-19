package gd.tp.cliente.dto;

import lombok.Data;

@Data
public class ClienteDTO {

    private Long idCliente;
    private String nombre;
    private String email;
    private String numero;
    private String thumbnail;

}
