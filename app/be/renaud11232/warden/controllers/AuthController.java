package be.renaud11232.warden.controllers;

import be.renaud11232.warden.actions.Authed;
import be.renaud11232.warden.jwt.JWTManager;
import be.renaud11232.warden.models.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
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

    public Result getToken(Http.Request request) {
        JsonNode data = request.body().asJson();
        if(data != null && data.hasNonNull("username") && data.hasNonNull("password")) {
            User user = userRepository.getByUsername(data.get("username").asText());
            if(user != null && passwordEncoder.matches(data.get("password").asText(), user.getPassword())) {
                return ok(getJsonTokenFor(user));
            }
        }
        return unauthorized();
    }

    @Authed
    public Result whoAmI(Http.Request request) {
        return ok(Json.toJson(jwtManager.decode(request)));
    }

    @Authed
    public Result renewToken(Http.Request request) {
        User user = jwtManager.decode(request);
        return ok(getJsonTokenFor(user));
    }

    private ObjectNode getJsonTokenFor(User user) {
        ObjectNode result = Json.newObject();
        result.put("token", jwtManager.generate(user));
        return result;
    }

}
