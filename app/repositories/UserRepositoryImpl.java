package repositories;

import models.User;

public class UserRepositoryImpl extends Repository implements UserRepository {

    @Override
    public User getByUserName(String username) {
        return jpa().withTransaction(em -> (User) em.createQuery("select u from User u where u.username = :username").setParameter("username", username).getResultList().stream().findFirst().orElse(null));
    }
}
