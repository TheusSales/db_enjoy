package com.enjoy.Entities;

import javax.persistence.EntityManager;

import com.enjoy.Configs.DbConn;

public abstract class EntityDefault {
    
    // public <T extends EntityDefault> EntityDefault find(Object pk) {    
    //     return DbConn.getInstance().getEntityManager().find(this.getClass(), pk);
    // }

    public static EntityDefault find(Class<? extends EntityDefault> entityClass, Object pk) {
        System.out.println(entityClass);
        EntityDefault ret = DbConn.getInstance().getEntityManager().find(entityClass, pk);

        return ret;
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
