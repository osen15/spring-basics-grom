package com.lesson3.service;

import com.lesson3.DAO.FileDAO;
import com.lesson3.DAO.StorageDAO;
import com.lesson3.models.File;
import com.lesson3.models.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class FileService {

    @Autowired
    Storage storage;
    @Autowired
    FileDAO fileDAO;
    @Autowired
    StorageDAO storageDAO;

    public void save(File file, long storageId) throws Exception {
        checkFormat(file);
        if (freeSpace(storageId) < file.getSize())
            throw new Exception("Not enouth space in a storage");
        file.setStorage(storageDAO.findStorageById(storageId));
        fileDAO.save(file);
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

    public void transferFile(File file, long storageId) throws Exception {
        checkFormat(file);
        if (freeSpace(storageId) >= file.getSize()) {
            file.setStorage(storageDAO.findStorageById(storageId));
            update(file);
        }
    }

    public void transferAll(long storageIdFrom, long storageIdTo) throws Exception {
        ArrayList<File> filesFrom = fileDAO.getAll(storageDAO.findStorageById(storageIdFrom).getId());
        checkAllFormat(filesFrom);
        if (freeSpace(storageIdFrom) > sizeAll(filesFrom)) {
            for (File file : filesFrom) {
                file.setStorage(storageDAO.findStorageById(storageIdTo));
                update(file);
            }
        }

    }


    private long freeSpace(long storageId) {
        long sizeFiles = 0;
        long storageSize = storageDAO.findStorageById(storageId).getStorageSize();
        ArrayList<File> files = fileDAO.getAll(storageId);
        if (files != null) {
            for (File file : fileDAO.getAll(storageId)) {
                sizeFiles = sizeFiles + file.getSize();
            }
            return storageSize - sizeFiles;
        } else return storageSize;

    }

    private void checkFormat(File file) throws Exception {
        if (!file.getFormat().equals("txt") || !file.getFormat().equals("jpg"))
            throw new Exception("wrong format");
    }

    private long sizeAll(ArrayList<File> files) throws NullPointerException {
        long res = 0;
        for (File file : files) {

            res = res + file.getSize();
        }
        return res;
    }


    private void checkAllFormat(ArrayList<File> files) throws Exception {
        for (File file : files) {
            checkFormat(file);
        }


    }


}

