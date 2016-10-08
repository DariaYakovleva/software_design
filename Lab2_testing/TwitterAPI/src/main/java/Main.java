import java.util.Arrays;
import java.util.List;

/**
 * Created by Daria on 01.10.2016.
 */
public class Main {

    public static void main(String[] args) {
        int hours = 4;
        TagFrequency tags = new TagFrequency("daria", hours);
        List<Long> frequencies = tags.getFrequency();
        System.err.println(Arrays.toString(frequencies.toArray()));
    }
}
