package ru.startandroid.vk_client.presenter;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import ru.startandroid.vk_client.DownloadClass.DownloadImageTask;
import ru.startandroid.vk_client.VK_app;
import ru.startandroid.vk_client.model.UserGetRequestResult;
import ru.startandroid.vk_client.model.UserPageModel;
import ru.startandroid.vk_client.view.FriendListView;
import ru.startandroid.vk_client.view.UserPageView;


public class UserPagePresenter extends PagePresenter<UserPageView, UserPageModel> {
    String TAG;
    VK_app app;
    private Object layout;

    public UserPagePresenter(UserPageView view)
    {
        setView(view);
        app = (VK_app) getView().getApplicationContext();
        this.TAG = app.TAG;
        doRequest();

    }

    private void doRequest() {
        final VKRequest req = new VKRequest(
                "users.get",
                VKParameters.from
                        (VKApiConst.FIELDS, app.getUserRequsetParams())
        );

        req.executeSyncWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                Gson gson = new Gson();
                UserGetRequestResult res = gson.fromJson(response.json.toString(), UserGetRequestResult.class);
                Log.d(TAG, "here request " + response.json.toString());
                Log.d(TAG, "here request " + res.getResponse().get(0).getFirstName());
                setModel(res.getResponse().get(0));
                Log.d(TAG, "here request " + m.getFirstName());
            }
        });
    }


    @Override
    public String getUserName() {
        return m.getFirstName() + m.getLastName();
    }
    @Override
    public String getOnline() {
        return m.getOnline() == 1 ? "online" : "offline";
    }
    @Override
    public String getCityTitle() {
        return m.getCity().getTitle();
    }
    @Override
    public void getPhoto(ImageView iv) {
        Picasso.with(iv.getContext())
                .load(getModel().getPhoto100())
                .into(iv);
    }
    @Override
    public String getFriendsCounter() {
        return m.getCounters().getFriends().toString();
    }
    @Override
    public String getFollowersCounter() {
        return m.getCounters().getFollowers().toString();
    }
    @Override
    public void friendsListener() {
        Toast.makeText(v.getApplicationContext(), "Friends", Toast.LENGTH_SHORT).show();
        getView().startActivity(new Intent(v.getApplicationContext(), FriendListView.class));
    }
    @Override
    public void followersListener() {
        Toast.makeText(v.getApplicationContext(), "Followers", Toast.LENGTH_SHORT).show();
    }


    public int getLayout() {
        Integer res = 0;
        switch (m.getFriendStatus())
        {
            case 0:
            {
                Log.d(TAG, "userId" + app.getUserID());
                Log.d(TAG,"getID" + m.getId().toString());
                if(!app.getUserID().equals(m.getId().toString()))
                    res = 4;
                break;
            }
            case 1:
            {
                res = 1;
                break;
            }
            case 2:
            {
                res = 2;
                break;
            }
            case 3:
            {
                res = 3;
                break;
            }
        }
        return res;
    }
}