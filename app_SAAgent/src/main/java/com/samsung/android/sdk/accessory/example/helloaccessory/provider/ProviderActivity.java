package com.samsung.android.sdk.accessory.example.helloaccessory.provider;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Console;

public class ProviderActivity extends Activity {
    private ProviderService mProviderService = null;
    private boolean mIsBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIsBound = bindService(new Intent(ProviderActivity.this, ProviderService.class), mConnection, Context.BIND_AUTO_CREATE);

        Button button1 = findViewById(R.id.Button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProviderService.sendMessage();
            }
        });

    }

    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            mProviderService = ((ProviderService.LocalBinder) service).getService();
            Log.d("helloaccessory", "service connected!");
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            mProviderService = null;
            mIsBound = false;
            Log.d("helloaccessory", "onServiceDisconnected");
        }
    };
}
