package com.lesson3.controller;

import com.google.gson.Gson;
import com.lesson3.DAO.FileDAO;
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
    @Autowired
    FileDAO fileDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/file")
    @ResponseBody
    String doGet(@RequestParam("id") String id) {

        try {
            return fileService.findById(Long.parseLong(id)).toString();
        } catch (NoResultException e) {
            e.printStackTrace();
            return ("File with id: " + id + " not found");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Bad request";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveFile")
    @ResponseBody
    String doPost(HttpServletRequest request, @RequestParam("id") String id) {

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

    @RequestMapping(method = RequestMethod.PUT, value = "/transferFile")
    @ResponseBody
    String doTransferFile(@RequestParam("fileId") String fileId,
                          @RequestParam("storageId") String storageId) {
        try {

            fileService.transferFile(Long.parseLong(fileId), Long.parseLong(storageId));
            return ("File with id: " + fileId + " is transferred");
        } catch (Exception e) {
            e.printStackTrace();
            return ("Error transferring " + e.getMessage());

        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/transferAll")
    @ResponseBody
    String doTransferAll(@RequestParam("storageIdFrom") String idFrom,
                         @RequestParam("storageIdTo") String idTo) {
        try {

            fileService.transferAll(Long.parseLong(idFrom), Long.parseLong(idTo));
            return ("Files" + fileDAO.getAll(Long.parseLong(idTo)) + " is transferred");

        } catch (Exception e) {
            e.printStackTrace();
            return ("Error transferring " + e.getMessage());

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













