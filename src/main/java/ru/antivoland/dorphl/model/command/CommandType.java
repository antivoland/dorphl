/*
 * @author antivoland
 */
package ru.antivoland.dorphl.model.command;

import ru.antivoland.dorphl.model.command.handler.CommandHandler;
import ru.antivoland.dorphl.model.command.handler.DisableCommandHandler;
import ru.antivoland.dorphl.model.command.handler.EnableCommandHandler;
import ru.antivoland.dorphl.model.command.handler.HelpCommandHandler;

public enum CommandType {
    help(HelpCommandHandler.class),
    enable(EnableCommandHandler.class),
    disable(DisableCommandHandler.class);

    private final Class<? extends CommandHandler> clazz;

    private CommandType(Class<? extends CommandHandler> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends CommandHandler> getClazz() {
        return clazz;
    }
}
