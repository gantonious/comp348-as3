package part1.server;

import part1.poems.PoemService;
import part1.protocol.MessageService;
import part1.server.clientstate.ClientState;
import part1.server.clientstate.WelcomeState;
import part1.statemachine.StateMachine;

import java.net.Socket;

/**
 * Created by George on 2017-12-29.
 */
public class PoemClientHandler {
    private Socket clientSocket;
    private MessageService messageService;

    public PoemClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.messageService = new MessageService(clientSocket);
    }

    public void serveClient() {
        ClientState clientState = new ClientState(new PoemService(), messageService);
        StateMachine<ClientState> clientStateMachine = new StateMachine<>(clientState);
        clientStateMachine.setStateTo(new WelcomeState());
    }
}
