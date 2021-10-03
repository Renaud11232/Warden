package repositories;

import play.db.jpa.JPAApi;

import javax.inject.Inject;

public abstract class Repository {

    @Inject
    private JPAApi jpa;

    public JPAApi jpa() {
        return jpa;
    }

}
