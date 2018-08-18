package com.lesson2.ItemDAOWithSpring.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.lesson2.ItemDAOWithSpring.entity.Item;
import com.lesson2.ItemDAOWithSpring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;


@Controller
public class ItemController {

    @Autowired
    ItemService itemService;

    @RequestMapping(method = RequestMethod.GET, value = "/item")
    @ResponseBody
    String getItem(@RequestParam("id") String ID) {

        try {

            return (itemService.findItem(Long.parseLong(ID)).toString());
        } catch (NoResultException e) {
            e.printStackTrace();
            return ("Item with id: " + ID + " not found");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Bad request";
        }


    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveItem", produces = "text/plain")
    @ResponseBody
    String doPost(HttpServletRequest req) {


        try {
            Item item = jsonToEntity(req);
            itemService.saveItem(item);
            return ("Item with id: " + item.getId() + " is saved");
        } catch (Exception e) {
            e.printStackTrace();
            return ("Error saving " + e.getMessage());

        }

    }


    @RequestMapping(method = RequestMethod.PUT, value = "/updateItem", produces = "text/plain")
    @ResponseBody
    String doPut(HttpServletRequest req) {

        try {
            Item item = jsonToEntity(req);
            item.setLastUpdatedDate(new Date());
            itemService.updateItem(jsonToEntity(req));
            return ("Item with id: " + item.getId() + " is updated");

        } catch (Exception e) {
            e.printStackTrace();
            return ("Error updating " + e.getMessage());

        }
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteItem", produces = "text/plain")
    @ResponseBody
    String doDelete(@RequestParam("id") String ID) throws Exception {

        try {

            itemService.deleteItem(Long.parseLong(ID));
            return ("item with id: " + ID + " deleted");
        } catch (NoResultException e) {
            e.printStackTrace();
            return ("Item with id: " + ID + " not found");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Bad request";
        }


    }

    private Item jsonToEntity(HttpServletRequest req) {

        StringBuilder stb = new StringBuilder();
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yy").create();
        String line;
        try (BufferedReader reader = req.getReader()) {


            while ((line = reader.readLine()) != null)
                stb.append(line);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return gson.fromJson(stb.toString(), Item.class);
    }
}