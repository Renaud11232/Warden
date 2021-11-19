package be.renaud11232.warden.actions;

import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import be.renaud11232.warden.repositories.UserRepository;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class LoggedInAction extends Action<LoggedIn> {

    @Inject
    private UserRepository repository;

    @Override
    public CompletionStage<Result> call(Http.Request req) {
        /*if(req.session().get("user").isEmpty()) {
            return CompletableFuture.supplyAsync(() -> redirect(routes.LoginController.show()));
        } else if(repository.getByUuid(req.session().get("user").get()) == null) {
            return CompletableFuture.supplyAsync(() -> redirect(routes.LoginController.show()).withNewSession());
        }*/
        return delegate.call(req);
    }

}
