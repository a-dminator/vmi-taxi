package ru.napoleonit.push;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.gcm.GcmListenerService;

import java.util.concurrent.atomic.AtomicInteger;

import ru.napoleonit.push.model.PushMessageModel;

import static ru.napoleonit.push.model.parse.PushModelParser.parsePushMessage;
import static ru.napoleonit.push.notification.PushNotificationFabric.createPushNotification;

public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";

    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    @Override public void onMessageReceived(String from, Bundle data) {
        PushMessageModel messageModel = parsePushMessage(data);
        sendNotification(messageModel);
    }

    private final AtomicInteger mId = new AtomicInteger(0);
    private void sendNotification(PushMessageModel model) {

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = createPushNotification(model, this);

        notificationManager.notify(mId.getAndIncrement(), notification);
    }

}