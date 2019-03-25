package com.kieran.api.exceptions;

import com.kieran.api.model.Project;

public class ProjectAlreadyExistsException extends RuntimeException {
    public ProjectAlreadyExistsException(Project project) {
        super("A project with the same key identifier already exists for " + project.toString());
    }
}
