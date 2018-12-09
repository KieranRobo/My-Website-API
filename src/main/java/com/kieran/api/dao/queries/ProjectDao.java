package com.kieran.api.dao.queries;

import com.kieran.api.dao.mappers.ProjectMapper;
import com.kieran.api.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProjectDao() {
    }

    public List<Project> queryForAllProjects() {
        String sql = "SELECT * FROM projects";
        return jdbcTemplate.query(sql, new ProjectMapper());
    }

    public Project queryForProject(int projectId) {
        String sql = "SELECT * FROM projects WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ProjectMapper(), projectId);
    }

    public void insertNewProject(String displayName, String linkName, String displayContent) {
        String sql = "INSERT INTO projects (display_name, link_name, date_created, last_modified, display_content) VALUES (?, ?, now(), now(), ?)";
        jdbcTemplate.update(sql, displayName, linkName, displayContent);
    }

    public void removeProject(int projectId) {
        String sql = "DELETE FROM projects WHERE id = ?";
        jdbcTemplate.update(sql, projectId);
    }
}
