package ru.itis.javawarrior.db.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.javawarrior.db.dao.BaseDAO;
import ru.itis.javawarrior.db.model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class BaseDAOImpl implements BaseDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T extends BaseEntity> T findById(Class<T> clazz, Long id) {
        return entityManager.unwrap(Session.class).get(clazz, id);
    }

    @Override
    public <T extends BaseEntity> T findByEmail(Class<T> clazz, String email) {
        return (T) entityManager.unwrap(Session.class).createQuery("SELECT o FROM " + clazz.getName() + " o where o.email = " + "'" + email + "'").uniqueResult();
    }

    @Override
    public Long getCount(Class<?> clazz) {
        return (Long) entityManager.unwrap(Session.class).createQuery("SELECT count(o.id) FROM " + clazz.getName() + " o").uniqueResult();
    }

    @Override
    public void save(BaseEntity o) {
        entityManager.unwrap(Session.class).save(o);
    }

    @Override
    public <T extends BaseEntity> void delete(Class<T> clazz, Long id) {
        T o = findById(clazz, id);
        if (o != null) {
            entityManager.unwrap(Session.class).delete(o);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public <T> List getList(Class<T> clazz) {
        Criteria criteria = getCriteria(clazz);
        if (BaseEntity.class.isAssignableFrom(clazz)) {
            criteria.addOrder(Order.desc("id"));
        }
        return criteria.list();
    }

    private Criteria getCriteria(final Class<?> clazz) {
        return entityManager.unwrap(Session.class).createCriteria(clazz);
    }
}
