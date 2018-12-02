package com.kieran.api.dao.queries;

import com.kieran.api.dao.mappers.PageMapper;
import com.kieran.api.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PagesDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public PagesDao() {
    }

    public List<Page> queryForAllPages() {
        String sql = "SELECT * FROM pages";
        return jdbcTemplate.query(sql, new PageMapper());
    }

    public Page queryForPage(int pageId) {
        String sql = "SELECT * FROM pages WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new PageMapper(), pageId);
    }

    public void insertNewPage(String pageName, String pageData) {
        String sql = "INSERT INTO pages (page_name, page_data) VALUES (?, ?)";
        jdbcTemplate.update(sql, pageName, pageData);
    }

    public void removePage(int pageId) {
        String sql = "DELETE FROM pages WHERE id = ?";
        jdbcTemplate.update(sql, pageId);
    }
}
