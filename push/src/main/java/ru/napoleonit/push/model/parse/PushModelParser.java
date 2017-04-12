package ru.napoleonit.push.model.parse;

import android.graphics.Color;
import android.os.Bundle;

import ru.napoleonit.push.model.PushMessageModel;

public class PushModelParser {

    // Bundle[{price=Kirill-01, collapse_key=do_not_collapse}]

    // Bundle[{custom={"i":"0cdfe238-0704-481e-a7f8-19e5d4adfc3c"}, o=[{"p":"","i":"","n":""}], bgac=FFFFFFFF, ledc=FF000000, alert=Kiril-from-service, bicon=1, licon=1, sicon=1, title=Kiril-from-service, collapse_key=do_not_collapse}]
    // Bundle[{custom={"i":"b9629e94-7a13-4d83-942c-c5f2a4a91cef"}, alert=Kiril-from-service2, title=Kiril-from-service2, collapse_key=do_not_collapse}]
    // Bundle[{custom={"i":"7e37b4f4-ff00-4a0d-991c-e02a771aecb4"}, alert=Kiril-from-service, bicon=http://icons.iconarchive.com/icons/artbees/bee-mac/256/iMac-24-ON-icon.png, licon=http://icons.iconarchive.com/icons/designbolts/free-multimedia/256/iMac-icon.png, title=Kiril-from-service, collapse_key=do_not_collapse}]
    // Bundle[{custom={"i":"b4274f18-82e8-400c-95ea-0be0aaa0c562"}, o=[{"p":"","i":"1","n":"but1"},{"p":"","i":"2","n":"but2"}], alert=MessageTestContent, title=MessageTest, collapse_key=do_not_collapse}]

    public static PushMessageModel parsePushMessage(Bundle data) {

        String oldMessage = data.getString("price", null); // Если успех, то пуш послан старым сервисом
        if (oldMessage != null) {

            return new PushMessageModel(
                    "Такси",
                    oldMessage,
                    Color.BLACK,
                    Color.WHITE,
                    null,
                    null,
                    null);

        } else {

            String title        = data.getString("title", "Такси");
            String message      = data.getString("alert", null);
            int    accentColor  = data.getInt(   "ledc", Color.BLACK);
            int    bgColor      = data.getInt(   "bgac", Color.WHITE);
            String bigIcon      = data.getString("bicon", null);
            String bigIconUrl   = bigIcon != null && (bigIcon.contains("https://") || bigIcon.contains("http://")) ? bigIcon : null;
            String largeIcon    = data.getString("licon");
            String largeIconUrl = largeIcon != null && (largeIcon.contains("https://") || largeIcon.contains("http://")) ? largeIcon : null;

            return new PushMessageModel(
                    title,
                    message,
                    accentColor,
                    bgColor,
                    largeIconUrl,
                    bigIconUrl,
                    null);

        }

    }

}
