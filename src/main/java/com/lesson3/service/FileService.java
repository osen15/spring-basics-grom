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
            throw  new Exception("Not enouth space in a storage");
            file.setStorage(storageDAO.findById(storageId));
            fileDAO.save(file);

    }


    public File findById(Long id) {
        return fileDAO.findById(id);


    }


    public void update(File file) {
        fileDAO.update(file);
    }


    public void delete(Long id) {
        fileDAO.delete(id);
    }


    private Long freeSpace(long storageId) {
        long sizeFiles = 0;
        Long storageSize = storageDAO.findById(storageId).getStorageSize();
        ArrayList<File> files = fileDAO.getAll(storageId);
        if (files != null) {
            for (File file : fileDAO.getAll(storageId)) {
                sizeFiles = sizeFiles + file.getSize();
            }
            return storageSize - sizeFiles;
        } else return storageSize;

    }

    private void checkFormat(File file) throws Exception{
        if (!file.getFormat().equals("txt") || !file.getFormat().equals("jpg"))
            throw  new Exception("wrong format");
    }

}

