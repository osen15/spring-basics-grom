package com.lesson3.service;

import com.lesson3.DAO.StorageDAO;
import com.lesson3.models.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    @Autowired
    StorageDAO storageDAO;

    public Storage findById(Long id) {
       return storageDAO.findById(id);
    }

    public void save(Storage storage) {
        storageDAO.save(storage);
    }

    public void delete(Long id) throws Exception {
        storageDAO.delete(id);
    }

    public void update(Storage storage) throws Exception {
        storageDAO.update(storage);
    }


}
