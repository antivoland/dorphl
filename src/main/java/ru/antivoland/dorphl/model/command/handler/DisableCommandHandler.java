/*
 * @author antivoland
 */
package ru.antivoland.dorphl.model.command.handler;

import com.skype.SkypeException;
import ru.antivoland.dorphl.model.chat.ChatContext;
import ru.antivoland.dorphl.model.command.Command;
import ru.antivoland.dorphl.model.command.CommandContext;

public class DisableCommandHandler implements CommandHandler {
    @Override
    public void handle(Command command, CommandContext context) {
        ChatContext chatContext = context.getChatContext();
        chatContext.setEnabled(false);
        try {
            context.getChat().send("Замолкаю.");
        } catch (SkypeException e) {
            e.printStackTrace();
        }
    }
}
