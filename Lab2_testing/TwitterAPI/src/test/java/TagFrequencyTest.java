import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.when;

/**
 * Created by Daria on 08.10.2016.
 */
public class TagFrequencyTest {

    private int hours = 4;
    private String tag = "Daria";

    @Mock
    private JSONParser JSONParser;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getFrequency() throws JSONException, ParseException {
        when(JSONParser.parse()).thenReturn(createAnswer());
        TagFrequency tagFrequency = new TagFrequency(tag, hours);
        List<Long> frequency = tagFrequency.frequencyCount(tagFrequency.getTweets());
        Assert.assertTrue(frequency.size() == hours);
        Assert.assertArrayEquals(Arrays.asList((long)3, (long)2, (long)0, (long)0).toArray(), frequency.toArray());
    }

    private List<Tweet> createAnswer() {
        List<Tweet> tweets = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < 20; i++) {
            Date tweetDate = cal.getTime();
            tweetDate.setTime(tweetDate.getTime() - TimeUnit.HOURS.toMillis(i % hours));
            tweets.add(new Tweet("#Daria", tweetDate));
        }
        return tweets;
    }
}
