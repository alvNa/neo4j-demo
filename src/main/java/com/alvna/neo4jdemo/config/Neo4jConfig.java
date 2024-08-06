package com.alvna.neo4jdemo.config;

//import org.neo4j.ogm.config.Configuration;

//import org.neo4j.cypherdsl.core.renderer.Configuration;
//import org.neo4j.cypherdsl.core.renderer.Dialect;
import liquibase.ext.neo4j.database.jdbc.Neo4jDriver;
import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.DataSourceClosingSpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
//import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@org.springframework.context.annotation.Configuration
@Slf4j
public class Neo4jConfig {
//    @Value("${spring.neo4j.uri}")
//    private String databaseUrl;
//
//    @Value("${spring.neo4j.username}")
//    private String userName;
//
//    @Value("${spring.neo4j.password}")
//    private String password;
//    @Bean
//    Configuration cypherDslConfiguration() {
//        return Configuration.newConfig()
//                .withDialect(Dialect.NEO4J_5).build();
//    }

//    @Bean
//    public SpringLiquibase liquibase(DataSource dataSource) {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setChangeLog("classpath:liquibase/master.xml");
//        liquibase.setDataSource(dataSource);
//        return liquibase;
//    }


//    @Bean
//    public SpringLiquibase liquibase(@Qualifier("taskExecutor") TaskExecutor taskExecutor,
//                                     DataSource dataSource, LiquibaseProperties liquibaseProperties) {
//
//        var datasource = new SimpleDriverDataSource(new Neo4jDriver(), "jdbc:neo4j:bolt://localhost:7687"); // https://github.com/spring-projects/spring-framework/blob/main/spring-jdbc/src/main/java/org/springframework/jdbc/datasource/SimpleDriverDataSource.java
//        Properties properties = new Properties();
//        properties.setProperty("user", "neo4j");
//        datasource.setConnectionProperties(properties);
//
//        // Use liquibase.integration.spring.SpringLiquibase if you don't want Liquibase to start asynchronously
//        //SpringLiquibase liquibase = new AsyncSpringLiquibase(taskExecutor, env);
//        SpringLiquibase liquibase = new DataSourceClosingSpringLiquibase();
//
//        liquibase.setDataSource(dataSource);
//        liquibase.setChangeLog("classpath:config/liquibase/master.xml");
//        liquibase.setContexts(liquibaseProperties.getContexts());
//        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
//        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
//        liquibase.setShouldRun(liquibaseProperties.isEnabled());
//
//        log.debug("Configuring Liquibase");
//
//        return liquibase;
//    }
}
