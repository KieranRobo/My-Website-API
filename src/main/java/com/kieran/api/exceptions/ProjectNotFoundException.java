package com.kieran.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Project Doesn't Exist")
public class ProjectNotFoundException extends RuntimeException {

    public ProjectNotFoundException(int projectId) {
        super("Project with ID of '" + projectId + "' could not be found.");
    }

    public ProjectNotFoundException(String projectLink) {
        super("Project with link name of '" + projectLink + "' could not be found.");
    }
}
