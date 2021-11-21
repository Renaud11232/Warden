package be.renaud11232.warden.jwt;

import be.renaud11232.warden.models.User;
import be.renaud11232.warden.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.typesafe.config.Config;
import play.mvc.Http;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

public class JWTManagerImpl implements JWTManager {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final UserRepository userRepository;

    @Inject
    public JWTManagerImpl(Config config, UserRepository userRepository) throws UnsupportedEncodingException {
        this.userRepository = userRepository;
        algorithm = Algorithm.HMAC256(config.getString("play.http.secret.key"));
        verifier = JWT.require(algorithm)
                .withIssuer("Warden")
                .build();
    }

    @Override
    public String generate(User user) {
        return JWT.create()
                .withIssuer("Warden")
                .withClaim("user", user.getUuid())
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(ZonedDateTime.now(ZoneId.systemDefault()).plusMinutes(15).toInstant()))
                .sign(algorithm);
    }

    @Override
    public User decode(Http.Request request) {
        Optional<String> authorizationHeader = request.header(AUTHORIZATION);
        if(authorizationHeader.filter(header -> header.startsWith(BEARER)).isEmpty()) {
            return null;
        }
        String token = authorizationHeader.map(header -> header.replace(BEARER, "")).orElse("");
        try {
            DecodedJWT jwt = verifier.verify(token);
            String uuid = jwt.getClaim("user").asString();
            return userRepository.getByUuid(uuid);
        } catch (JWTVerificationException ignored) {
            return null;
        }
    }
}
