package part1.server;

import part1.protocol.PoemMessage;
import part1.protocol.PoemService;

import java.net.Socket;

/**
 * Created by George on 2017-12-29.
 */
public class PoemClientHandler {
    private Socket clientSocket;
    private PoemService poemService;

    public PoemClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.poemService = new PoemService(clientSocket);
    }

    public void serveClient() {
        while(true) {
            PoemMessage poemMessage = poemService.readNextMessage();
            if (poemMessage.getMessageBody().equals("END")) {

                return;
            }

            poemService.writeMessage(poemMessage);
        }
    }
}
