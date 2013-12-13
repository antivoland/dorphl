/*
 * @author antivoland
 */
package ru.antivoland.dorphl.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import ru.antivoland.dorphl.App;
import ru.antivoland.dorphl.model.MessageListener;
import ru.antivoland.dorphl.model.MessageProcessor;
import ru.antivoland.dorphl.model.chat.ChatManager;
import ru.antivoland.dorphl.model.command.handler.DisableCommandHandler;
import ru.antivoland.dorphl.model.command.handler.EnableCommandHandler;
import ru.antivoland.dorphl.model.command.handler.HelpCommandHandler;
import ru.antivoland.dorphl.settings.SettingsManager;

public class DorphlMainModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named("settings-url")).toInstance(App.config.getString("settings.url"));

        bind(SettingsManager.class).in(Scopes.SINGLETON);
        bind(MessageListener.class).in(Scopes.SINGLETON);
        bind(MessageProcessor.class).in(Scopes.SINGLETON);
        bind(ChatManager.class).in(Scopes.SINGLETON);

        bindCommands();
    }

    private void bindCommands() {
        bind(HelpCommandHandler.class).in(Scopes.SINGLETON);
        bind(EnableCommandHandler.class).in(Scopes.SINGLETON);
        bind(DisableCommandHandler.class).in(Scopes.SINGLETON);
    }
}
