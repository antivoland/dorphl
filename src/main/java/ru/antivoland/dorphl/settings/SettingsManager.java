/*
 * @author antivoland
 */
package ru.antivoland.dorphl.settings;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.configuration.ConfigurationUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class SettingsManager {
    private final String settingsUrl;

    @Inject
    public SettingsManager(@Named("settings-url") String settingsUrl) {
        this.settingsUrl = settingsUrl;
    }

    public BotSettings parseSettings() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        URL url = ConfigurationUtils.locate(settingsUrl);
        ConfigurationFile file = mapper.readValue(url, ConfigurationFile.class);

        BotSettings settings = new BotSettings();
        settings.setCategories(file.getCategories());
        return settings;
    }
}
