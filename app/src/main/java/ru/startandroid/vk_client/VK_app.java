package ru.startandroid.vk_client;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;

import ru.startandroid.vk_client.view.UserPageView;


public class VK_app extends Application {
    boolean is_logged = false;

    String UserRequsetParams = "first_name, last_name, online, city, photo_100, counters, friend_status";
    String FriendListRequestParams = "first_name, last_name, photo_100,";
    String []appScope = {VKScope.FRIENDS};
    String UserID = "0";
    public String TAG = "myTagLog";

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
        check_Authorization();

    }

    private void check_Authorization()
    {
        is_logged = VKSdk.isLoggedIn();
        Intent nextView;
        if(is_logged) {
            nextView = enter();
            setUserID(VKSdk.getAccessToken().userId);
        }
        else
            nextView = authorization();
        nextView.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(nextView);
    }
    private Intent enter()
    {
        Log.d(TAG,"user is logged in");
        return new Intent(this, UserPageView.class);
    }
    private Intent authorization()
    {
        Log.d(TAG, "user is not logged in");
        return new Intent(this, LoginView.class);
    }
    public void setUserID(String id)
    {
        UserID = id;
    }
    public String getUserID()
    {
        return UserID;
    }
    public String getUserRequsetParams()
    {
        return UserRequsetParams;
    }
    public String getFriendListRequestParams()
    {
        return FriendListRequestParams;
    }
    public String []getAppScope()
    {
        return appScope;
    }

}
