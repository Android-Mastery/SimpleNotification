/*
 * Copyright (c) 2018.
 * Gilang Ramadhan (gilang@imastudio.co.id)
 */

package com.androidmastery.simplenotification;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;

public class NotificationService extends IntentService {
    public static String REPLAY_ACTION = "com.androidmastery.simplenotification.REPLAY_ACTION";
    private static String KEY_REPLAY = "key_reply_message";

    private int mNotifyId;
    private int mMessageId;

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            showNotification();
        }
    }

    private void showNotification() {
        mNotifyId = 1;
        mMessageId = 123;

        String replayName = "bales sekarang";

        RemoteInput input = new RemoteInput.Builder(KEY_REPLAY)
                .setLabel(replayName)
                .build();

        NotificationCompat.Action replayAction = new NotificationCompat.Action
                .Builder(R.mipmap.ic_launcher, replayName, getPendingIntent())
                .addRemoteInput(input)
                .setAllowGeneratedReplies(true)
                .build();

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Ini adalah kontent")
                .setContentTitle("Ini adalah judul")
                .setShowWhen(true)
                .addAction(replayAction);

        NotificationManagerCompat mNotificationManagerCompat = NotificationManagerCompat.from(this);
        mNotificationManagerCompat.notify(mNotifyId, mBuilder.build());
    }

    private PendingIntent getPendingIntent() {
        Intent intent;
        intent = NoficationBroadcastReceiver.getReplyMessageIntent(this, mNotifyId, mMessageId);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return PendingIntent.getActivity(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static CharSequence getReplayMessage(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput !=null){
            return remoteInput.getCharSequence(KEY_REPLAY);
        }
        return null;
    }
}
