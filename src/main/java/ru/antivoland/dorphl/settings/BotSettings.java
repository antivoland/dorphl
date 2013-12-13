/*
 * @author antivoland
 */
package ru.antivoland.dorphl.settings;

import ru.antivoland.dorphl.settings.entity.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BotSettings {
    private List<Category> categories = new ArrayList<Category>();
    private Random random = new Random();

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String randomPhraseFor(String message) {
        Category category = randomCategoryFor(message);
        if (category == null) {
            return null;
        }
        int index = random.nextInt(category.getPhrases().size());
        return category.getPhrases().get(index);
    }

    private Category randomCategoryFor(String message) {
        float chance = random.nextFloat();
        int totalWeight = 0;
        List<Integer> weights = new ArrayList<Integer>();
        for (Category category : categories) {
            if (category.getProbability() > chance && category.matches(message)) {
                weights.add(category.getWeight());
            } else {
                weights.add(0);
            }
        }

        int randomWeight = 1 + random.nextInt(totalWeight > 1 ? totalWeight - 1 : 1);
        int index = 0;
        while (index < weights.size() && weights.get(index) < randomWeight) {
            ++index;
        }
        return index < categories.size() ? categories.get(index) : null;
    }
}