/*
 * @author antivoland
 */
package ru.antivoland.dorphl.model;

import com.skype.ChatMessage;
import com.skype.SkypeException;
import ru.antivoland.dorphl.App;

import java.util.Random;

public class MessageResponseThread implements Runnable {
    private ChatMessage receivedChatMessage;

    public MessageResponseThread(ChatMessage receivedChatMessage) {
        this.receivedChatMessage = receivedChatMessage;
    }

    @Override
    public void run() {
        try {
            String message = receivedChatMessage.getContent();
            String phrase = App.settings.randomPhraseFor(message);
            if (phrase != null) {
                Thread.sleep(1000 + new Random().nextInt(3) * 1000);
                receivedChatMessage.getChat().send(phrase);
            }
        } catch (SkypeException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
