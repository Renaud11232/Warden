package be.renaud11232.warden.tasks;

import akka.actor.ActorSystem;
import be.renaud11232.warden.models.Role;
import be.renaud11232.warden.models.User;
import be.renaud11232.warden.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;

import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class FirstSetupTask {

    private final UserRepository userRepository;
    private final Argon2PasswordEncoder argon2PasswordEncoder;
    private final Logger logger = LoggerFactory.getLogger("setup");

    @Inject
    public FirstSetupTask(ActorSystem actorSystem, ExecutionContext executionContext, UserRepository userRepository, Argon2PasswordEncoder argon2PasswordEncoder) {
        this.userRepository = userRepository;
        this.argon2PasswordEncoder = argon2PasswordEncoder;
        actorSystem.scheduler()
                .scheduleOnce(
                        Duration.create(0, TimeUnit.SECONDS),
                        this::createUserIfNeeded,
                        executionContext
                );
    }

    private void createUserIfNeeded() {
        if (!userRepository.hasUser()) {
            String username = "admin";
            String password = "changeme";
            User defaultUser = new User();
            defaultUser.setRole(Role.SUPERADMIN);
            defaultUser.setUsername(username);
            defaultUser.setPassword(argon2PasswordEncoder.encode(password));
            defaultUser.setUuid(UUID.randomUUID().toString());
            userRepository.create(defaultUser);
            logger.info("============================================================================================================");
            logger.info(" \\ \\        / /          | |");
            logger.info("  \\ \\  /\\  / /_ _ _ __ __| | ___ _ __");
            logger.info("   \\ \\/  \\/ / _` | '__/ _` |/ _ \\ '_ \\");
            logger.info("    \\  /\\  / (_| | | | (_| |  __/ | | |");
            logger.info("     \\/  \\/ \\__,_|_|  \\__,_|\\___|_| |_|");
            logger.info("============================================================================================================");
            logger.info("Welcome, the default user has been created. You can now login to the webUI using the following credentials :");
            logger.info("Username : " + username);
            logger.info("Password : " + password);
            logger.info("Once connected, don't forget to change these default credentials");
            logger.info("============================================================================================================");
        }
    }

}
