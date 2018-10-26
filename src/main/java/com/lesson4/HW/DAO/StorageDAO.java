package com.lesson4.HW.DAO;

import com.lesson4.HW.models.Storage;
import org.springframework.stereotype.Repository;

@Repository
public class StorageDAO extends GeneralDAO<Storage> {
    public void saveStorage(Storage storage) {
        save(storage);
    }

    public void deleteStorage(long id) {
        delete("Storage", id);
    }

    public void updateStorage(Storage storage) {
        update(storage);
    }

    public Storage findStorageById(long id) {
        return findById("Storage", id);
    }


}
