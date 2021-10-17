package filters;

import akka.stream.Materializer;
import play.mvc.Filter;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import repositories.UserRepository;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public class FirstSetupFilter extends Filter {

    @Inject
    private UserRepository userRepository;

    @Inject
    public FirstSetupFilter(Materializer mat) {
        super(mat);
    }

    @Override
    public CompletionStage<Result> apply(Function<Http.RequestHeader, CompletionStage<Result>> next, Http.RequestHeader rh) {
        if(userRepository.hasUser() || rh.path().equals("/setup")) {
            return next.apply(rh);
        } else {
            return CompletableFuture.supplyAsync(() -> Results.redirect("/setup"));
        }
    }
}
