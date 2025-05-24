package gd.tp.recuperacion.dto;

public class CambiarPasswordRequest {

    private String email;
    private String nuevaPassword;

    public CambiarPasswordRequest() {
    }

    public CambiarPasswordRequest(String email, String nuevaPassword) {
        this.email = email;
        this.nuevaPassword = nuevaPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNuevaPassword() {
        return nuevaPassword;
    }

    public void setNuevaPassword(String nuevaPassword) {
        this.nuevaPassword = nuevaPassword;
    }

    @Override
    public String toString() {
        return "CambiarPasswordRequest{" +
	    "email='" + email + '\'' +
	    ", nuevaPassword='[PROTECTED]'" +
	    '}';
    }
}
