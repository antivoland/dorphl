/*
 * @author antivoland
 */
package ru.antivoland.dorphl.model;

import com.google.inject.Inject;
import com.skype.ChatMessage;
import com.skype.ChatMessageListener;
import com.skype.SkypeException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageListener implements Runnable, ChatMessageListener {
    private final Logger log = Logger.getLogger(MessageListener.class);

    private final List<ChatMessage> messages = new ArrayList<ChatMessage>();
    private final Lock lock = new ReentrantLock();
    private final Condition newMessages;
    private final Condition emptyList;
    private final MessageProcessor messageProcessor;

    @Inject
    public MessageListener(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
        this.newMessages = lock.newCondition();
        this.emptyList = lock.newCondition();
    }

    @Override
    public void run() {
        while (true) {
            log.debug("Locking...");
            lock.lock();
            try {
                while (messages.isEmpty()) {
                    log.debug("Awaiting for messages in list...");
                    newMessages.await();
                    log.debug("Continuing");
                }
                if (messages.size() > 1) {
                    log.debug("Messages in queue: " + messages.size());
                }
                for (ChatMessage message : messages) {
                    log.debug("Message processor, processing message: " + message.getId());
                    messageProcessor.processReceivedMessage(message);
                    log.debug("Message processed: " + message.getId());
                }
                messages.clear();
                log.debug("Signaling empty list");
                emptyList.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.debug("Unlocking");
                lock.unlock();
            }
        }
    }

    @Override
    public void chatMessageReceived(ChatMessage receivedChatMessage) throws SkypeException {
        log.debug("Message received: " + receivedChatMessage.getId() + " in chat " + receivedChatMessage.getChat().getId());
        lock.lock();
        try {
            while (messages.size() > 0) {
                log.debug("Messages list size > 0, awaiting");
                emptyList.await();
                log.debug("Awaiting complete");
            }
            log.debug("Adding message " + receivedChatMessage.getId() + " to list.");
            messages.add(receivedChatMessage);
            newMessages.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void chatMessageSent(ChatMessage sentChatMessage) throws SkypeException {
        // do nothing
    }
}
