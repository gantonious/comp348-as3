package part1.protocol;

/**
 * Created by George on 2017-12-28.
 *
 * Message line1
 * Message line2
 * /n
 * ./n
 *
 */
public class PoemMessage {
    private String messageBody;

    public PoemMessage(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageBody() {
        return messageBody;
    }
}
