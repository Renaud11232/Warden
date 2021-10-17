package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class FirstSetupController extends Controller {

    public Result show() {
        return ok("setup");
    }

}
