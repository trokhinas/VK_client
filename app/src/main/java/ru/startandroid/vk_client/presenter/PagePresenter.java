package ru.startandroid.vk_client.presenter;


import android.view.View;
import android.widget.ImageView;

public abstract class PagePresenter<View, Model> {

    protected View v;
    protected Model m;

    public void setView(View v)
    {
        this.v = v;
    }
    public View getView(){return v;}

    public void setModel(Model m)
    {
        this.m = m;
    }
    public Model getModel(){return m;}

    public abstract String getUserName();
    public abstract String getOnline();
    public abstract String getCityTitle();
    public abstract void getPhoto(ImageView iv);
    public abstract String getFriendsCounter();
    public abstract String getFollowersCounter();


    public abstract void userListener(android.view.View v);
}
