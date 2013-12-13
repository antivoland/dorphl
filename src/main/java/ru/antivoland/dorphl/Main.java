/*
 * @author antivoland
 */
package ru.antivoland.dorphl;

import com.google.inject.Guice;
import com.skype.Skype;
import org.apache.commons.configuration.PropertiesConfiguration;
import ru.antivoland.dorphl.guice.DorphlMainModule;
import ru.antivoland.dorphl.model.MessageListener;
import ru.antivoland.dorphl.settings.SettingsManager;

import javax.swing.*;

public class Main {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    public static void main(String[] args) throws Exception {
        App.config = new PropertiesConfiguration("conf/dorphl.properties");
        App.runMode = RunMode.valueOf(App.config.getString("runMode"));
        App.injector = Guice.createInjector(new DorphlMainModule());

        SettingsManager settingsManager = App.injector.getInstance(SettingsManager.class);
        App.settings = settingsManager.parseSettings();

        MessageListener messageListener = App.injector.getInstance(MessageListener.class);
        new Thread(messageListener).start();
        Skype.addChatMessageListener(messageListener);

        JFrame frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
