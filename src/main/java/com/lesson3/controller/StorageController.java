package com.lesson3.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lesson3.models.Storage;
import com.lesson3.service.StorageService;
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

@Controller
public class StorageController {


        @Autowired
        StorageService storageService;

        @RequestMapping(method = RequestMethod.GET, value = "/storage")
        @ResponseBody
        String doGet(@RequestParam("id") String ID) {

            try {

                return (storageService.findById(Long.parseLong(ID)).toString());
            } catch (NoResultException e) {
                e.printStackTrace();
                return ("Storage with id: " + ID + " not found");

            } catch (NumberFormatException e) {
                e.printStackTrace();
                return "Bad request";
            }


        }

        @RequestMapping(method = RequestMethod.POST, value = "/saveStorage")
        @ResponseBody
        String doPost(HttpServletRequest req) {


            try {
                Storage storage = jsonToEntity(req);

                storageService.save(storage);
                return ("Storage with id: " + storage.getId() + " is saved");
            } catch (Exception e) {
                e.printStackTrace();
                return ("Error saving " + e.getMessage());

            }

        }


        @RequestMapping(method = RequestMethod.PUT, value = "/updateStorage")
        @ResponseBody
        String doPut(HttpServletRequest req) {

            try {
                Storage storage = jsonToEntity(req);

                storageService.update(storage);
                return ("Storage with id: " + storage.getId() + " is updated");

            } catch (Exception e) {
                e.printStackTrace();
                return ("Error updating " + e.getMessage());

            }
        }


        @RequestMapping(method = RequestMethod.DELETE, value = "/deleteStorage")
        @ResponseBody
        String doDelete(@RequestParam("id") String ID) throws Exception {

            try {

                storageService.delete(Long.parseLong(ID));
                return ("item with id: " + ID + " deleted");
            } catch (NoResultException e) {
                e.printStackTrace();
                return ("Storage with id: " + ID + " not found");

            } catch (NumberFormatException e) {
                e.printStackTrace();
                return "Bad request";
            }


        }



    private Storage jsonToEntity(HttpServletRequest req) {

        StringBuilder stb = new StringBuilder();
        Gson gson = new GsonBuilder().create();
        String line;
        try (BufferedReader reader = req.getReader()) {


            while ((line = reader.readLine()) != null)
                stb.append(line);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return gson.fromJson(stb.toString(), Storage.class);
    }
}
