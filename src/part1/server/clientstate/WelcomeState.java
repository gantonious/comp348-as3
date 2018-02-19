package part1.server.clientstate;

import part1.poems.Poem;
import part1.poems.PoemService;
import part1.protocol.Message;
import part1.protocol.MessageService;
import part1.statemachine.IState;
import part1.statemachine.StateMachine;

import java.util.List;

/**
 * Created by George on 2017-12-29.
 */
public class WelcomeState implements IState<ClientState> {
    @Override
    public void handle(StateMachine<ClientState> stateMachine) {
        PoemService poemService = stateMachine.getState().getPoemService();
        MessageService messageService = stateMachine.getState().getMessageService();

        List<Poem> poems = poemService.getAllPoems();

        StringBuilder welcomeMessageBuilder = new StringBuilder();
        welcomeMessageBuilder.append("Welcome to the Poem of The Day Server");
        welcomeMessageBuilder.append("\n");
        welcomeMessageBuilder.append("Select one of the following poems:");
        welcomeMessageBuilder.append("\n");

        for (int i = 0; i < poems.size(); i++) {
            welcomeMessageBuilder.append(String.format("%d. %s\n", i + 1, poems.get(i).getTitle()));
        }

        messageService.writeMessage(new Message(welcomeMessageBuilder.toString().trim()));
        stateMachine.setStateTo(new WaitForPoemSelectionState(poems));
    }
}