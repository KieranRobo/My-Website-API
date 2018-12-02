package com.kieran.api.dao.queries;

import com.kieran.api.dao.mappers.PageMapper;
import com.kieran.api.model.Page;
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

    public List<Page> queryForAllPages() {
        String sql = "SELECT * FROM pages";
        return jdbcTemplate.query(sql, new PageMapper());
    }
}
