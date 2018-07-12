package ru.startandroid.vk_client.view;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import ru.startandroid.vk_client.Adapters.UserAdapter;
import ru.startandroid.vk_client.R;
import ru.startandroid.vk_client.VK_app;
import ru.startandroid.vk_client.presenter.FollowerListPresenter;


public class FollowerListView extends listView{
    FollowerListPresenter presenter;
    RecyclerView FollowerList;
    UserAdapter ua;

    VK_app app;
    String TAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (VK_app) getApplicationContext();
        TAG = app.TAG;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.follower_list_view);
        presenter = new FollowerListPresenter(this);
        initRecyclerView();
        initActionBar();

    }

    private UserAdapter.OnUserClickListener initListener()
    {
        //создается слушатель кликов
        return new UserAdapter.OnUserClickListener() {
            @Override
            //реализующий определенные действия по клику
            public void onUserClick(String id) {
                Toast.makeText(getApplicationContext(),
                        "user " + id,
                        Toast.LENGTH_SHORT).show();
                presenter.onItemClick(id);
            }
        };
    }
    private void initRecyclerView() {
        FollowerList = findViewById(R.id.FriendList);
        FollowerList.setLayoutManager(new LinearLayoutManager(this));
        ua = presenter.getAdapter();
        ua.setItems(presenter.getFollowerList());
        ua.setCount();
        ua.setOnUserClickListener(initListener());
        FollowerList.setAdapter(ua);
    }
    private void initActionBar()
    {
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            Log.d(TAG, "setup action bar's tab");
            bar.setTitle(presenter.getBarTitle());
        }
    }



    @Override
    public void ShowProgress() {

    }

    @Override
    public void HideProgress() {

    }
}
