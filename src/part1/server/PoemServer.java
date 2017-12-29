package part1.server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by George on 2017-12-28.
 */
public class PoemServer {
    private ServerSocket serverSocket;

    public PoemServer(int port) {
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
                PoemClientHandler poemClientHandler = new PoemClientHandler(clientSocket);
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
