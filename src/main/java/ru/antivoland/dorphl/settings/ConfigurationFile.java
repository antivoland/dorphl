/*
 * @author antivoland
 */
package ru.antivoland.dorphl.settings;

import ru.antivoland.dorphl.settings.entity.Category;

import java.util.List;

public class ConfigurationFile {
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
