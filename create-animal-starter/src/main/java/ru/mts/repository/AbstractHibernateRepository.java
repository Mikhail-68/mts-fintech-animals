package ru.mts.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.postgresql.shaded.com.ongres.scram.common.util.Preconditions;

import java.util.List;

public abstract class AbstractHibernateRepository<T> {

    protected final SessionFactory sessionFactory;

    private Class<T> clazz;

    protected AbstractHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public final void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet, null);
    }

    public T findOne(final long id) {
        return getCurrentSession().get(clazz, id);
    }

    public List<T> findAll() {
        Session session = getCurrentSession();
        session.beginTransaction();
        List<T> list = session.createQuery("from " + clazz.getName(), clazz).list();
        session.close();
        return list;
    }

    public void create(final T entity) {
        Preconditions.checkNotNull(entity, null);
        Transaction transaction = getCurrentSession().beginTransaction();
        getCurrentSession().saveOrUpdate(entity);
        transaction.commit();
    }

    public T update(final T entity) {
        Preconditions.checkNotNull(entity, null);
        Session currentSession = getCurrentSession();
        currentSession.beginTransaction();
        T merge = currentSession.merge(entity);
        currentSession.close();
        return merge;
    }

    public void delete(final T entity) {
        Preconditions.checkNotNull(entity, null);
        getCurrentSession().remove(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkNotNull(entity, null);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
