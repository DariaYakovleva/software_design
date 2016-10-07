import java.util.List;

/**
 * Created by Daria on 01.10.2016.
 */
public class Main {

    public static void main(String[] args) {
        TagFrequency tags = new TagFrequency("love", 4);
        List<Integer> frequencies = tags.getFrequency();
    }
}
