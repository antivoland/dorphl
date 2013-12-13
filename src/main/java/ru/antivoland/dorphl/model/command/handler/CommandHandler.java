/*
 * @author antivoland
 */
package ru.antivoland.dorphl.model.command.handler;

import ru.antivoland.dorphl.model.command.Command;
import ru.antivoland.dorphl.model.command.CommandContext;

public interface CommandHandler {
    void handle(Command command, CommandContext context);
}
