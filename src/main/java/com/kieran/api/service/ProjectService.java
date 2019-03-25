package com.kieran.api.service;

import com.kieran.api.dao.queries.ProjectRepository;
import com.kieran.api.exceptions.ProjectAlreadyExistsException;
import com.kieran.api.exceptions.ProjectNotFoundException;
import com.kieran.api.model.Project;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectService {

    @Resource
    private ProjectRepository projectRepo;

    public List<Project> getAll() {
        return projectRepo.queryForAllProjects();
    }

    public Project retrieveById(int id) {
        Project project = projectRepo.queryForProjectById(id);
        if (project == null) {
            throw new ProjectNotFoundException(id);
        }
        return project;
    }

    public Project retrieveByLink(String linkName) throws ProjectNotFoundException {
        Project project = projectRepo.queryForProjectByLink(linkName);
        if (project == null) {
            throw new ProjectNotFoundException(linkName);
        }
        return project;
    }

    public int create(Project project) throws ProjectAlreadyExistsException {
        if (retrieveByLink(project.getSymLink()) != null) {
            throw new ProjectAlreadyExistsException(project);
        }
        return projectRepo.insertNewProject(project.getName(), project.getSymLink(), project.getDisplayImage(), project.getContent());
    }

    public void delete(int id) {
        if (projectRepo.queryForProjectById(id) == null) {
            throw new ProjectNotFoundException(id);
        }
        projectRepo.removeProject(id);
    }

    public void update(int id, Project newProject) {
        if (projectRepo.queryForProjectById(id) == null) {
            throw new ProjectNotFoundException(id);
        }

        if (newProject.getName() != null) {
            projectRepo.updateProjectName(id, newProject.getName());
        }
        if (newProject.getSymLink() != null) {
            projectRepo.updateProjectLinkName(id, newProject.getSymLink());
        }
        if (newProject.getContent() != null) {
            projectRepo.updateProjectContent(id, newProject.getContent());
        }
        if (newProject.getDisplayImage() != null) {
            projectRepo.updateProjectImage(id, newProject.getDisplayImage());
        }
    }

}
