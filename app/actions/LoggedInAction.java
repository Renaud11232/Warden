package actions;

import controllers.auth.routes;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import repositories.UserRepository;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class LoggedInAction extends Action<LoggedIn> {

    @Inject
    private UserRepository repository;

    @Override
    public CompletionStage<Result> call(Http.Request req) {
        if(req.session().get("username").isEmpty()) {
            return CompletableFuture.completedFuture(redirect(routes.LoginController.show()));
        } else if(repository.getByUserName(req.session().get("username").get()) == null) {
            return CompletableFuture.completedFuture(redirect(routes.LoginController.show()).withNewSession());
        }
        return delegate.call(req);
    }

}
