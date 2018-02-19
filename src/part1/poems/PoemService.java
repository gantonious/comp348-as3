package part1.poems;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by George on 2017-12-29.
 */
public class PoemService {

    private final static String TITLE_SEPARATOR = "---";
    private final static String POEM_SEPARATOR = "===";

    private List<Poem> poems;

    public PoemService(String poemsFile) {
        poems = loadPoemsFrom(poemsFile);
    }

    private List<Poem> loadPoemsFrom(String poemsFile) {
        try {
            return tryToLoadPoemsFrom(poemsFile);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Could not load poems from: %s.", poemsFile));
        }
    }

    private List<Poem> tryToLoadPoemsFrom(String poemsFile) throws Exception {
        String rawFile = new String(Files.readAllBytes(Paths.get(poemsFile)));

        return Arrays.stream(rawFile.split(POEM_SEPARATOR))
                .map(this::convertToPoem)
                .collect(Collectors.toList());
    }

    private Poem convertToPoem(String rawPoem) {
        String[] separateTitleAndBody = rawPoem.split(TITLE_SEPARATOR);
        return new Poem(separateTitleAndBody[0].trim(), separateTitleAndBody[1].trim());
    }

    public List<Poem> getAllPoems() {
        return new ArrayList<>(poems);
    }
}
