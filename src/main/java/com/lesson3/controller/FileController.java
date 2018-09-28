package com.lesson3.controller;

import com.google.gson.Gson;
import com.lesson3.models.File;
import com.lesson3.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;


@Controller
public class FileController {
    @Autowired
    FileService fileService;


    @RequestMapping(method = RequestMethod.GET, value = "/file")
    @ResponseBody
    String get(@RequestParam("id") String ID) {

        try {

            return fileService.findById(Long.parseLong(ID)).toString();
        } catch (NoResultException e) {
            e.printStackTrace();
            return ("File with id: " + ID + " not found");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Bad request";
        }


    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveFile")
    @ResponseBody
    String doPost(HttpServletRequest request, @RequestParam ("id") String id) {


        try {
            File file = jsonToEntity(request);

            fileService.save(file, Long.parseLong(id));
            return ("File with id: " + file.getId() + " is saved");
        } catch (Exception e) {
            e.printStackTrace();
            return ("Error saving " + e.getMessage());

        }

    }


    @RequestMapping(method = RequestMethod.PUT, value = "/updateFile")
    @ResponseBody
    String doPut(HttpServletRequest req) {

        try {
            File file = jsonToEntity(req);

            fileService.update(file);
            return ("File with id: " + file.getId() + " is updated");

        } catch (Exception e) {
            e.printStackTrace();
            return ("Error updating " + e.getMessage());

        }
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteFile")
    @ResponseBody
    String doDelete(@RequestParam("id") String ID) throws Exception {

        try {

            fileService.delete(Long.parseLong(ID));
            return ("file with id: " + ID + " deleted");
        } catch (NoResultException e) {
            e.printStackTrace();
            return ("file with id: " + ID + " not found");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Bad request";
        }


    }

    private File jsonToEntity(HttpServletRequest req) {

        StringBuilder stb = new StringBuilder();
        Gson gson = new Gson();
        String line;
        try (BufferedReader reader = req.getReader()) {


            while ((line = reader.readLine()) != null)
                stb.append(line);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return gson.fromJson(stb.toString(), File.class);
    }
}













