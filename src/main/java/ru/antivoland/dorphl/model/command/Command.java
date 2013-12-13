/*
 * @author antivoland
 */
package ru.antivoland.dorphl.model.command;

import com.skype.ChatMessage;
import com.skype.SkypeException;
import ru.antivoland.dorphl.App;
import ru.antivoland.dorphl.model.BusinessException;
import ru.antivoland.dorphl.model.MessageProcessor;
import ru.antivoland.dorphl.model.command.handler.CommandHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Command {
    private CommandType type;
    private List<String> args = new ArrayList<String>();

    public Command(ChatMessage message) throws BusinessException {
        String messageBody;
        try {
            messageBody = message.getContent().substring(MessageProcessor.COMMAND_PREFIX.length());
        } catch (SkypeException e) {
            throw new BusinessException("Unable to retrieve message body", e);
        }

        StringTokenizer tokenizer = new StringTokenizer(messageBody);
        String strType;
        if (tokenizer.hasMoreTokens()) {
            strType = tokenizer.nextToken();
        } else {
            throw new BusinessException("Missing command type");
        }

        try {
            this.type = CommandType.valueOf(strType);
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Unknown command " + strType, e);
        }

        while (tokenizer.hasMoreTokens()) {
            this.args.add(tokenizer.nextToken());
        }
    }

    public CommandType getType() {
        return type;
    }

    public List<String> getArgs() {
        return args;
    }

    public void execute(CommandContext context) {
        CommandHandler handler = App.injector.getInstance(type.getClazz());
        handler.handle(this, context);
    }
}
