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

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.util.VKUtil;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnEnter;
    VKAccessToken act;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnEnter = (Button) findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(this);
        String[] scopeArr = {"friends"};
        //VKSdk.login(this,scopeArr);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btnEnter:
                String[] scopeArr = {"friends"};
                VKSdk.login(this,scopeArr);
                Intent go = new Intent(this, Screen.class);
                if (act != null) act.saveTokenToSharedPreferences(this.getApplicationContext(), "ACCESS_TOKEN");
                startActivity(go);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // Пользователь успешно авторизовался
                act = res;
            }
            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
