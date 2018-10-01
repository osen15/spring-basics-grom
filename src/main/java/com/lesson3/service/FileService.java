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
    private File file;
    @Autowired
    private FileDAO fileDAO;
    @Autowired
    private StorageDAO storageDAO;

    public void save(File file, long storageId) throws Exception {
        checkFormat(file.getFormat());
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

    public void transferFile(long fileId, long storageId) throws Exception {
        file = findById(fileId);
        checkFormat(file.getFormat());
        if (freeSpace(storageId) <= file.getSize())
            throw new Exception("Not enouth space in a storageId: " + storageId);
        file.setStorage(storageDAO.findStorageById(storageId));
        update(file);
    }

    public void transferAll(long storageIdFrom, long storageIdTo) throws Exception {
        ArrayList<File> filesFrom = fileDAO.getAll(storageDAO.findStorageById(storageIdFrom).getId());
        checkAllFormat(filesFrom);
        if (freeSpace(storageIdFrom) < sizeAll(filesFrom))
            throw new Exception("Not enouth space in a storageId: " + storageIdTo);
        for (File file : filesFrom) {
            file.setStorage(storageDAO.findStorageById(storageIdTo));
            update(file);
        }
    }


    public long freeSpace(long storageId) {
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

    private void checkFormat(String format) throws Exception {
        if (!format.equals("txt") && !format.equals("jpg"))
            throw new Exception("wrong format: " + format);


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
            checkFormat(file.getFormat());
        }


    }


}

