package ru.startandroid.vk_client.Presenter;

import android.support.annotation.IntegerRes;
import android.text.TextUtils;
import android.util.Log;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import ru.startandroid.vk_client.Model.FriendListModel;
import ru.startandroid.vk_client.Model.FriendModel;
import ru.startandroid.vk_client.View.FriendListView;


public class FriendListPresenter extends ListPresenter<FriendListView, FriendListModel>{

    public VKRequest req;
    ArrayList<FriendModel> resultArr;

    public FriendListPresenter(FriendListView view)
    {
        setView(view);
        String userID = v.getIntent().getStringExtra("id");
        Log.d("FUCK", userID == null ? "null" : userID);
        if(TextUtils.isEmpty(userID)) {
            req = new VKRequest("friends.get",
                    VKParameters.from(
                            VKApiConst.FIELDS, "first_name, last_name, photo_100")
            );
            Log.d("FUCK", "user friends is here");
        }
        else {
            req = new VKRequest("friends.get",
                    VKParameters.from(
                            VKApiConst.USER_ID, userID,
                            VKApiConst.FIELDS, "first_name, last_name, photo_100")
            );
            Log.d("FUCK", "not user friends is here");
        }
        ArrayList<FriendModel> listForModel = fillList();
        setModel(new FriendListModel(listForModel));
    }

    public ArrayList<FriendModel> fillList()
    {
        resultArr = new ArrayList<>();
        req.executeSyncWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                v.ShowProgress();
                super.onComplete(response);
                JSONObject body = response.json.optJSONObject("response");
                if(body != null)
                {
                    Log.d("FUCK", "Friends body here" + body.toString());
                    int count = body.optInt("count");
                    JSONArray items = body.optJSONArray("items");
                    if(items != null)
                    {
                        Log.d("FUCK", "Friends items here" + items.toString());
                        for(int i = 0 ; i < count ; i ++)
                        {
                            JSONObject friend = items.optJSONObject(i);
                            if(friend != null)
                            {
                                Integer id = friend.optInt("id");
                                String fname = friend.optString("first_name");
                                String lname = friend.optString("last_name");
                                String photo = friend.optString("photo_100");
                                FriendModel fm = new FriendModel(id.toString(), fname, lname, photo);
                                resultArr.add(fm);
                            }
                        }

                    }
                }
                v.HideProgress();
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }

            @Override
            public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
                super.onProgress(progressType, bytesLoaded, bytesTotal);

            }
        });
        return resultArr;
    }

    public String getID(int index){
        return m.getFromList(index).getId();
    }
}
