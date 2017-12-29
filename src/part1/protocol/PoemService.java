package part1.protocol;

import part1.state.StateMachine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by George on 2017-12-28.
 */
public class PoemService {
    private BufferedReader bufferedReader;
    private DataOutputStream dataOutputStream;

    public PoemService(Socket clientSocket) {
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PoemMessage readNextMessage() {
        ReaderState readerState = new ReaderState(bufferedReader);
        StateMachine<ReaderState> readerStateMachine = new StateMachine<>(readerState);
        readerStateMachine.setStateTo(new ReadingMessageState());

        return readerState.getMessageFromState();
    }

    public void writeMessage(PoemMessage message) {
        try {
            dataOutputStream.write(message.getMessageBody().getBytes());
            dataOutputStream.write("\n".getBytes());
            dataOutputStream.write(".\n".getBytes());
            dataOutputStream.write(".\n".getBytes());
        } catch (Exception e) {

        }
    }
}
