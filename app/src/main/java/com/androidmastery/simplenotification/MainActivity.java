/*
 * Copyright (c) 2018.
 * Gilang Ramadhan (gilang@imastudio.co.id)
 */

package com.androidmastery.simplenotification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });
    }
    private void showNotification(){
        startService(new Intent(this, NotificationService.class));
    }
}
