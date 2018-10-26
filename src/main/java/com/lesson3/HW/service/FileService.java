package com.lesson3.HW.service;

import com.lesson3.HW.DAO.FileDAO;
import com.lesson3.HW.DAO.StorageDAO;
import com.lesson3.HW.models.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class FileService {
    @Autowired
    private File file;
    @Autowired
    private FileDAO fileDAO;
    @Autowired
    private StorageDAO storageDAO;

    public void save(File file, long storageId) throws Exception {
        validation(file, storageDAO.findStorageById(storageId).getStringFormat().split(","));
        if (fileDAO.freeSpace(storageId) < file.getSize())
            throw new Exception("Not enouth space in a storage");
        file.setStorage(storageDAO.findStorageById(storageId));
        fileDAO.saveFile(file);
    }

    public File findById(long id) {
        return fileDAO.findFileById(id);
    }

    public void update(File file) {
        fileDAO.updateFile(file);
    }

    public void delete(Long id) {
        fileDAO.deleteFile(id);
    }

    public void transferFile(long fileId, long storageIdTo) throws Exception {
        file = findById(fileId);
        validation(file, storageDAO.findStorageById(storageIdTo).getStringFormat().split(","));
        if (fileDAO.freeSpace(storageIdTo) <= file.getSize())
            throw new Exception("Not enouth space in a storageId: " + storageIdTo);
        fileDAO.transferFile(fileId, storageIdTo);
    }

    public void transferAll(long storageIdFrom, long storageIdTo) throws Exception {

        checkFormat(storageIdFrom, storageIdTo);
        if (fileDAO.sizeAll(storageIdFrom) > fileDAO.freeSpace(storageIdTo))
            throw new Exception("Not enouth space in a storageId: " + storageIdTo);
        fileDAO.transferAll(storageIdFrom, storageIdTo);
    }

    private void checkFormat(long storageIdFrom, long storageIdTo) throws Exception {
        for (String fileFormatFrom : fileDAO.allFormats(storageIdFrom).split(",")) {
            for (String storageFormat : storageDAO.findStorageById(storageIdTo).toString().split(",")) {
                if (!fileFormatFrom.equals(storageFormat))
                    throw new Exception("wrong format");
            }
        }


    }

    private void validation(File file, String[] formatsSupported) throws Exception {
        if (file.getName().length() < 10)
            throw new Exception("to large file name");
        if (!Arrays.asList(formatsSupported).contains(file.getFormat()))
            throw new Exception("wrong format");
    }
}










