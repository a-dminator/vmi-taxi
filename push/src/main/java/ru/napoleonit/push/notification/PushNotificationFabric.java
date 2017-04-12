package ru.napoleonit.push.notification;

import android.app.Notification;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import ru.napoleonit.push.R;
import ru.napoleonit.push.model.PushMessageModel;

public final class PushNotificationFabric {
    private static final String TAG = "PushNotificationFabric";

    private PushNotificationFabric() {}

    public static Notification createPushNotification(PushMessageModel model, Context context) {

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notificationBuilder
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle(model.title)
                .setContentText(model.message)
                .setColor(model.accentColor)
                .setAutoCancel(true)
                .setSound(defaultSoundUri);

        return notificationBuilder.build();
    }

}
