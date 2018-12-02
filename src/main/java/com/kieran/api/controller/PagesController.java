package com.kieran.api.controller;

import com.kieran.api.dao.queries.PagesDao;
import com.kieran.api.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PagesController {

    @Autowired
    private PagesDao dao;

    @RequestMapping(value = "/pages/all", method = RequestMethod.GET)
    public ResponseEntity test() {
        List<Page> allPages = dao.queryForAllPages();
        return new ResponseEntity<>(allPages, HttpStatus.OK);
    }


}
