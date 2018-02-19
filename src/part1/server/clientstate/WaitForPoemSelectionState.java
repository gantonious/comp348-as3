package part1.server.clientstate;

import part1.poems.Poem;
import part1.protocol.Message;
import part1.protocol.MessageService;
import part1.statemachine.IState;
import part1.statemachine.StateMachine;

import java.util.List;

/**
 * Created by George on 2017-12-29.
 */
public class WaitForPoemSelectionState implements IState<ClientState> {
    private List<Poem> poems;

    public WaitForPoemSelectionState(List<Poem> poems) {
        this.poems = poems;
    }

    @Override
    public void handle(StateMachine<ClientState> stateMachine) {
        MessageService messageService = stateMachine.getState().getMessageService();
        Message selectionMessage = messageService.readNextMessage();

        if (isSelectionValid(selectionMessage.getMessageBody())) {
            int selection = Integer.parseInt(selectionMessage.getMessageBody());
            stateMachine.setStateTo(new DeliverPoemState(poems.get(selection - 1)));
        } else {
            stateMachine.setStateTo(new ErrorState("Make sure you select using one of the numbers listed."));
        }

    }

    private boolean isSelectionValid(String selectionMessage) {
        try {
            int selection = Integer.parseInt(selectionMessage);
            return selection > 0 && selection <= poems.size();
        } catch (Exception e) {
            return false;
        }
    }
}
