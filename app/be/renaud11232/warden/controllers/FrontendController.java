package be.renaud11232.warden.controllers;

import controllers.Assets;
import play.api.mvc.Action;
import play.api.mvc.AnyContent;
import play.mvc.Controller;

import javax.inject.Inject;

public class FrontendController extends Controller {

    @Inject
    private Assets assets;

    public Action<AnyContent> index() {
        return assets.at("index.html");
    }

    public Action<AnyContent> assetOrDefault(String resource) {
        String[] resourcePath = resource.split("/");
        if(resourcePath[resourcePath.length - 1].contains(".")) {
            return assets.at(resource);
        } else {
            return index();
        }
    }

}
