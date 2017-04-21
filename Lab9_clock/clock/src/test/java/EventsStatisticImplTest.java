import clock.Clock;
import clock.SetableClock;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.*;

/**
 * Created by Daria on 28.03.2017.
 */
public class EventsStatisticImplTest {

    private final double EPS = 0.001;
    String event1 = "event A";
    String event2 = "event B";
    String event3 = "event C";
    String event4 = "event D";
    Clock clock;
    EventsStatistic stats;

    @Before
    public void setUp() throws Exception {
        Instant start = Instant.EPOCH;
        clock = new SetableClock(start);
        stats = new EventsStatisticImpl(clock);
        for (int i = 0; i < 100; i++) {
            clock.setNow(start.plusSeconds(60 * i));
            stats.incEvent(event1);
            stats.incEvent(event2);
            clock.setNow(start.plusSeconds(60 * i + 30));
            stats.incEvent(event2);
        }
        clock.setNow(start.plusSeconds(60 * 100));
    }

    @Test
    public void getEventStatisticByName() throws Exception {
        double res1 = stats.getEventStatisticByName(event1);
        System.out.println("res1 = " + res1);
        assertTrue("getByName1 fail", Math.abs(res1 - 1.0) < EPS);
        double res2 = stats.getEventStatisticByName(event2);
        System.out.println("res2 = " + res2);
        assertTrue("getByName2 fail", Math.abs(res2 - 2.0) < EPS);
        double res3 = stats.getEventStatisticByName(event3);
        System.out.println("res3 = " + res3);
        assertTrue("getByName3 fail", Math.abs(res3) < EPS);
    }

    @Test
    public void getAllEventStatistic() throws Exception {
        double res = stats.getAllEventStatistic();
        assertTrue("getAll fail", Math.abs(res - 3.0) < EPS);
    }

    @Test
    public void printStatistic() throws Exception {
    }

}