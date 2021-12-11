package be.renaud11232.warden.controllers;

import be.renaud11232.warden.actions.Authed;
import be.renaud11232.warden.dto.Token;
import be.renaud11232.warden.forms.Login;
import be.renaud11232.warden.jwt.JWTManager;
import be.renaud11232.warden.models.User;
import be.renaud11232.warden.request.RequestAttribute;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import be.renaud11232.warden.repositories.UserRepository;

import javax.inject.Inject;

public class AuthController extends Controller {

    @Inject
    private UserRepository userRepository;

    @Inject
    private Argon2PasswordEncoder passwordEncoder;

    @Inject
    private JWTManager jwtManager;

    private final Form<Login> loginForm;

    @Inject
    public AuthController(FormFactory formFactory) {
        this.loginForm = formFactory.form(Login.class);
    }

    public Result login(Http.Request request) {
        Form<Login> boundLoginForm = loginForm.bindFromRequest(request);
        if(boundLoginForm.hasErrors()) {
            return badRequest(boundLoginForm.errorsAsJson());
        }
        Login login = boundLoginForm.get();
        User user = userRepository.getByUsername(login.getUsername());
        if(user != null && passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            Token token = new Token(jwtManager.generate(user));
            return ok(Json.toJson(token));
        }
        return unauthorized(boundLoginForm.withGlobalError("The username and password don't match.").errorsAsJson());
    }

    @Authed
    public Result whoAmI(Http.Request request) {
        return ok(Json.toJson(request.attrs().get(RequestAttribute.USER)));
    }

    @Authed
    public Result renewToken(Http.Request request) {
        User user = request.attrs().get(RequestAttribute.USER);
        Token token = new Token(jwtManager.generate(user));
        return ok(Json.toJson(token));
    }

}
