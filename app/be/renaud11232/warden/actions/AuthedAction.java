package be.renaud11232.warden.actions;

import be.renaud11232.warden.jwt.JWTManager;
import be.renaud11232.warden.models.User;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class AuthedAction extends Action<Authed> {

    @Inject
    private JWTManager jwtManager;

    @Override
    public CompletionStage<Result> call(Http.Request req) {
        User authedUser = jwtManager.decode(req);
        if(authedUser == null) {
            return CompletableFuture.supplyAsync(Results::forbidden);
        }
        return delegate.call(req);
    }

}
