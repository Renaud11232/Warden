package be.renaud11232.warden.jwt;

import be.renaud11232.warden.models.User;
import com.google.inject.ImplementedBy;
import play.mvc.Http;

@ImplementedBy(JWTManagerImpl.class)
public interface JWTManager {

    String generate(User user);

    User decode(Http.Request request);

}
