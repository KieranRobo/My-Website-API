package com.kieran.api.controller;

import com.kieran.api.dao.queries.ProjectRepository;
import com.kieran.api.exceptions.ProjectNotFoundException;
import com.kieran.api.model.NewProject;
import com.kieran.api.model.Project;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ProjectController {

    @Resource
    private ProjectRepository projectRepo;

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
    public Project updateProject( @PathVariable int projectId,
                                 @RequestParam String name) {
        Project project = projectRepo.queryForProject(projectId);
        if (project == null)
            throw new ProjectNotFoundException(projectId);

        if (name != null)
            projectRepo.updateProjectName(name, projectId);

        // TODO: this seemingly returns the project before the update.
        return projectRepo.queryForProject(projectId);
    }
}
