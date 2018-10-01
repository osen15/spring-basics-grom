package com.lesson3.DAO;

import com.lesson3.models.File;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class FileDAO extends GeneralDAO<File> {

    private String GET_ALL_FILES = "from File where STORAGE_ID = :storageId";


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

    public ArrayList<File> getAll(long storageId) {


        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(GET_ALL_FILES);
            query.setParameter("storageId", storageId);
            return (ArrayList<File>) query.getResultList();

        } catch (HibernateException e) {
            System.err.println("find by id is failed");
            e.printStackTrace();
        }


        return null;
    }

}
