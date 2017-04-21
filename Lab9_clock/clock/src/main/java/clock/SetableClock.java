package clock;

import java.time.Instant;

/**
 * Created by Daria on 25.03.2017.
 */
public class SetableClock implements Clock {
    private Instant now;

    public SetableClock(Instant now) {
        this.now = now;
    }

    public void setNow(Instant now) {
        this.now = now;
    }

    public Instant now() {
        return now;
    }


}
