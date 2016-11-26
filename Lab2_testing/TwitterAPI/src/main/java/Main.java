import java.util.Arrays;
import java.util.List;

/**
 * Created by Daria on 01.10.2016.
 */
public class Main {

    public static void main(String[] args) {
        int hours = 4;
        TagFrequency tags = new TagFrequency();
        List<Long> frequencies = tags.getFrequency("daria", hours);
        System.out.println(Arrays.toString(frequencies.toArray()));
    }
}
