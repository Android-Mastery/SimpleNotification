/*
 * Copyright (c) 2018.
 * Gilang Ramadhan (gilang@imastudio.co.id)
 */

package com.androidmastery.simplenotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.androidmastery.simplenotification.NoficationBroadcastReceiver.KEY_MESSAGE_ID;
import static com.androidmastery.simplenotification.NoficationBroadcastReceiver.KEY_NOTIFICATION_ID;

public class ReplaytActivity extends AppCompatActivity {
    private int mMessageId;
    private int mNotifyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replayt);

        Intent intent = getIntent();
        if (intent != null) {
            mNotifyId = intent.getIntExtra(KEY_NOTIFICATION_ID, 0);
            mMessageId = intent.getIntExtra(KEY_MESSAGE_ID, 0);
        }

        final EditText input = findViewById(R.id.balas);
        Button submit = findViewById(R.id.btnSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(mNotifyId, mMessageId, input.getText().toString());
            }
        });
    }

    private void sendMessage(int mNotifyId, int mMessageId, String s) {
        Toast.makeText(this, "Message ID : " + mMessageId+"\nMessage :" + s, Toast.LENGTH_SHORT).show();

        NoficationBroadcastReceiver.updateNotification(this, mNotifyId, s);
    }
}
