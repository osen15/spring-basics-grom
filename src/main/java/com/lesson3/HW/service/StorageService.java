package com.lesson3.HW.service;

import com.lesson3.HW.DAO.StorageDAO;
import com.lesson3.HW.models.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    @Autowired
    StorageDAO storageDAO;

    public Storage findById(long id) {
        return storageDAO.findStorageById(id);
    }

    public void save(Storage storage) {
        storageDAO.saveStorage(storage);
    }

    public void delete(Long id) {
        storageDAO.deleteStorage(id);
    }

    public void update(Storage storage) {
        storageDAO.updateStorage(storage);
    }


}
