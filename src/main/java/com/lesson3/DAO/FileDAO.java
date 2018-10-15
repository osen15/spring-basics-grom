package com.lesson3.DAO;

import com.lesson3.models.File;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class FileDAO extends GeneralDAO<File> {
    @Autowired
    StorageDAO storageDAO;


    private String FREE_SPACE_IN_STORAGE = "select (s.storageSize -sum(f.size)) from File f" +
            " inner join Storage s on s.id = STORAGE_ID " +
            "where STORAGE_ID =:id group by s.storageSize";

    private String SIZE_ALL_FILES_IN_STORAGE = "select sum(f.size) from File f" +
            " inner join Storage s on s.id = STORAGE_ID " +
            "where STORAGE_ID =:storageId group by s.storageSize";

    private String TRANSFER_ALL = "UPDATE" +
            "(SELECT STORAGE_ID as NEW" +
            " FROM FILE1" +
            " INNER JOIN STORAGE" +
            " ON FILE1.STORAGE_ID = STORAGE.ID" +
            " WHERE FILE1.STORAGE_ID =:storageIdFrom" +
            ") t SET t.NEW =:storageIdTo";


    private String TRANSFER_FILE = "update" +
            "(select FILE1.STORAGE_ID as NEW from File " +
            "inner join Storage on FILE1.STORAGE_ID = STORAGE.ID " +
            "where FILE1.ID =:fileId) f set f.NEW =:storageIdTo";


    private String FORMATS = "SELECT FORMAT FROM FILE1 " +
            "WHERE STORAGE_ID =:storageIdFrom GROUP BY FORMAT";

    public void saveFile(File file) {
        save(file);
    }

    public void deleteFile(long id) {
        delete("File", id);
    }


    public void updateFile(File file) {
        update(file);
    }

    public File findFileById(long id) {
        return findById("File", id);
    }

    public void transferFile(long fileId, long storageIdTo) {
        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {
            tr = session.beginTransaction();
            Query query = session.createNativeQuery(TRANSFER_FILE);
            query.setParameter("sfileId", fileId);
            query.setParameter("storageIdTo", storageIdTo);
            session.update(query);
            tr.commit();

        } catch (HibernateException e) {
            System.err.println("update is failed");
            e.printStackTrace();
            if (tr != null) tr.rollback();

        }
        System.out.println("update is done!");
    }

    public void transferAll(long storageIdTo, long storageIdFrom) {
        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {
            tr = session.beginTransaction();
            NativeQuery nativeQuery = session.createNativeQuery(TRANSFER_ALL);
            nativeQuery.setParameter("storageIdTo", storageIdTo);
            nativeQuery.setParameter("storageIdFrom", storageIdFrom);

            tr.commit();

        } catch (HibernateException e) {
            System.err.println("update is failed");
            e.printStackTrace();
            if (tr != null) tr.rollback();

        }
        System.out.println("update is done!");
    }

    public long freeSpace(long id) {


        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(FREE_SPACE_IN_STORAGE);
            query.setParameter("id", id);
            List<Long> result = query.getResultList();
            if (result.isEmpty())
                return storageDAO.findStorageById(id).getStorageSize();
            return result.get(0);

        } catch (HibernateException e) {
            System.err.println("find by id is failed");
            e.printStackTrace();
        }


        return -1;
    }


    public long sizeAll(long storageId) {


        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(SIZE_ALL_FILES_IN_STORAGE);
            query.setParameter("storageId", storageId);
            List<Long> result = query.list();
            if (result.isEmpty())
                return 0;
            return result.get(0);


        } catch (
                HibernateException e)

        {
            System.err.println("find by id is failed");
            e.printStackTrace();
        }


        return -1;
    }


    public String allFormats(long storageIdFrom) {


        try (Session session = createSessionFactory().openSession()) {
            NativeQuery nativeQuery = session.createNativeQuery(FORMATS);
            nativeQuery.setParameter("storageIdFrom", storageIdFrom);
            String result = nativeQuery.list().toString();
            if (result.isEmpty())
                return null;
            return result;


        } catch (
                HibernateException e)

        {
            System.err.println("find by id is failed");
            e.printStackTrace();
        }


        return null;
    }

}
