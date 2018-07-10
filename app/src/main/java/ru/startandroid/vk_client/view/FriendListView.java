package ru.startandroid.vk_client.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import ru.startandroid.vk_client.Adapters.UserAdapter;
import ru.startandroid.vk_client.model.UserModel;
import ru.startandroid.vk_client.presenter.FriendListPresenter;
import ru.startandroid.vk_client.R;

public class FriendListView extends listView {
    FriendListPresenter presenter;
    RecyclerView FriendList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_list_view);
        presenter = new FriendListPresenter(this);
        initRecyclerView();


    }

    private void initRecyclerView() {

        FriendList = (RecyclerView) findViewById(R.id.FriendList);
        FriendList.setLayoutManager(new LinearLayoutManager(this));
        //создается слушатель кликов
        UserAdapter.OnUserClickListener onUserClickListener = new UserAdapter.OnUserClickListener() {
            @Override
            //реализующий определенные действия по клику
            public void onUserClick(String id) {
                Toast.makeText(getApplicationContext(),
                        "user " + id,
                        Toast.LENGTH_SHORT).show();
                presenter.onItemClick(id);
            }
        };
        UserAdapter ua = presenter.getAdapter();
        ua.setOnUserClickListener(onUserClickListener);
        FriendList.setAdapter(ua);
    }


    @Override
    public void ShowProgress() {

    }

    @Override
    public void HideProgress() {

    }
}
