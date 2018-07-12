package ru.startandroid.vk_client.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import ru.startandroid.vk_client.R;
import ru.startandroid.vk_client.VK_app;
import ru.startandroid.vk_client.presenter.UserPagePresenter;

public class UserPageView extends PageView {
    UserPagePresenter presenter;
    VK_app app;
    String TAG;
    int layoutState = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (VK_app) getApplicationContext();
        TAG = app.TAG;
        Log.d(TAG, "presenter initialising");
        presenter = new UserPagePresenter(this);
        setLayout();
        initSpecific();
    }

    public void setLayout() {
        switch (presenter.getLayout())
        {
            case -1://banned user
            {
                Log.d(TAG,"It's banned user layout");
                setContentView(R.layout.user_banned_page);
                initMainPageInfo();
                layoutState = -1;
                break;
            }
            case 0://me
            {
                Log.d(TAG,"It's my layout");
                setContentView(R.layout.user_page);
                initUserPage();
                break;
            }
            default://friend or not, follower, and person which you follow
            {
                setContentView(R.layout.user_friend_page);
                layoutState = 1;
                initUserPage();
            }
        }
    }

    public void initSpecific()
    {
        switch (layoutState)
        {
            case -1:{
                TextView tvBannedStatus = findViewById(R.id.tvBannedStatus);
                tvBannedStatus.setText(presenter.getBannedstatus());
                break;
            }
            case 0:{
                Button btnChange = findViewById(R.id.btnChange);
                btnChange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "btn change was clocked", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            }
            case 1:{
                TextView tvFriendStatus = findViewById(R.id.tvFriendStatus);
                tvFriendStatus.setText(presenter.getFriendStatus());
                break;
            }
        }
    }
    private void initUserPage() {
        initMainPageInfo();

        btnFriend =  findViewById(R.id.btnFriends);
        btnFollowers =  findViewById(R.id.btnFollowers);
        btnFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.userListener(v);
            }
        });
        btnFollowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.userListener(v);
            }
        });

        setFollowers();setFriends();
    }
    @Override
    void initMainPageInfo() {
        tvUsername =  findViewById(R.id.tvUsername);
        tvOnline = findViewById(R.id.tvOnline);
        tvCity =  findViewById(R.id.tvCity);
        ivPhoto =  findViewById(R.id.ivPhoto);
        setUserName();setOnline();
        setCity();setPhoto();
    }

    @Override
    public void setUserName() {
        String userName = presenter.getUserName();
        if(!TextUtils.isEmpty(userName))
            tvUsername.setText(presenter.getUserName());
    }
    @Override
    public void setOnline() {
        String online = presenter.getOnline();
        if(!TextUtils.isEmpty(online))
            tvOnline.setText(online);
        else
            tvOnline.setText("");
    }
    @Override
    public void setCity() {
        String city = presenter.getCityTitle();
        if(!TextUtils.isEmpty(city))
            tvCity.setText(city);
        else
            tvCity.setText("");
    }
    @Override
    public void setPhoto() {
        presenter.getPhoto(ivPhoto);
    }
    @Override
    public void setFriends() {
        String friendsNum = presenter.getFriendsCounter();
        if(!TextUtils.isEmpty(friendsNum))
            btnFriend.setText("Friends:" + friendsNum);
    }
    @Override
    public void setFollowers() {
        String followersNum = presenter.getFollowersCounter();
        if(!TextUtils.isEmpty(followersNum))
            btnFollowers.setText("Followers:" + followersNum);
    }

}
