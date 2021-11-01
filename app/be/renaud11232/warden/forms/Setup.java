package be.renaud11232.warden.forms;

import play.data.validation.Constraints;
import play.data.validation.ValidationError;

public class Setup implements Constraints.Validatable<ValidationError> {

    @Constraints.Required
    @Constraints.MaxLength(255)
    private String username;

    @Constraints.Required
    @Constraints.MinLength(8)
    private String password;

    @Constraints.Required
    @Constraints.MinLength(8)
    private String passwordConfirmation;

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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    @Override
    public ValidationError validate() {
        if(!password.equals(passwordConfirmation)) {
            return new ValidationError("passwordConfirmation", "The passwords don't match");
        }
        return null;
    }
}
