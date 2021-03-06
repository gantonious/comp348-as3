package part1.protocol.messagereading;

import part1.protocol.Message;

import java.io.BufferedReader;

/**
 * Created by George on 2017-12-29.
 */
public class ReaderState {
    private StringBuilder messageBuilder;
    private BufferedReader messageReader;

    public ReaderState(BufferedReader messageReader) {
        this.messageReader = messageReader;
        this.messageBuilder = new StringBuilder();
    }

    public BufferedReader getMessageReader() {
        return messageReader;
    }

    public StringBuilder getMessageBuilder() {
        return messageBuilder;
    }

    public String readNextLine() {
        try {
            return getMessageReader().readLine();
        } catch (Exception e) {
            return null;
        }
    }

    public Message getMessageFromState() {
        return new Message(messageBuilder.toString().trim());
    }
}
