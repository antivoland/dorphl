/*
 * @author antivoland
 */
package ru.antivoland.dorphl.model.command.handler;

import com.skype.SkypeException;
import ru.antivoland.dorphl.model.chat.ChatContext;
import ru.antivoland.dorphl.model.command.Command;
import ru.antivoland.dorphl.model.command.CommandContext;

public class EnableCommandHandler implements CommandHandler {
    @Override
    public void handle(Command command, CommandContext context) {
        ChatContext chatContext = context.getChatContext();
        chatContext.setEnabled(true);
        try {
            context.getChat().send("(sun)");
        } catch (SkypeException e) {
            e.printStackTrace();
        }
    }
}
