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
                    enter();
            }
        }
    };*/


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("FUCK", "Сначала вызывается апликатион");
        Log.d("FUCK", this.getClass().toString() + " created");
        VKSdk.initialize(this);
        //vkAccessTokenTracker.startTracking();
        check_Authorization();


    }

    void check_Authorization()
    {
        is_logged = VKSdk.isLoggedIn();
        Intent nextView;
        if(is_logged)
            nextView = enter();
        else
            nextView = authorization();
        startActivity(nextView);
    }

    Intent enter()
    {
        Log.d("FUCK", "user is logged in");
        return new Intent(this, UserPageView.class);
    }
    Intent authorization()
    {
        Log.d("FUCK", "user is not logged in");
        return new Intent(this, LoginView.class);
    }

}
