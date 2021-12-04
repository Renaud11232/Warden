package be.renaud11232.warden.forms;

import play.data.validation.Constraints;


public class Login {

    @Constraints.Required
    private String username;

    @Constraints.Required
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
