package com.lesson5.itemApp;

import com.lesson5.itemApp.DAO;
import com.lesson5.itemApp.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceItem {

    private final DAO dao;

    @Autowired
    public ServiceItem(DAO dao) {
        this.dao = dao;
    }

    public Item save(Item item) {
        try {
            return dao.save(item);
        } catch (Exception e) {
            System.out.println("Item with id: " + item.getId() + "is not saved");
            e.printStackTrace();
            return null;
        }
    }

    public Item update(Item item) {
        try {
            return dao.update(item);
        } catch (Exception e) {
            System.out.println("Item with id: " + item.getId() + "is not updated");
            e.printStackTrace();
            return null;
        }
    }

    public void delete(long id) {
        try {
            dao.delete(id);
        } catch (Exception e) {
            System.out.println("Item with id: " + id + "is not deleted");
            e.printStackTrace();
        }
    }


}
