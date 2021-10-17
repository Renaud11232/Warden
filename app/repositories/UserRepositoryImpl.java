package repositories;

import models.User;

public class UserRepositoryImpl extends Repository implements UserRepository {

    @Override
    public User getByUsername(String username) {
        return jpa().withTransaction(em -> (User) em.createQuery("select u from User u where u.username = :username").setParameter("username", username).getResultList().stream().findFirst().orElse(null));
    }

    @Override
    public User getByUuid(String uuid) {
        return jpa().withTransaction(em -> (User) em.createQuery("select u from User u where u.uuid = :uuid").setParameter("uuid", uuid).getResultList().stream().findFirst().orElse(null));
    }

    @Override
    public boolean hasUser() {
        return jpa().withTransaction(em -> !em.createQuery("select u from User u").setMaxResults(1).getResultList().isEmpty());
    }
}
