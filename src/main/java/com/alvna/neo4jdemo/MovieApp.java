package com.alvna.neo4jdemo;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration;
//import org.springframework.data.neo4j.config.EnableNeo4jAuditing;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {JdbcRepositoriesAutoConfiguration.class})
@EnableNeo4jRepositories
@EnableScheduling
//@EnableNeo4jAuditing
public class MovieApp
{
    public static void main(String[] args) {
        SpringApplication.run(MovieApp.class, args);
    }
}
