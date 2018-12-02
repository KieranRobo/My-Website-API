package com.kieran.api.dao.mappers;

import com.kieran.api.model.Page;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PageMapper implements RowMapper<Page> {

    @Override
    public Page mapRow(ResultSet resultSet, int i) throws SQLException {
        Page page = new Page();
        page.setId(resultSet.getInt("id"));
        page.setName(resultSet.getString("page_name"));
        page.setData(resultSet.getString("page_data"));

        return page;
    }
}
