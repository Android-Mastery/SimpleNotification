/*
 * Copyright (c) 2018.
 * Gilang Ramadhan (gilang@imastudio.co.id)
 */

package com.androidmastery.simplenotification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import static com.androidmastery.simplenotification.NotificationService.REPLAY_ACTION;

public class NoficationBroadcastReceiver extends BroadcastReceiver{
    public static String KEY_NOTIFICATION_ID = "key_notification_id";
    public static String KEY_MESSAGE_ID= "key_message_id";

    public static Intent getReplyMessageIntent(Context context, int mNotifyId, int mMessageId) {
        Intent intent = new Intent(context, ReplaytActivity.class);
        intent.setAction(REPLAY_ACTION);
        intent.putExtra(KEY_NOTIFICATION_ID, mNotifyId);
        intent.putExtra(KEY_MESSAGE_ID, mMessageId);
        return intent;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (REPLAY_ACTION.equals(intent.getAction())){
            CharSequence message = NotificationService.getReplayMessage(intent);
            int messageId = intent.getIntExtra(KEY_MESSAGE_ID, 0);
            Toast.makeText(context, "Message ID : " + messageId+"\nMessage :" + message, Toast.LENGTH_SHORT).show();

            int notifyID = intent.getIntExtra(KEY_NOTIFICATION_ID, 1);
            updateNotification(context, notifyID, "Ini adalah Teks Balasan");
        }
    }

    public static void updateNotification(Context context, int notifyID, String message) {
        NotificationManagerCompat mNotificationManagerCompat = NotificationManagerCompat.from(context);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(message)
                .setContentTitle("Ini adalah judul Balasan");

        mNotificationManagerCompat.notify(notifyID, mBuilder.build());
    }
}
