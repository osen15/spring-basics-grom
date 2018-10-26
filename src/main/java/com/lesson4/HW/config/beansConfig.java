package com.lesson4.HW.config;


import com.lesson4.HW.DAO.FileDAO;
import com.lesson4.HW.DAO.StorageDAO;
import com.lesson4.HW.models.File;
import com.lesson4.HW.models.Storage;
import com.lesson4.HW.service.FileService;
import com.lesson4.HW.service.StorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class beansConfig {
    @Bean
    public File file() {
        return new File();
    }

    @Bean
    public Storage storage() {
        return new Storage();
    }

    @Bean
    public FileDAO fileDAO() {
        return new FileDAO();
    }

    @Bean
    public StorageDAO storageDAO() {
        return new StorageDAO();
    }

    @Bean
    public FileService fileService() {
        return new FileService();
    }

    @Bean
    public StorageService storageService() {
        return new StorageService();
    }


}
