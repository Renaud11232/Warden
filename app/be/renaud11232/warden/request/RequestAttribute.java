package be.renaud11232.warden.request;

import be.renaud11232.warden.models.User;
import play.libs.typedmap.TypedKey;

public class RequestAttribute {
    public static final TypedKey<User> USER = TypedKey.create("user");
}
