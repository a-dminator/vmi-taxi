package ru.napoleonit.push.model;

public class PushButtonModel {

    public final String text;
    public final String imageUrl;
    public final int    actionId;
    public PushButtonModel(String text,
                           String imageUrl,
                           int    actionId) {

        this.text     = text;
        this.imageUrl = imageUrl;
        this.actionId = actionId;
    }

}
