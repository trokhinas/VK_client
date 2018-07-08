package ru.startandroid.vk_client.View;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ru.startandroid.vk_client.R;
import ru.startandroid.vk_client.Presenter.UserPagePresenter;

public class UserPageView extends PageView {

    UserPagePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*костылюга*/
        Intent i = getIntent();
        String userID = i.getStringExtra("id");
        Log.d("FUCK", userID == null ? "null" : userID);
        if(TextUtils.isEmpty(userID))
            setContentView(R.layout.user_page);
        else
            setContentView(R.layout.user_friend_page);
        //разобраться с тем какой лэйаут отображать в зависимости от того юзер это или нет
        initUserPage();
    }

    private void initUserPage() {
        initPage();
        Log.d("FUCK", "presenter initialised");
        presenter = new UserPagePresenter(this);
        setUserName();
        setOnline();
        setCity();
        setPhoto();
        setFollowers();
        setFriends();
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
        tvCity.setText(presenter.getCity());
    }
    @Override
    public void setPhoto() {
        presenter.getPhoto(ivPhoto);
    }
    @Override
    public void setFriends() {
        btnFriend.setText(presenter.getFriends());
    }
    @Override
    public void setFollowers() {
        btnFollowers.setText(presenter.getFollowers());
    }


}
