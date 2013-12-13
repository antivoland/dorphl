/*
 * @author antivoland
 */
package ru.antivoland.dorphl;

import com.google.inject.Injector;
import org.apache.commons.configuration.PropertiesConfiguration;
import ru.antivoland.dorphl.settings.BotSettings;

public class App {
    public static PropertiesConfiguration config;
    public static RunMode runMode;
    public static Injector injector;
    public static BotSettings settings;
}
