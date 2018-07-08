package ru.startandroid.vk_client;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.vk.sdk.VKSdk;

import retrofit2.Retrofit;
import ru.startandroid.vk_client.View.FriendListView;
import ru.startandroid.vk_client.View.UserPageView;


public class VK_app extends Application {
    boolean is_logged = false;
    static String defaultUserID = "0";

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
