package be.renaud11232.warden.controllers;

import be.renaud11232.warden.actions.LoggedIn;
import play.mvc.Http;
import play.mvc.Result;

@LoggedIn
public class DashboardController extends AuthedController {

    public Result show(Http.Request request) {
        return ok(views.html.pages.dashboard.render(request, getUser(request)));
    }

}
