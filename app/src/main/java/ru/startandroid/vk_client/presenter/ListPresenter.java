package ru.startandroid.vk_client.presenter;

/**
 * Created by toxat on 08.07.2018.
 */

public class ListPresenter<View, Model> {

    protected View v;
    protected Model m;

    public void setModel(Model m)
    {
        this.m = m;
    }
    public Model getModel()
    {
        return m;
    }

    public void setView(View v)
    {
        this.v = v;
    }
    public View getView()
    {
        return v;
    }

}
