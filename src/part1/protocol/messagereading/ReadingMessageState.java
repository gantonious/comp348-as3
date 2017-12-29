package part1.protocol.messagereading;

import statemachine.IState;
import statemachine.StateMachine;

/**
 * Created by George on 2017-12-29.
 */
public class ReadingMessageState implements IState<ReaderState> {
    @Override
    public void handle(StateMachine<ReaderState> stateMachine) {
        ReaderState readerState = stateMachine.getState();
        String nextLine = readerState.readNextLine();

        if (nextLine.trim().isEmpty()) {
            stateMachine.setStateTo(new NewLineDelimiterState());
            return;
        }

        readerState.getMessageBuilder().append(nextLine);
        readerState.getMessageBuilder().append("\n");
        stateMachine.setStateTo(new ReadingMessageState());
    }
}
