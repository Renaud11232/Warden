package be.renaud11232.warden.controllers;

import be.renaud11232.warden.actions.LoggedIn;
import be.renaud11232.warden.models.User;
import be.renaud11232.warden.repositories.UserRepository;
import play.mvc.Controller;
import play.mvc.Http;

import javax.inject.Inject;
import java.util.Optional;

@LoggedIn
public abstract class AuthedController extends Controller {

    @Inject
    private UserRepository userRepository;

    protected User getUser(Http.Request req) {
        Optional<String> uuid = req.session().get("user");
        return uuid.map(s -> userRepository.getByUuid(s)).orElse(null);
    }

}
