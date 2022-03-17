package com.enjoy.Configs;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DbConn {
    private static DbConn instance = null;
    private EntityManager em = null;

    private DbConn() {
        em = Persistence.createEntityManagerFactory("enjoy").createEntityManager();
    }

    public static DbConn getInstance() {
        if (instance == null)
            instance = new DbConn();
        return instance;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void close() {
        em.close();
    }

}
