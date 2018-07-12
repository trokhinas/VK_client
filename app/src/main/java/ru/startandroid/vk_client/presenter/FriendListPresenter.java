package ru.startandroid.vk_client.presenter;

import android.content.Intent;
import android.util.Log;


import com.google.gson.Gson;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.startandroid.vk_client.Adapters.UserAdapter;
import ru.startandroid.vk_client.VK_app;
import ru.startandroid.vk_client.model.FriendListModel;
import ru.startandroid.vk_client.model.FriendsGetRequestResult;
import ru.startandroid.vk_client.model.UserGetRequestResult;
import ru.startandroid.vk_client.model.UserModel;
import ru.startandroid.vk_client.model.UserPageModel;
import ru.startandroid.vk_client.view.FriendListView;
import ru.startandroid.vk_client.view.UserPageView;


public class FriendListPresenter extends ListPresenter<FriendListView, FriendListModel>{
    String TAG;
    VK_app app;
    UserPageModel user;

    public FriendListPresenter(FriendListView view)
    {
        setView(view);
        app = (VK_app) getView().getApplicationContext();
        this.TAG = app.TAG;
        String userID = getView().getIntent().getStringExtra("id");
        Log.d(TAG, "id from extra " + userID);
        doRequest(userID);

    }

    private void doRequest(String id) {
        VKRequest req = new VKRequest(
                "friends.get",
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

    public UserAdapter getAdapter() {
        UserAdapter res = new UserAdapter();
        return res;
    }
    public List<UserModel> getFriendList()
    {
        return m.getItems();
    }
    public List<UserModel> getFriendList_online()
    {
        ArrayList<UserModel> res = new ArrayList<>();
        List<UserModel> src = getFriendList();
        for(int i = 0, k = 0; i < src.size() ; i ++)
            if(src.get(i).getOnline() == 1)
                res.add(src.get(i));
        Log.d(TAG, String.valueOf(res.size()));
        return res;
    }
    public String getFriendTabTitle()
    {
        String res = user.getCounters().getFriends().toString();
        if(res != null)
            return "Friends:" + res;
        else
            return "Friends:";
    }
    public String getOnlineTabTitle()
    {
        String res = user.getCounters().getOnlineFriends().toString();
        if(res != null)
            return "Online:" + res;
        else
            return "Online:";
    }
    public String getBarTitle()
    {
        String userID = String.valueOf(app.getUserID());
        String userID_list = user.getId().toString();
        if(userID.equals(userID_list))
        {
            return "Friends";
        }
        else
        {
            return user.getFirstName() + "'s "  + " Friends";
        }
    }
    public void onItemClick(String id)
    {
        Intent i = new Intent(getView().getApplicationContext(), UserPageView.class);
        i.putExtra("id", id);
        getView().startActivity(i);
    }
}
