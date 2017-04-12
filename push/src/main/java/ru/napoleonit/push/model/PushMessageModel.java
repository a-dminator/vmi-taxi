package ru.napoleonit.push.model;

import java.util.List;

public class PushMessageModel {

    public final String                title;
    public final String                message;
    public final int                   accentColor;
    public final int                   bgColor;
    public final String                largeIconUrl;
    public final String                bigIconUrl;
    public final List<PushButtonModel> buttonModels;
    public PushMessageModel(String                title,
                            String                message,
                            int accentColor,
                            int                   bgColor,
                            String                largeIconUrl,
                            String                bigIconUrl,
                            List<PushButtonModel> buttonModels) {

        this.title        = title;
        this.message      = message;
        this.accentColor  = accentColor;
        this.bgColor      = bgColor;
        this.largeIconUrl = largeIconUrl;
        this.bigIconUrl   = bigIconUrl;
        this.buttonModels = buttonModels;
    }

}
