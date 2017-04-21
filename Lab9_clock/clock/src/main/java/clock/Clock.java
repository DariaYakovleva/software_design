package clock;

import java.time.Instant;

/**
 * Created by Daria on 25.03.2017.
 */
public interface Clock {
    Instant now();
    void setNow(Instant now);
}
