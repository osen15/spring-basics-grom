package com.lesson5.itemApp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lesson5.itemApp.Item;
import com.lesson5.itemApp.ServiceItem;
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
public class TestController {

    private ServiceItem serviceItem;

    @Autowired
    public TestController(ServiceItem serviceItem) {
        this.serviceItem = serviceItem;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/item/save", produces = "text-plain")
    public @ResponseBody
    String saveItem(HttpServletRequest request) {
        Item item = jsonToEntity(request);
        serviceItem.save(item);
        return "ok";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/item/update", produces = "text-plain")
    public @ResponseBody
    String updateItem(HttpServletRequest request) {
        Item item = jsonToEntity(request);
        serviceItem.update(item);
        return "ok";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/item/delete", produces = "text-plain")
    public @ResponseBody
    String deleteItem(@RequestParam("id") String id) {
        serviceItem.delete(Long.parseLong(id));
        return "ok";
    }


    private Item jsonToEntity(HttpServletRequest req) {
        StringBuilder stb = new StringBuilder();
        Gson gson = new GsonBuilder().create();
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
