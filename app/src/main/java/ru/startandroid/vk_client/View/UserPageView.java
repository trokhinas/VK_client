package ru.startandroid.vk_client.View;

import android.os.Bundle;
import android.util.Log;
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
        setContentView(R.layout.user_friend_page);
        //разобраться с тем какой лэйаут отображать в зависимости от того юзер это или нет
        Log.d("FUCK", this.getClass().toString() + " created");
        initUserPage();
        Log.d("FUCK", "presenter filled");
    }

    private void initUserPage() {
        initPage();
        Log.d("FUCK", "presenter initialised");
        presenter = new UserPagePresenter(this);
        setUserName();
        setOnline();
        setCity();
        setPhoto(ivPhoto);
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
    public void setPhoto(ImageView imageView) {
        presenter.getPhoto(imageView);
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
