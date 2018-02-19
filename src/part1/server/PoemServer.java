package part1.server;

import part1.poems.PoemService;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by George on 2017-12-28.
 */
public class PoemServer {
    private PoemService poemService;
    private ServerSocket serverSocket;

    public PoemServer(int port, PoemService poemService) {
        this.poemService = poemService;

        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void run() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                PoemClientHandler poemClientHandler = new PoemClientHandler(clientSocket, poemService);
                poemClientHandler.serveClient();
            } catch (Exception e) {
                continue;
            }
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
