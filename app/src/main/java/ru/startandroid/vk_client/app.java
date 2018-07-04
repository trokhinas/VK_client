package ru.startandroid.vk_client;

import android.app.Application;
import android.content.Intent;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;

/**
 * Created by toxat on 04.07.2018.
 */

public class app extends Application {

    VKAccessToken act;

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);

    }
}
