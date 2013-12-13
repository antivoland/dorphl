/*
 * @author antivoland
 */
package ru.antivoland.dorphl.model.chat;

public class ChatContext {
    private String id;
    private boolean enabled;
    private boolean talkative;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isTalkative() {
        return talkative;
    }

    public void setTalkative(boolean talkative) {
        this.talkative = talkative;
    }
}
