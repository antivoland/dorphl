/*
 * @author antivoland
 */
package ru.antivoland.dorphl.settings.entity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Category {
    private String id;
    private String pattern;
    private Float probability;
    private Integer weight;
    private List<String> phrases;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Float getProbability() {
        return probability;
    }

    public void setProbability(Float probability) {
        this.probability = probability;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public List<String> getPhrases() {
        return phrases;
    }

    public void setPhrases(List<String> phrases) {
        this.phrases = phrases;
    }

    public boolean matches(String message) {
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(message);
        return m.find();
    }
}
