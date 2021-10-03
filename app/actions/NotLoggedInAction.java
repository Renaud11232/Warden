package actions;

import controllers.routes;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class NotLoggedInAction extends Action<NotLoggedIn> {

    @Override
    public CompletionStage<Result> call(Http.Request req) {
        if(req.session().get("username").isPresent()) {
            return CompletableFuture.completedFuture(redirect(routes.DashboardController.show()));
        }
        return delegate.call(req);
    }

}
