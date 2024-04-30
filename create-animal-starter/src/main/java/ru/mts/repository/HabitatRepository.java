package ru.mts.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mts.entity.Habitat;

@Repository
public class HabitatRepository extends AbstractHibernateRepository<Habitat> {
    @Autowired
    public HabitatRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
        setClazz(Habitat.class);
    }
}
