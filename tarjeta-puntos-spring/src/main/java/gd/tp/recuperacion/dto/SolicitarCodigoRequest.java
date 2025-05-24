package gd.tp.recuperacion.dto;

public class SolicitarCodigoRequest {

    private String email;

    public SolicitarCodigoRequest() {
    }

    public SolicitarCodigoRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SolicitarCodigoRequest{" +
	    "email='" + email + '\'' +
	    '}';
    }
}
