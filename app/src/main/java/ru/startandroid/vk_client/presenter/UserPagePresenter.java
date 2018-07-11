package ru.startandroid.vk_client.presenter;


import android.content.Intent;
import android.text.TextUtils;
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
import ru.startandroid.vk_client.model.City;
import ru.startandroid.vk_client.model.UserGetRequestResult;
import ru.startandroid.vk_client.model.UserPageModel;
import ru.startandroid.vk_client.view.FollowerListView;
import ru.startandroid.vk_client.view.FriendListView;
import ru.startandroid.vk_client.view.UserPageView;


public class UserPagePresenter extends PagePresenter<UserPageView, UserPageModel> {
    private String TAG;
    private VK_app app;

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
                setModel(res.getResponse().get(0));
            }
        });
    }


    @Override
    public String getUserName() {
        return m.getFirstName() + " " + m.getLastName();
    }
    @Override
    public String getOnline() {
        Integer online = m.getOnline();
        if(online != null)
            return m.getOnline() == 1 ? "online" : "offline";
        else
            return null;
    }
    @Override
    public String getCityTitle() {
        if(m.getCity() == null)
            return null;
        String city = m.getCity().getTitle();
        if(!TextUtils.isEmpty(city))
            return city;
        else
            return null;
    }
    @Override
    public void getPhoto(ImageView iv) {
        String photoUrl = m.getPhoto100();
        if(!TextUtils.isEmpty(photoUrl))
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
                onFollowerClick();
                break;
            }
            case R.id.btnFriends: {
                onFriendClick();
                break;
            }
        }

    }

    private void onFriendClick() {
        Intent i = new Intent(v.getApplicationContext(), FriendListView.class);
        i.putExtra("id", m.getId().toString());
        getView().startActivity(i);
    }
    private void onFollowerClick() {
        Intent i = new Intent(v.getApplicationContext(), FollowerListView.class);
        i.putExtra("id", m.getId().toString());
        getView().startActivity(i);
    }
    public int getLayout() {
        Integer res = 0;
        if(m.getDeactivated() != null)//banned
            return -1;
        switch (m.getFriendStatus())
        {
            case 0://user or not friend or banned user
            {

                if(!app.getUserID().equals(m.getId().toString()))//not friend
                    res = 4;
                break;
            }
            case 1://person which you follow
            {
                res = 1;
                break;
            }
            case 2://your follower
            {
                res = 2;
                break;
            }
            case 3://your friend
            {
                res = 3;
                break;
            }
        }
        return res;
    }

    public String getBannedstatus() {
        String bs = m.getDeactivated();
        if(bs.equals("banned"))
            return "User was banned";
        else
            return "User deleted this page";
    }
    public String getFriendStatus() {
        int fs = m.getFriendStatus();
        String returnState = "";
        switch (fs)
        {
            case 1:
            {
                returnState =  "You follow on this user";
                break;
            }
            case 2:
            {
                returnState =  "This user follows on you";
                break;
            }
            case 3:
            {
                returnState =  m.getFirstName() + " " + m.getLastName() + " is your friend";
                break;
            }
            default:
            {
                returnState = null;
            }
        }
        return returnState;
    }
}
