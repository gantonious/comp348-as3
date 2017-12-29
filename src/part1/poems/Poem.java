package part1.poems;

/**
 * Created by George on 2017-12-29.
 */
public class Poem {
    private String title;
    private String poem;

    public Poem(String title, String poem) {
        this.title = title;
        this.poem = poem;
    }

    public String getTitle() {
        return title;
    }

    public String getPoem() {
        return poem;
    }
}
