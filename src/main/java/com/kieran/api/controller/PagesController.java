package com.kieran.api.controller;

import com.google.gson.Gson;
import com.kieran.api.dao.PagesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PagesController {

    @Autowired
    private PagesDao dao;

    private Gson gson = new Gson();

    @RequestMapping(value = "/pages/all", method = RequestMethod.GET)
    public String test() {
        List<Map<String, Object>> allPages = dao.queryForAllPages();
        return gson.toJson(allPages);
    }
}
