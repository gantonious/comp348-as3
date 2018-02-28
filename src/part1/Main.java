package part1;

import part1.poems.PoemService;
import part1.server.PoemServer;

/**
 * title: Main.java
 * description: The main entry for the Poem of the Day server.
 * date: February 28, 2018
 * @author George Antonious
 * @version 1.0
 * @copyright 2018 George Antonious
 *
 * I declare that this assignment is my own work and that all material
 * previously written or published in any source by any other person
 * has been duly acknowledged in the assignment. I have not submitted
 * this work, or a significant part thereof, previously as part of any
 * academic program. In submitting this assignment I give permission to
 * copy it for assessment purposes only.
 *
 * The usage, design, and test plan for this part can be found in the
 * README.md file in the root of this project. It is recommended to view
 * it in a markdown reader.
 */
public class Main {
    public static void main(String[] args) {
        PoemServer poemServer = new PoemServer(1822, new PoemService(args[0]));
        createShutdownHook(poemServer);
        poemServer.run();
    }

    private static void createShutdownHook(final PoemServer poemServer) {
        Runnable shutdownRunnable = new Runnable() {
            @Override
            public void run() {
                poemServer.stop();
            }
        };
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownRunnable));
    }

}
