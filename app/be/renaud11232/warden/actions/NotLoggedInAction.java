package be.renaud11232.warden.actions;

import be.renaud11232.warden.controllers.routes;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class NotLoggedInAction extends Action<NotLoggedIn> {

    @Override
    public CompletionStage<Result> call(Http.Request req) {
        if(req.session().get("user").isPresent()) {
            return CompletableFuture.supplyAsync(() -> redirect(routes.DashboardController.show()));
        }
        return delegate.call(req);
    }

}
