package com.kieran.api.controller;

import com.kieran.api.dao.queries.ProjectRepository;
import com.kieran.api.exceptions.ProjectNotFoundException;
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

    /**
     * @param projectIdentifier: Can either be the ID or link name of a project
     * @return: The Project if it exists (200)
     * @throws ProjectNotFoundException: If the project doesn't exist (404)
     */
    @GetMapping("/projects/{projectIdentifier}")
    public Project getProject(@PathVariable String projectIdentifier) throws ProjectNotFoundException {
        Project project;
        try {
            project = projectRepo.queryForProjectById(Integer.parseInt(projectIdentifier));
        } catch (NumberFormatException ex) {
            // The input therefore must be a link name
            project = projectRepo.queryForProjectByLink(projectIdentifier);
        }
        if (project == null)
            throw new ProjectNotFoundException(projectIdentifier);

        return project;
    }

    @PostMapping("/projects")
    public Project newProject(@RequestBody Project project) {
        int newProjectId = projectRepo.insertNewProject(project.getName(), project.getSymLink(), project.getContent());
        return projectRepo.queryForProjectById(newProjectId);
    }

    @DeleteMapping("/projects/{projectId}")
    public void removeProject(@PathVariable int projectId) {
        Project project = projectRepo.queryForProjectById(projectId);
        if (project == null)
            throw new ProjectNotFoundException(projectId);
        projectRepo.removeProject(projectId);
    }

    @PutMapping("/projects/{projectId}")
    public Project updateProject( @PathVariable int projectId, @RequestBody Project newProject) {
        Project oldProject = projectRepo.queryForProjectById(projectId);
        if (oldProject == null)
            throw new ProjectNotFoundException(projectId);

        if (newProject.getName() != null)
            projectRepo.updateProjectName(projectId, newProject.getName());
        if (newProject.getSymLink() != null)
            projectRepo.updateProjectLinkName(projectId, newProject.getSymLink());
        if (newProject.getContent() != null)
            projectRepo.updateProjectContent(projectId, newProject.getContent());

        em.clear();
        return projectRepo.queryForProjectById(projectId);
    }
}
