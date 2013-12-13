/*
 * @author antivoland
 */
package ru.antivoland.dorphl.model.command;

import com.skype.Chat;
import ru.antivoland.dorphl.model.chat.ChatContext;

public class CommandContext {
    private ChatContext chatContext;
    private Chat chat;

    public ChatContext getChatContext() {
        return chatContext;
    }

    public void setChatContext(ChatContext chatContext) {
        this.chatContext = chatContext;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
