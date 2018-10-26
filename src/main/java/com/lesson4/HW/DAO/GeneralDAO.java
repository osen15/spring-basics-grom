package com.lesson4.HW.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;


public class GeneralDAO<T> {
    private SessionFactory sessionFactory;

    public void save(T t) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            session.save(t);
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
            if (tr != null) tr.rollback();
        }
        System.out.println("save is done!");
    }

    public void update(T t) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.beginTransaction();
            session.update(t);
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("update is failed");
            e.printStackTrace();
            if (tr != null) tr.rollback();
        }
        System.out.println("update is done!");
    }

    public void delete(String tableName, long id) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            Query query = session.createQuery("delete from " + tableName + " where id =:id");
            query.setParameter("id", id);
            query.executeUpdate();
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("delete is failed");
            e.printStackTrace();
        }
        System.out.println("delete is done!");
    }

    public T findById(String tableName, long id) {
        T res = null;
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("from " + tableName + " where id = :id");
            query.setParameter("id", id);
            res = (T) query.getSingleResult();
        } catch (HibernateException e) {
            System.err.println("find by id is failed");
            e.printStackTrace();
        }
        return res;
    }

    public ArrayList<T> getAllFilesInStorage(String tableName, long storageId) {
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("from " + tableName + " where STORAGE_ID = :storageId");
            query.setParameter("storageId", storageId);
            return (ArrayList<T>) query.getResultList();
        } catch (HibernateException e) {
            System.err.println("find by id is failed");
            e.printStackTrace();
        }
        return null;
    }

    public SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
