package com.kieran.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PagesDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public PagesDao() {
    }

    public List<Map<String, Object>> queryForAllPages() {
        String sql = "SELECT * FROM pages";
        return jdbcTemplate.queryForList(sql);
    }
}
