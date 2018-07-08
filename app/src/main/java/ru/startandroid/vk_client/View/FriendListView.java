package ru.startandroid.vk_client.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ru.startandroid.vk_client.Adapters.FriendAdapter;
import ru.startandroid.vk_client.Model.FriendModel;
import ru.startandroid.vk_client.Presenter.FriendListPresenter;
import ru.startandroid.vk_client.R;

public class FriendListView extends listView {


    /*ArrayList<FriendModel> ar = new ArrayList<>();
    FriendModel fm1 = new FriendModel();
    FriendModel fm2 = new FriendModel();*/

    FriendListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_list_view);
        presenter = new FriendListPresenter(this);

        lv = (ListView) findViewById(R.id.lvFriends);

        FriendAdapter adapter = new FriendAdapter(this, presenter.getModel().getList());
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent friend = new Intent(view.getContext(), UserPageView.class);
                friend.putExtra("id", presenter.getID((int) id));
                startActivity(friend);
            }
        });

    }

    @Override
    public void ShowProgress() {

    }

    @Override
    public void HideProgress() {

    }
}
