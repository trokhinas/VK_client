package ru.startandroid.vk_client.presenter;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import ru.startandroid.vk_client.R;
import ru.startandroid.vk_client.VK_app;
import ru.startandroid.vk_client.model.UserGetRequestResult;
import ru.startandroid.vk_client.model.UserPageModel;
import ru.startandroid.vk_client.view.FollowerListView;
import ru.startandroid.vk_client.view.FriendListView;
import ru.startandroid.vk_client.view.UserPageView;


public class UserPagePresenter extends PagePresenter<UserPageView, UserPageModel> {
    String TAG;
    VK_app app;

    public UserPagePresenter(UserPageView view)
    {
        setView(view);
        app = (VK_app) getView().getApplicationContext();
        this.TAG = app.TAG;
        String userID = getView().getIntent().getStringExtra("id");
        doRequest(userID);
    }

    private void doRequest(String id) {
        final VKRequest req = new VKRequest(
                "users.get",
                VKParameters.from
                        (VKApiConst.USER_ID , id,
                                VKApiConst.FIELDS, app.getUserRequsetParams())
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
    public void userListener(View v) {
        switch (v.getId())
        {
            case R.id.btnFollowers: {
                onFriendClick();
                break;
            }
            case R.id.btnFriends: {
                onFolloweClick();
            }
        }

    }

    public void onFriendClick() {
        Intent i = new Intent(v.getApplicationContext(), FriendListView.class);
        i.putExtra("id", m.getId().toString());
        getView().startActivity(i);
    }
    public void onFolloweClick() {
        Intent i = new Intent(v.getApplicationContext(), FollowerListView.class);
        i.putExtra("id", m.getId().toString());
        getView().startActivity(i);
    }
    public int getLayout() {
        Integer res = 0;
        switch (m.getFriendStatus())
        {
            case 0:
            {
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
