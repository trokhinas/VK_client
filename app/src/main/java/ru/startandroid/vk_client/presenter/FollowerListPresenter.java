package ru.startandroid.vk_client.presenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import java.util.List;

import ru.startandroid.vk_client.Adapters.UserAdapter;
import ru.startandroid.vk_client.VK_app;
import ru.startandroid.vk_client.model.FriendListModel;
import ru.startandroid.vk_client.model.FriendsGetRequestResult;
import ru.startandroid.vk_client.model.UserGetRequestResult;
import ru.startandroid.vk_client.model.UserModel;
import ru.startandroid.vk_client.model.UserPageModel;
import ru.startandroid.vk_client.view.FollowerListView;
import ru.startandroid.vk_client.view.UserPageView;


public class FollowerListPresenter extends ListPresenter<FollowerListView, FriendListModel>{
    String TAG;
    VK_app app;
    UserPageModel user;

    public FollowerListPresenter(FollowerListView view)
    {
        setView(view);
        app = (VK_app) getView().getApplicationContext();
        this.TAG = app.TAG;
        String userID = v.getIntent().getStringExtra("id");
        Log.d(TAG, "id from extra " + userID);
        doRequest(userID);

    }

    private void doRequest(String id) {
        VKRequest req = new VKRequest(
                "users.getFollowers",
                VKParameters.from
                        (VKApiConst.USER_ID, id,
                                VKApiConst.FIELDS, app.getFriendListRequestParams())
        );
        req.executeSyncWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                Gson gson = new Gson();
                FriendsGetRequestResult res = gson.fromJson(response.json.toString(), FriendsGetRequestResult.class);
                Log.d(TAG, "here request " + response.json.toString());
                setModel(new FriendListModel(res.getResponse().getCount(), res.getResponse().getItems()));
                Log.d(TAG, "here request " + m.getCount());
            }
        });
        req = new VKRequest(
                "users.get",
                VKParameters.from
                        (VKApiConst.USER_ID , id,
                                VKApiConst.FIELDS, app.getUserRequsetParams())
        );
        req.executeSyncWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                Gson gson = new Gson();
                UserGetRequestResult res = gson.fromJson(response.json.toString(), UserGetRequestResult.class);
                user = res.getResponse().get(0);
            }
        });
    }


    public String getID(int index){
        return m.getItems().get(index).getId().toString();
    }

    public UserAdapter getAdapter() {
        UserAdapter res = new UserAdapter();
        res.setItems(m.getItems());
        res.setCount(m.getCount());
        return res;
    }


    public void onItemClick(String id) {
        Intent i = new Intent(getView().getApplicationContext(), UserPageView.class);
        i.putExtra("id", id);
        getView().startActivity(i);
    }


    public List<UserModel> getFollowerList() {
        return m.getItems();
    }

    public String getBarTitle() {
        String userID = String.valueOf(app.getUserID());
        String userID_list = user.getId().toString();
        if(userID.equals(userID_list))
        {
            return "Followers";
        }
        else
        {
            return user.getFirstName() + "'s "  + " Followers";
        }
    }
}
