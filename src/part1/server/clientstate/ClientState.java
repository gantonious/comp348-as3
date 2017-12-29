package part1.server.clientstate;

import part1.poems.PoemService;
import part1.protocol.MessageService;

/**
 * Created by George on 2017-12-29.
 */
public class ClientState {
    private PoemService poemService;
    private MessageService messageService;

    public ClientState(PoemService poemService, MessageService messageService) {
        this.poemService = poemService;
        this.messageService = messageService;
    }

    public PoemService getPoemService() {
        return poemService;
    }

    public void setPoemService(PoemService poemService) {
        this.poemService = poemService;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
}
