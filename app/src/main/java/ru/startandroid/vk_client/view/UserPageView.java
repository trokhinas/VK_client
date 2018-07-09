package ru.startandroid.vk_client.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import ru.startandroid.vk_client.R;
import ru.startandroid.vk_client.VK_app;
import ru.startandroid.vk_client.presenter.UserPagePresenter;

public class UserPageView extends PageView {
    UserPagePresenter presenter;
    VK_app app;
    String TAG;
    public enum layoutState {
        me,
        i_follower,
        my_follower,
        friend,
        not_friend;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);
        Log.d(TAG, "presenter initialising");
        presenter = new UserPagePresenter(this);
        setLayout();
        initUserPage();
    }

    private void setLayout() {
        switch (presenter.getLayout())
        {
            case 0:
            {
                Toast.makeText(this, "It's my layout", Toast.LENGTH_SHORT).show();
                break;
            }
            case 1:
            {
                Toast.makeText(this, "It's i followr layout", Toast.LENGTH_SHORT).show();
                break;
            }
            case 2:
            {
                Toast.makeText(this, "It's my followr layout", Toast.LENGTH_SHORT).show();
                break;
            }
            case 3:
            {
                Toast.makeText(this, "It's i my friend layout", Toast.LENGTH_SHORT).show();
                break;
            }
            case 4:
            {
                Toast.makeText(this, "It's i not my friend layout", Toast.LENGTH_SHORT).show();
                break;
            }

        }
    }

    private void initUserPage() {
        initPage();

        setUserName();setOnline();setCity();
        setPhoto();setFollowers();setFriends();
    }
    @Override
    void initPage() {
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvOnline = (TextView) findViewById(R.id.tvOnline);
        tvCity = (TextView) findViewById(R.id.tvCity);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
        btnFriend = (Button) findViewById(R.id.btnFriends);
        btnFollowers = (Button) findViewById(R.id.btnFollowers);

        btnFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.friendsListener();
            }
        });
        btnFollowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.followersListener();
            }
        });
    }

    @Override
    public void setUserName() {
        tvUsername.setText(presenter.getUserName());
    }
    @Override
    public void setOnline() {
        tvOnline.setText(presenter.getOnline());
    }
    @Override
    public void setCity() {
        tvCity.setText(presenter.getCityTitle());
    }
    @Override
    public void setPhoto() {
        presenter.getPhoto(ivPhoto);
    }
    @Override
    public void setFriends() {
        btnFriend.setText("Friends:" + presenter.getFriendsCounter());
    }
    @Override
    public void setFollowers() {
        btnFollowers.setText("Followers:" + presenter.getFollowersCounter());
    }

}
