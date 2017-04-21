package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.*;


@Aspect
public class LoggingExecutionTimeAspect {


    public LoggingExecutionTimeAspect() {
    }

    Map<String, List<Long>> logs =  new HashMap<>();
    long prevT = 0;
    @Around("execution(* domain.AccountManager.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String name = joinPoint.getSignature().getName();
        long startTime = System.nanoTime();

        Object result = joinPoint.proceed(joinPoint.getArgs());
        Long spTime = System.nanoTime() - startTime;
        if (!logs.containsKey(name)) {
            logs.put(name, new ArrayList<Long>());
        }
        logs.get(name).add(spTime);

        if (System.nanoTime() - prevT > 10000) {
            getLogs();
            prevT = System.nanoTime();
        }
        return result;
    }

    public void getLogs() {
        System.out.println("================");
        for (Map.Entry<String, List<Long>> ent: logs.entrySet()) {
            int cnt = ent.getValue().size();
            long sum = 0;
            for (Long x: ent.getValue()) {
                sum += x;
            }
            System.out.println(ent.getKey() + ": COUNT = " + cnt +
                    "; EX. TIME = " + sum + "; MEAN TIME = " + (double)sum / cnt);
        }
    }
}
