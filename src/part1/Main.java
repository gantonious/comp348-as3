package part1;

import part1.poems.PoemService;
import part1.server.PoemServer;

/**
 * Created by George on 2017-12-29.
 */
public class Main {
    public static void main(String[] args) {
        PoemServer poemServer = new PoemServer(1822, new PoemService("src/part1/poems.txt"));
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
