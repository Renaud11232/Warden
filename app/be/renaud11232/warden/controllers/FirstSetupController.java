package be.renaud11232.warden.controllers;

import be.renaud11232.warden.controllers.auth.routes;
import be.renaud11232.warden.forms.Setup;
import be.renaud11232.warden.models.Role;
import be.renaud11232.warden.models.User;
import be.renaud11232.warden.repositories.UserRepository;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.UUID;

public class FirstSetupController extends Controller {

    private final Form<Setup> setupForm;

    @Inject
    private UserRepository userRepository;

    @Inject
    private Argon2PasswordEncoder passwordEncoder;

    @Inject
    private MessagesApi messagesApi;

    @Inject
    public FirstSetupController(FormFactory formFactory) {
        this.setupForm = formFactory.form(Setup.class);
    }

    public Result show(Http.Request req) {
        return ok(views.html.pages.setup.render(req, setupForm, messagesApi.preferred(req)));
    }

    public Result createUser(Http.Request request) {
        Form<Setup> boundSetupForm = setupForm.bindFromRequest(request).withDirectFieldAccess(true);
        if(boundSetupForm.hasErrors() || boundSetupForm.hasGlobalErrors()) {
            return badRequest(views.html.pages.setup.render(request, boundSetupForm, messagesApi.preferred(request)));
        } else {
            Setup setup = boundSetupForm.get();
            String hashedPassword = passwordEncoder.encode(setup.getPassword());
            String uuid = UUID.randomUUID().toString();
            User user = new User();
            user.setPassword(hashedPassword);
            user.setUuid(uuid);
            user.setUsername(setup.getUsername());
            user.setRole(Role.SUPERADMIN);
            userRepository.create(user);
            return redirect(routes.LoginController.show());
        }
    }

}
