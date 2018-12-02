package com.kieran.api.controller;

import com.kieran.api.dao.queries.PagesDao;
import com.kieran.api.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PagesController {

    @Autowired
    private PagesDao dao;

    @RequestMapping(value = "/pages/all", method = RequestMethod.GET)
    public ResponseEntity allPages() {
        List<Page> allPages = dao.queryForAllPages();
        return new ResponseEntity<>(allPages, HttpStatus.OK);
    }

    @RequestMapping(value = "/pages/details", method = RequestMethod.GET)
    public ResponseEntity<Page> getPage(@RequestParam("id") int pageId) {
        Page page = dao.queryForPage(pageId);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/pages/new", method = RequestMethod.POST)
    public ResponseEntity newPage(@RequestParam("name") String pageName,
                                  @RequestParam("data") String pageData) {
        if (pageName.isEmpty() || pageData.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);

        dao.insertNewPage(pageName, pageData);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/pages/remove", method = RequestMethod.DELETE)
    public ResponseEntity removePage(@RequestParam("id") int pageId) {
        dao.removePage(pageId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
