package com.kieran.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class TestDao extends JdbcDaoSupport {

    @Autowired
    public TestDao(DataSource dataSource)
    {
        this.setDataSource(dataSource);
    }

    public List<Map<String, Object>> testQuery() {
        String sql = "SELECT 1";

        return this.getJdbcTemplate().queryForList(sql);
    }
}
