package com.enjoy.Entities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.enjoy.Configs.DbConn;

public abstract class EntityDefault {
    
    // public <T extends EntityDefault> EntityDefault find(Object pk) {    
    //     return DbConn.getInstance().getEntityManager().find(this.getClass(), pk);
    // }

    public static EntityDefault find(Class<? extends EntityDefault> entityClass, Object pk) {
        EntityDefault ret = DbConn.getInstance().getEntityManager().find(entityClass, pk);

        return ret;
    }

    public static <T extends EntityDefault> List<T> findWhere(Class<T> entityClass, String querySql) {
        EntityManager em = DbConn.getInstance().getEntityManager();
        var edList = em.createQuery(querySql, entityClass).getResultList();

        if (edList.size() > 0)
            return edList;
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T extends EntityDefault> List<T> findNativeWhere(Class<T> entityClass, String querySql) {
        EntityManager em = DbConn.getInstance().getEntityManager();
        var edList = em.createNativeQuery(querySql, entityClass).getResultList();

        if (edList.size() > 0)
            return edList;
        return null;
    }

    public static <T extends EntityDefault> TypedQuery<T> query(String querySql, Class<T> entityClass) {
        return DbConn.getInstance().getEntityManager().createQuery(querySql, entityClass);
    }

    public static Query queryNative(String querySql) {
        return DbConn.getInstance().getEntityManager().createNativeQuery(querySql);
    }

    public static Query queryNative(String querySql, Class<?> entityClass) {
        return DbConn.getInstance().getEntityManager().createNativeQuery(querySql, entityClass);
    }

    public boolean add(boolean commit) {
        EntityManager em = DbConn.getInstance().getEntityManager();
        try {
            em.persist(this);
            if (commit) {
                em.getTransaction().begin();
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean add() {
        return add(true);
    }

    public boolean update(boolean commit) {
        EntityManager em = DbConn.getInstance().getEntityManager();
        try {
            em.merge(this);
            if (commit) {
                em.getTransaction().begin();
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean update() {
        return update(true);
    }

    public boolean delete(boolean commit) {
        EntityManager em = DbConn.getInstance().getEntityManager();
        try {
            em.remove(this);
            if (commit) {
                em.getTransaction().begin();
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean delete() {
        return delete(true);
    }

}
