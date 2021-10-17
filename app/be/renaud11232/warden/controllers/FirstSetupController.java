package be.renaud11232.warden.controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

public class FirstSetupController extends Controller {

    public Result show(Http.Request req) {
        return ok(views.html.pages.setup.render(req));
    }

}
