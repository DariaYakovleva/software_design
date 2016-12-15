package config;

import dao.TODODao;
import dao.TODOInMemoryDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMemoryDaoContextConfiguration {
    @Bean
    public TODODao TODODao() {
        return new TODOInMemoryDao();
    }
}
