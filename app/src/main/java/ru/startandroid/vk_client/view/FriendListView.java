package ru.startandroid.vk_client.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ru.startandroid.vk_client.Adapters.UserAdapter;
import ru.startandroid.vk_client.presenter.FriendListPresenter;
import ru.startandroid.vk_client.R;

public class FriendListView extends listView {
    FriendListPresenter presenter;
    RecyclerView FriendList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_list_view);
        FriendList = (RecyclerView) findViewById(R.id.FriendList);
        FriendList.setLayoutManager(new LinearLayoutManager(this));
        presenter = new FriendListPresenter(this);
        UserAdapter ua = presenter.getAdapter();
        FriendList.setAdapter(ua);

    }


    @Override
    public void ShowProgress() {

    }

    @Override
    public void HideProgress() {

    }
}
