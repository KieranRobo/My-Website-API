package com.kieran.api.controller;

import com.kieran.api.dao.queries.ProjectDao;
import com.kieran.api.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectDao dao;

    @RequestMapping(value = "/project/all", method = RequestMethod.GET)
    public ResponseEntity allProjects() {
        List<Project> allPages = dao.queryForAllProjects();
        return new ResponseEntity<>(allPages, HttpStatus.OK);
    }

    @RequestMapping(value = "/project/details", method = RequestMethod.GET)
    public ResponseEntity getProject(@RequestParam("id") int projectId) {
        Project project = null;
        try {
            project = dao.queryForProject(projectId);
        } catch (EmptyResultDataAccessException ex) {
            return new ResponseEntity<>(project, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @RequestMapping(value = "/project/new", method = RequestMethod.POST)
    public ResponseEntity newProject(@RequestParam("display_name") String displayName,
                                  @RequestParam("link_name") String linkName,
                                  @RequestParam("display_content") String displayContent) {
        if (displayName.isEmpty() || linkName.isEmpty() || displayContent.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);

        dao.insertNewProject(displayName, linkName, displayContent);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/project/remove", method = RequestMethod.DELETE)
    public ResponseEntity removeProject(@RequestParam("id") int projectId) {
        dao.removeProject(projectId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
