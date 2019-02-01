package com.kieran.api.controller;

import com.kieran.api.dao.queries.ProjectRepository;
import com.kieran.api.exceptions.ProjectNotFoundException;
import com.kieran.api.model.NewProject;
import com.kieran.api.model.Project;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
public class ProjectController {

    @Resource
    private ProjectRepository projectRepo;

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/projects")
    public List<Project> allProjects() {
        return projectRepo.queryForAllProjects();
    }

    @GetMapping("/projects/{projectId}")
    public Project getProject(@PathVariable int projectId) throws ProjectNotFoundException {
        Project project = projectRepo.queryForProject(projectId);
        if (project == null)
            throw new ProjectNotFoundException(projectId);
        return project;
    }

    @PostMapping("/projects")
    public Project newProject(@RequestBody NewProject project) {
        int newProjectId = projectRepo.insertNewProject(project.getName(), project.getSymLink(), project.getContent());
        return projectRepo.queryForProject(newProjectId);
    }

    @DeleteMapping("/projects/{projectId}")
    public void removeProject(@PathVariable int projectId) {
        Project project = projectRepo.queryForProject(projectId);
        if (project == null)
            throw new ProjectNotFoundException(projectId);
        projectRepo.removeProject(projectId);
    }

    @PutMapping("/projects/{projectId}")
    public Project updateProject( @PathVariable int projectId, Project newProject) {
        Project oldProject = projectRepo.queryForProject(projectId);
        if (oldProject == null)
            throw new ProjectNotFoundException(projectId);

        if (newProject.getName() != null)
            projectRepo.updateProjectName(newProject.getName(), projectId);

        em.clear();
        // TODO: this seemingly returns the project before the update.
        return projectRepo.queryForProject(projectId);
    }
}
