package com.lesson3.DAO;

import com.lesson3.models.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StorageDAO {
    @Autowired
    private GeneralDAO<Storage> generalDAO;

    public void save(Storage storage) {
        generalDAO.save(storage);
    }

    public void delete(Long id) {
        generalDAO.delete("Storage", id);
    }


    public void update(Storage storage) {
        generalDAO.update(storage);
    }

    public Storage findById(Long id) {
       return generalDAO.findById("Storage", id);
    }







}
