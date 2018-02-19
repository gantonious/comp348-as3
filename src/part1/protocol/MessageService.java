package part1.protocol;

import part1.protocol.messagereading.ReaderState;
import part1.protocol.messagereading.ReadingMessageState;
import part1.statemachine.StateMachine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by George on 2017-12-28.
 */
public class MessageService {
    private Socket clientSocket;
    private BufferedReader bufferedReader;
    private DataOutputStream dataOutputStream;

    public MessageService(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Message readNextMessage() {
        ReaderState readerState = new ReaderState(bufferedReader);
        StateMachine<ReaderState> readerStateMachine = new StateMachine<>(readerState);
        readerStateMachine.setStateTo(new ReadingMessageState());

        return readerState.getMessageFromState();
    }

    public void writeMessage(Message message) {
        try {
            dataOutputStream.write(message.getMessageBody().getBytes());
            dataOutputStream.write("\n\n.\n".getBytes());
        } catch (Exception e) {

        }
    }

    public void close() {
        try {
            clientSocket.close();
        } catch (Exception e) {

        }
    }
}
