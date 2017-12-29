package part1.poems;

import java.util.Arrays;
import java.util.List;

/**
 * Created by George on 2017-12-29.
 */
public class PoemService {

    public List<Poem> getAllPoems() {
        return Arrays.asList(
            new Poem("Jess", "Jess is lovely, jess is true, jess is all the love of blu"),
            new Poem("George", "Jess\nGeorge\nlove"),
            new Poem("Wapoz", "dang,swef,wapoz")
        );
    }
}
