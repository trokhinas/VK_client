package ru.startandroid.vk_client;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.util.VKUtil;

import java.util.Arrays;

public class LoginView extends AppCompatActivity implements View.OnClickListener {

    Button btnEnter;
    TextView tvAppName, tvWarning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("FUCK", this.getClass().toString() + " created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("FUCK", this.getClass().toString() + " resumed");
    }

    private void init() {
        findElements();
        initListeners();
    }
    private void initListeners() {
        btnEnter.setOnClickListener(this);
    }
    private void findElements() {
        btnEnter = (Button) findViewById(R.id.btnEnter);
        tvAppName = (TextView) findViewById(R.id.tvAppName);
        tvWarning = (TextView) findViewById(R.id.tvWarning);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnEnter:
            {
                String scope[] = {"friends"};
                VKSdk.login(this, scope);
            }

        }
    }
}