package com.kieran.api;

import com.kieran.api.dao.DaoConnection;
import com.kieran.api.dao.TestDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class WebsiteApiApplication {

    public static void main(String[] args) throws SQLException {
        //DaoConnection daoConnection = new DaoConnection();
        //daoConnection.connect();

        ApplicationContext context = new AnnotationConfigApplicationContext(WebsiteApiApplication.class);
        TestDao dao =  context.getBean(TestDao.class);
        dao.testQuery();


        //SpringApplication.run(WebsiteApiApplication.class, args);
    }
}
