package ru.startandroid.vk_client.view;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.Toast;

import ru.startandroid.vk_client.Adapters.UserAdapter;
import ru.startandroid.vk_client.Adapters.UserAdapter.OnUserClickListener;
import ru.startandroid.vk_client.VK_app;
import ru.startandroid.vk_client.presenter.FriendListPresenter;
import ru.startandroid.vk_client.R;

public class FriendListView extends listView {
    FriendListPresenter presenter;
    RecyclerView FriendList;
    UserAdapter ua;

    VK_app app;
    String TAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (VK_app) getApplicationContext();
        TAG = app.TAG;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_list_view);
        presenter = new FriendListPresenter(this);
        initRecyclerView();
        initActionBar();

    }

    private OnUserClickListener initListener()
    {
        //создается слушатель кликов
        return new OnUserClickListener() {
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
        FriendList = findViewById(R.id.FriendList);
        FriendList.setLayoutManager(new LinearLayoutManager(this));
        ua = presenter.getAdapter();
        ua.setItems(presenter.getFriendList());
        ua.setCount();
        ua.setOnUserClickListener(initListener());
        FriendList.setAdapter(ua);
    }
    private void initActionBar()
    {
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            Log.d(TAG, "setup action bar's tab");
            bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

            Tab tab = bar.newTab();
            tab.setText(presenter.getFriendTabTitle());
            tab.setTabListener(new ActionBar.TabListener() {
                @Override
                public void onTabSelected(Tab tab, FragmentTransaction ft) {
                    ua.clearItems();
                    ua.setItems(presenter.getFriendList());
                    ua.setCount();
                    ua.notifyDataSetChanged();
                    Log.d(TAG, String.valueOf(ua.getItemCount()));
                }

                @Override
                public void onTabUnselected(Tab tab, FragmentTransaction ft) {
                    ua.clearItems();
                    ua.notifyDataSetChanged();
                }

                @Override
                public void onTabReselected(Tab tab, FragmentTransaction ft) {

                }
            });
            bar.addTab(tab);

            tab = bar.newTab();
            tab.setText(presenter.getOnlineTabTitle());
            tab.setTabListener(new ActionBar.TabListener() {
                @Override
                public void onTabSelected(Tab tab, FragmentTransaction ft) {
                    ua.setItems(presenter.getFriendList_online());
                    ua.setCount();
                    ua.notifyDataSetChanged();
                }

                @Override
                public void onTabUnselected(Tab tab, FragmentTransaction ft) {
                    ua.clearItems();
                    ua.notifyDataSetChanged();
                }

                @Override
                public void onTabReselected(Tab tab, FragmentTransaction ft) {

                }
            });
            bar.addTab(tab);

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
