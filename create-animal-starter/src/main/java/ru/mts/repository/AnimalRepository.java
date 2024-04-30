package ru.mts.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.mts.entity.Animal;

@Repository
public class AnimalRepository extends AbstractHibernateRepository<Animal> {

    private final SessionFactory sessionFactory;

    public AnimalRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
        setClazz(Animal.class);
    }

}
