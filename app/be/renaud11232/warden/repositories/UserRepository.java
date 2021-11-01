package be.renaud11232.warden.repositories;

import com.google.inject.ImplementedBy;
import be.renaud11232.warden.models.User;

@ImplementedBy(UserRepositoryImpl.class)
public interface UserRepository {

    User getByUsername(String username);

    User getByUuid(String uuid);

    boolean hasUser();

    void create(User user);

}
