package part1.protocol.messagereading;

import part1.statemachine.IState;
import part1.statemachine.StateMachine;

/**
 * Created by George on 2017-12-29.
 */
public class NewLineDelimiterState implements IState<ReaderState> {
    @Override
    public void handle(StateMachine<ReaderState> stateMachine) {
        ReaderState readerState = stateMachine.getState();
        String nextLine = readerState.readNextLine();

        if (".".equals(nextLine.trim())) {
            stateMachine.finish();
            return;
        }

        // the newline found is not part of the delimiter so include it in the message
        readerState.getMessageBuilder().append("\n");
        readerState.getMessageBuilder().append(nextLine);
        readerState.getMessageBuilder().append("\n");
        stateMachine.setStateTo(new ReadingMessageState());
    }
}
