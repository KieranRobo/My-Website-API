package com.kieran.api.dao.queries;

import com.kieran.api.model.Project;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "SELECT * FROM projects", nativeQuery = true)
    List<Project> queryForAllProjects();

    @Query(value = "SELECT * FROM projects WHERE id = ?", nativeQuery = true)
    Project queryForProject(@Param("projectId") int projectId);

    @Query(value = "INSERT INTO projects (display_name, link_name, date_created, last_modified, display_content) VALUES (?, ?, now(), now(), ?) RETURNING id", nativeQuery = true)
    int insertNewProject(@Param("displayName") String displayName, @Param("linkName") String linkName, @Param("displayContent") String displayContent);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM projects WHERE id = ?", nativeQuery = true)
    void removeProject(@Param("projectId") int projectId);
}
