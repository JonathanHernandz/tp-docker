package gd.tp.recuperacion.dto;


public class VerificarCodigoRequest {
    private String email;
    private String code;

    public VerificarCodigoRequest() {
    }

    public VerificarCodigoRequest(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "VerificarCodigoRequest{" +
	    "email='" + email + '\'' +
	    ", code='[PROTECTED]'" +
	    '}';
    }
}
