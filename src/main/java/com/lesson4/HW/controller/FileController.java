package com.lesson4.HW.controller;

import com.google.gson.Gson;
import com.lesson4.HW.DAO.FileDAO;
import com.lesson4.HW.models.File;
import com.lesson4.HW.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return fileService.findById(Long.parseLong(id)).toString();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveFile")
    @ResponseBody
    String doPost(HttpServletRequest request, @RequestParam("id") String id) throws Exception {
        File file = jsonToEntity(request);
        fileService.save(file, Long.parseLong(id));
        return "File with id: " + file.getId() + " is saved";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateFile")
    @ResponseBody
    String doPut(HttpServletRequest req) {
        File file = jsonToEntity(req);
        fileService.update(file);
        return "File with id: " + file.getId() + " is updated";

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/transferFile")
    @ResponseBody
    String doTransferFile(@RequestParam("fileId") String fileId,
                          @RequestParam("storageId") String storageId) throws Exception {
        fileService.transferFile(Long.parseLong(fileId), Long.parseLong(storageId));
        return "File with id: " + fileId + " is transferred";

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/transferAll")
    @ResponseBody
    String doTransferAll(@RequestParam("storageIdFrom") String idFrom,
                         @RequestParam("storageIdTo") String idTo) throws Exception {
        fileService.transferAll(Long.parseLong(idFrom), Long.parseLong(idTo));
        return "Files is transferred";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteFile")
    @ResponseBody
    String doDelete(@RequestParam("id") String ID) {

        fileService.delete(Long.parseLong(ID));
        return "file with id: " + ID + " deleted";
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













