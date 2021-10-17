package repositories;

import com.google.inject.ImplementedBy;
import models.User;

@ImplementedBy(UserRepositoryImpl.class)
public interface UserRepository {

    User getByUsername(String username);

    User getByUuid(String uuid);

    boolean hasUser();

}
