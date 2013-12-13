/*
 * @author antivoland
 */
package ru.antivoland.dorphl.model.chat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatManager {
    private final Map<String, ChatContext> chats = new ConcurrentHashMap<String, ChatContext>();

    public ChatContext getChatContext(String chatId) {
        ChatContext chatContext;
        if (chats.containsKey(chatId)) {
            chatContext = chats.get(chatId);
        } else {
            chatContext = new ChatContext();
            chatContext.setId(chatId);
            chatContext.setEnabled(true);
            chatContext.setTalkative(false);
            chats.put(chatId, chatContext);
        }
        return chatContext;
    }
}
