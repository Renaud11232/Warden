package controllers;

import actions.LoggedIn;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

@LoggedIn
public class DashboardController extends Controller {

    public Result show(Http.Request request) {
        return ok(views.html.pages.dashboard.render(request));
    }

}
