package com.kieran.api.controller;

import com.kieran.api.exceptions.ProjectNotFoundException;
import com.kieran.api.model.Project;
import com.kieran.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/projects")
    public List<Project> allProjects() {
        return projectService.getAll();
    }

    /**
     * @param projectIdentifier: Can either be the ID or link name of a project
     * @return: The Project if it exists (200)
     * @throws ProjectNotFoundException: If the project doesn't exist (404)
     */
    @GetMapping("/projects/{projectIdentifier}")
    public Project getProject(@PathVariable String projectIdentifier) throws ProjectNotFoundException {
        Project project;
        try {
            project = projectService.retrieveById(Integer.parseInt(projectIdentifier));
        } catch (NumberFormatException ex) {
            // The input therefore must be a link name
            project = projectService.retrieveByLink(projectIdentifier);
        }
        return project;
    }

    @PostMapping("/projects")
    public Project newProject(@RequestBody Project project) {
        int newProjectId = projectService.create(project);
        return projectService.retrieveById(newProjectId);
    }

    @DeleteMapping("/projects/{projectId}")
    public void removeProject(@PathVariable int projectId) {
        projectService.delete(projectId);
    }

    @PutMapping("/projects/{projectId}")
    public Project updateProject( @PathVariable int projectId, @RequestBody Project newProject) {
        projectService.update(projectId, newProject);
        em.clear();

        return projectService.retrieveById(projectId);
    }
}
