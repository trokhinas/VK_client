package ru.startandroid.vk_client;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * базовый класс для страницы
 */

public abstract class PageView extends AppCompatActivity{
    TextView tvUsername, tvOnline, tvCity;
    ImageView ivPhoto;
    Button btnFriend, btnFollowers;

    abstract void setUserName(String userName);
    abstract void setOnline(String online);
    abstract void setCity(String city);

    abstract void setPhoto(ImageView photo);

    abstract void setFriends(String friends);
    abstract void setFollowers(String followers);




}
