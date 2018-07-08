package ru.startandroid.vk_client.View;

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

    public abstract void setUserName();
    public abstract void setOnline();
    public abstract void setCity();

    public abstract void setPhoto();

    public abstract void setFriends();
    public abstract void setFollowers();

    abstract void initPage();


}
