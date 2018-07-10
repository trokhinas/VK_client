package ru.startandroid.vk_client.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import ru.startandroid.vk_client.Adapters.UserAdapter;
import ru.startandroid.vk_client.R;
import ru.startandroid.vk_client.presenter.FollowerListPresenter;
import ru.startandroid.vk_client.presenter.FriendListPresenter;

public class FollowerListView extends listView{
    FollowerListPresenter presenter;
    RecyclerView FollowerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.follower_list_view);
        presenter = new FollowerListPresenter(this);
        initRecyclerView();

    }

    private void initRecyclerView() {
        FollowerList = findViewById(R.id.FriendList);
        FollowerList.setLayoutManager(new LinearLayoutManager(this));
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
        FollowerList.setAdapter(ua);
    }



    @Override
    public void ShowProgress() {

    }

    @Override
    public void HideProgress() {

    }
}
