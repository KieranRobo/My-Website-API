package com.kieran.api.dao.queries;

import com.kieran.api.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "SELECT * FROM projects", nativeQuery = true)
    List<Project> queryForAllProjects();

    @Query(value = "SELECT * FROM projects WHERE id = :projectId", nativeQuery = true)
    Project queryForProject(@Param("projectId") int projectId);

    @Query(value = "INSERT INTO projects (display_name, link_name, date_created, last_modified, display_content) VALUES (:displayName, :linkName, now(), now(), :displayContent) RETURNING id", nativeQuery = true)
    int insertNewProject(@Param("displayName") String displayName, @Param("linkName") String linkName, @Param("displayContent") String displayContent);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM projects WHERE id = :projectId", nativeQuery = true)
    void removeProject(@Param("projectId") int projectId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE projects SET display_name = :newName, last_modified = now() WHERE id = :projectId", nativeQuery = true)
    void updateProjectName(@Param("projectId") int projectId, @Param("newName") String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE projects SET link_name = :newLinkName, last_modified = now() WHERE id = :projectId", nativeQuery = true)
    void updateProjectLinkName(@Param("projectId") int projectId, @Param("newLinkName") String linkName);

    @Transactional
    @Modifying
    @Query(value = "UPDATE projects SET display_content = :newContent, last_modified = now() WHERE id = :projectId", nativeQuery = true)
    void updateProjectContent(@Param("projectId") int projectId, @Param("newContent") String content);

}
