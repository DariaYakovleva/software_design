package config;

import dao.TODOJdbcDao;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class JdbcDaoContextConfiguration {
    @Bean
    public TODOJdbcDao TODOJdbcDao(DataSource dataSource) {
        return new TODOJdbcDao(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:todos.db");
        dataSource.setUsername("");
        dataSource.setPassword("");
        return dataSource;
    }
}
