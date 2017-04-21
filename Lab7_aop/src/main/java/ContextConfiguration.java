import aspect.LoggingExecutionTimeAspect;
import aspect.LoggingIOException;
import dao.BankDao;
import domain.AccountManager;
import domain.AccountManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Daria on 04.03.2017.
 */
@Configuration
@EnableAspectJAutoProxy
public class ContextConfiguration {
    @Bean
    public AccountManager account() {
        return new AccountManagerImpl(new BankDao());
    }

    @Bean
    public LoggingExecutionTimeAspect aspect() {
        return new LoggingExecutionTimeAspect();
    }

    @Bean
    public LoggingIOException error() {
        return new LoggingIOException();
    }
}
