package ru.startandroid.vk_client;

/**
 * Created by toxat on 06.07.2018.
 */

public class PagePresenter<View, Model> {

    private View v;
    private Model m;

    void setView(View v)
    {
        this.v = v;
    }
    View getView(){return v;}

    void setModel(Model m)
    {
        this.m = m;
    }
    Model getModel(){return m;}
}
