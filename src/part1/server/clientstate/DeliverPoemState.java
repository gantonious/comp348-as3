package part1.server.clientstate;

import part1.poems.Poem;
import part1.protocol.Message;
import part1.protocol.MessageService;
import part1.statemachine.IState;
import part1.statemachine.StateMachine;

/**
 * Created by George on 2017-12-29.
 */
public class DeliverPoemState implements IState<ClientState> {
    private Poem selectedPoem;

    public DeliverPoemState(Poem selectedPoem) {
        this.selectedPoem = selectedPoem;
    }

    @Override
    public void handle(StateMachine<ClientState> stateMachine) {
        MessageService messageService = stateMachine.getState().getMessageService();

        StringBuilder poemMessageBuilder = new StringBuilder();
        poemMessageBuilder.append("Title: ");
        poemMessageBuilder.append(selectedPoem.getTitle());
        poemMessageBuilder.append("\n----------------\n");
        poemMessageBuilder.append(selectedPoem.getPoem());
        poemMessageBuilder.append("\n----------------\n");
        poemMessageBuilder.append("Thanks for reading, visit again soon!");

        Message poemMessage = new Message(poemMessageBuilder.toString());
        messageService.writeMessage(poemMessage);
        messageService.close();
        stateMachine.finish();
    }
}
