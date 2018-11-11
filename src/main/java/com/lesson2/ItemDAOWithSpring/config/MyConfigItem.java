package com.lesson2.ItemDAOWithSpring.config;

import com.lesson2.ItemDAOWithSpring.dao.ItemDAO;
import com.lesson2.ItemDAOWithSpring.entity.Item;
import com.lesson2.ItemDAOWithSpring.service.ItemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigItem {

    @Bean
    public Item item() {
        return new Item();
    }

    @Bean
    public ItemService itemService() {
        return new ItemService();
    }

    @Bean
    public ItemDAO itemDAO() {
        return new ItemDAO();
    }
}
