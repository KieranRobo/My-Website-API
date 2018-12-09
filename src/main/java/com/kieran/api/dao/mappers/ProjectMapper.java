package com.kieran.api.dao.mappers;

import com.kieran.api.model.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet resultSet, int i) throws SQLException {
        Project project = new Project();

        project.setId(resultSet.getInt("id"));
        project.setName(resultSet.getString("display_name"));
        project.setSymLink(resultSet.getString("link_name"));
        project.setDateCreated(resultSet.getDate("date_created"));
        project.setLastModified(resultSet.getDate("last_modified"));
        project.setContent(resultSet.getString("display_content"));

        return project;
    }
}
