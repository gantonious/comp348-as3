package part1.server.clientstate;

import part1.protocol.Message;
import part1.protocol.MessageService;
import statemachine.IState;
import statemachine.StateMachine;

/**
 * Created by George on 2017-12-29.
 */
public class ErrorState implements IState<ClientState> {
    private String errorMessage;

    public ErrorState(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void handle(StateMachine<ClientState> stateMachine) {
        MessageService messageService = stateMachine.getState().getMessageService();
        Message message = new Message(errorMessage);

        messageService.writeMessage(message);
        messageService.close();
        stateMachine.finish();
    }
}
