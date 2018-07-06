package ru.startandroid.vk_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKList;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserPageView extends PageView {

    UserPagePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);
        Log.d("FUCK", this.getClass().toString() + " created");
        initUserPage();

        presenter.fillModel();
        presenter.msg();
        Log.d("FUCK", "presenter filled");
    }

    private void initUserPage() {
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvOnline = (TextView) findViewById(R.id.tvOnline);
        tvCity = (TextView) findViewById(R.id.tvCity);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);

        Log.d("FUCK", "presenter initialised");
        presenter = new UserPagePresenter(this);
    }

    @Override
    void setUserName(String userName) {
        tvUsername.setText(userName);
    }
    @Override
    void setOnline(String online) {
        tvOnline.setText(online);
    }
    @Override
    void setCity(String city) {
        tvCity.setText(city);
    }
    @Override
    void setPhoto(ImageView photo) {
        ivPhoto = photo;
    }
    @Override
    void setFriends(String friends) {
        btnFriend.setText(friends);
    }
    @Override
    void setFollowers(String followers) {
        btnFollowers.setText(followers);
    }
}
