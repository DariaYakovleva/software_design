package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import java.io.IOException;

/**
 * Created by Daria on 11.03.2017.
 */
@Aspect
public class
LoggingIOException {

    @AfterThrowing(pointcut = "execution(* domain.AccountManager.*(..))", throwing= "error")
    public void logAfterThrowing(JoinPoint joinPoint, IOException error) {
        System.out.println("EXCEPTION: " + error.getMessage());
        System.out.println("******");
    }
}
