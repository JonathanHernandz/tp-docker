package gd.tp.recuperacion.dto;

import lombok.Data;

@Data
public class CambiarPasswordRequest {

    private String email;
    private String nuevaPassword;
}
