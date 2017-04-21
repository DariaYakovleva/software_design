import clock.Clock;

import java.time.Instant;
import java.util.*;

/**
 * Created by Daria on 25.03.2017.
 */
public class EventsStatisticImpl implements EventsStatistic {

    Map<String, Deque<Instant>> events;
    Clock clock;

    public EventsStatisticImpl(Clock clock) {
        events = new HashMap<String, Deque<Instant>>();
        this.clock = clock;
    }

    public void incEvent(String name) {
        if (!events.containsKey(name)) {
            events.put(name, new LinkedList<Instant>());
        }
        events.get(name).add(clock.now());
    }

    public double getEventStatisticByName(String name) {
        if (!events.containsKey(name)) {
            return 0;
        }
        Instant curTime = clock.now();
        Deque<Instant> curEvents = events.get(name);
        System.out.println(curEvents.size());
        while (!curEvents.isEmpty() && Math.abs(curEvents.getFirst().getEpochSecond() - curTime.getEpochSecond()) > 3600) {
            curEvents.removeFirst();
        }
        events.put(name, curEvents);
        System.out.println(curEvents.size());
        return (float)curEvents.size() / 60;
    }

    public double getAllEventStatistic() {
        double average = 0.0;
        for (Map.Entry<String, Deque<Instant>> x: events.entrySet()) {
            average += getEventStatisticByName(x.getKey());
        }
        return average;
    }

    public void printStatistic() {
        for (Map.Entry<String, Deque<Instant>> x: events.entrySet()) {
            System.out.println("EVENT = " + x.getKey() + " RPM = " + getEventStatisticByName(x.getKey()));
        }
    }
}
