package com.lesson3.DAO;

import com.lesson3.models.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class FileDAO {



    @Autowired
    private GeneralDAO<File> generalDAO;

    public void save(File file) {
        generalDAO.save(file);
    }

    public void delete(long id) {
        generalDAO.delete("File", id);
    }


    public void update(File file) {
        generalDAO.update(file);
    }

    public File findById(long id) {
        return generalDAO.findById("File", id);
    }

    public ArrayList<File> getAll(long id) {
        return generalDAO.getAllFilesInStorage("File", id);
    }


}
