/*
 * @author antivoland
 */
package ru.antivoland.dorphl.model;

import com.google.inject.Inject;
import com.skype.ChatMessage;
import com.skype.SkypeException;
import org.apache.log4j.Logger;
import ru.antivoland.dorphl.model.chat.ChatContext;
import ru.antivoland.dorphl.model.chat.ChatManager;
import ru.antivoland.dorphl.model.command.Command;
import ru.antivoland.dorphl.model.command.CommandContext;

public class MessageProcessor {
    public static final String COMMAND_PREFIX = "!";

    private static final Logger log = Logger.getLogger(MessageProcessor.class);

    private final ChatManager chatManager;

    @Inject
    public MessageProcessor(ChatManager chatManager) {
        this.chatManager = chatManager;
    }

    public void processReceivedMessage(ChatMessage receivedChatMessage) {
        try {
            ChatContext chatContext = chatManager.getChatContext(receivedChatMessage.getChat().getId());
            String message = receivedChatMessage.getContent();
            log.debug("Processing message \"" + message + "\"");
            if (message.startsWith(COMMAND_PREFIX)) {
                log.debug("call process command");
                processCommand(receivedChatMessage, chatContext);
                log.debug("finished call process command");
            } else {
                if (chatContext.isEnabled()) {
                    log.debug("call process message");
                    processMessage(receivedChatMessage);
                    log.debug("finished call process message");
                }
            }
        } catch (SkypeException e) {
            e.printStackTrace();
        }
    }

    private void processCommand(ChatMessage receivedChatMessage, ChatContext chatContext) {
        try {
            CommandContext commandContext = new CommandContext();
            commandContext.setChatContext(chatContext);
            commandContext.setChat(receivedChatMessage.getChat());
            new Command(receivedChatMessage).execute(commandContext);
        } catch (BusinessException e) {
            e.printStackTrace();
        } catch (SkypeException e) {
            e.printStackTrace();
        }
    }

    private void processMessage(ChatMessage receivedChatMessage) throws SkypeException {
        // TODO: magic number
        if (System.currentTimeMillis() - receivedChatMessage.getTime().getTime() > 60000) {
            return;
        }

        ChatMessage.Type messageType = receivedChatMessage.getType();
        if (messageType == ChatMessage.Type.SAID || messageType == ChatMessage.Type.EMOTED) {
            new Thread(new MessageResponseThread(receivedChatMessage)).run();
        }
    }
}
