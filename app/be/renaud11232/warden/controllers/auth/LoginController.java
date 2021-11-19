package be.renaud11232.warden.controllers.auth;

import be.renaud11232.warden.actions.LoggedIn;
import be.renaud11232.warden.actions.NotLoggedIn;
import be.renaud11232.warden.forms.Login;
import be.renaud11232.warden.models.User;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import be.renaud11232.warden.repositories.UserRepository;

import javax.inject.Inject;

public class LoginController extends Controller {

    private final UserRepository userRepository;
    private final Argon2PasswordEncoder passwordEncoder;
    private final Form<Login> loginForm;
    private final MessagesApi messagesApi;

    @Inject
    public LoginController(UserRepository userRepository, Argon2PasswordEncoder argon2PasswordEncoder, FormFactory formFactory, MessagesApi messagesApi) {
        this.userRepository = userRepository;
        this.passwordEncoder = argon2PasswordEncoder;
        this.loginForm = formFactory.form(Login.class);
        this.messagesApi = messagesApi;
    }

    @NotLoggedIn
    public Result login(Http.Request request) {
        Form<Login> boundLoginForm = loginForm.bindFromRequest(request);
        if(!boundLoginForm.hasErrors() && !boundLoginForm.hasGlobalErrors()) {
            Login login = boundLoginForm.get();
            User user = userRepository.getByUsername(login.getUsername());
            if(user != null && ((user.getPassword() == null && login.getPassword().isEmpty()) || (user.getPassword() != null && passwordEncoder.matches(login.getPassword(), user.getPassword())))) {
                //return redirect(be.renaud11232.warden.controllers.routes.DashboardController.show()).withNewSession().addingToSession(request, "user", user.getUuid());
            } else {
                //return unauthorized(views.html.pages.auth.login.render(request, boundLoginForm.fill(login).withGlobalError("The username and password don't match"), messagesApi.preferred(request)));
            }
        }
        return ok();
        //return unauthorized(views.html.pages.auth.login.render(request, boundLoginForm, messagesApi.preferred(request)));
    }

    @LoggedIn
    public Result logout() {
        //return redirect(be.renaud11232.warden.controllers.routes.DashboardController.show()).withNewSession();
        return ok();
    }

}
