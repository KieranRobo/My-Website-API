package com.kieran.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class WebsiteApiApplication {

    public static void main(String[] args) throws SQLException {
        //DaoConnection daoConnection = new DaoConnection();
        //daoConnection.connect();

        /*
        ApplicationContext context = new AnnotationConfigApplicationContext(WebsiteApiApplication.class);
        TestDao dao =  context.getBean(TestDao.class);
        dao.testQuery();
        */


        SpringApplication.run(WebsiteApiApplication.class, args);
    }
}
