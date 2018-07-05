package ru.startandroid.vk_client;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;


public class VK_app extends Application {
    boolean is_logged = false;
    /*VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            if (newToken == null) {
                // VKAccessToken is invalid
            }
        }
    };*/


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("FUCK", "Сначала вызывается апликатион");
        Log.d("FUCK", this.getClass().toString() + " created");
        VKSdk.initialize(this);
        check_Authorization();


    }

    void check_Authorization()
    {
        is_logged = VKSdk.isLoggedIn();
        Intent nextView;
        if(is_logged) {
            Log.d("FUCK", "user is logged in");
            nextView = new Intent(this, UserPageView.class);
        }
        else {
            Log.d("FUCK", "user is not logged in");
            nextView = new Intent(this, LoginView.class);
        }
        startActivity(nextView);
    }

}
