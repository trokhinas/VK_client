package ru.startandroid.vk_client.Presenter;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import ru.startandroid.vk_client.DownloadClass.DownloadImageTask;
import ru.startandroid.vk_client.Model.UserPageModel;
import ru.startandroid.vk_client.View.UserPageView;


public class UserPagePresenter extends PagePresenter<UserPageView, UserPageModel> {

    VKRequest req = VKApi.users().
            get(VKParameters.from
                    (VKApiConst.FIELDS, "first_name, last_name, online, city, photo_100, counters"));

    //нужно сделать правильный реквест чтобы брать инфу по любому юзеру


    public UserPagePresenter(UserPageView view)
    {
        setView(view);
        setModel(new UserPageModel());
        fillModel();
    }

    public void fillModel()
    {
        Log.d("FUCK", "request is processed");
        req.executeSyncWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                JSONArray res = response.json.optJSONArray("response");
                Log.d("FUCK", "there's your request" + res.toString());
                JSONObject body = res.optJSONObject(0);
                if(body != null)
                {
                    m.setId(body.optInt("id"));
                    Log.d("FUCK", m.getId() + " in execute");
                    m.setFname(body.optString("first_name"));
                    Log.d("FUCK", m.getFname() + " in execute");
                    m.setLname(body.optString("last_name"));
                    m.setOnline(body.optInt("online") == 0 ? "offline" : "online");
                    m.setPhotoUrl(body.optString("photo_100"));

                    JSONObject counters = body.optJSONObject("counters");
                    if(counters != null)
                    {
                        m.setFriends(counters.optInt("friends"));
                        m.setFollowers(counters.optInt("followers"));
                    }
                    else
                    {
                        Log.d("FUCK", "request error: counter = null");
                    }
                    JSONObject city = body.optJSONObject("city");
                    if(city != null)
                    {
                        m.setCity(city.optString("title"));
                    }
                    else
                    {
                        Log.d("FUCK", "request error: city = null");
                    }
                }
                else
                    Log.d("FUCK", "request error: body = null");


            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                Log.d("FUCC", "request error");
            }
        });
    }


    @Override
    public String getUserName() {
        return m.getFname() + " " + m.getLname();
    }
    @Override
    public String getOnline() {
        return m.getOnline();
    }
    @Override
    public String getCity() {
        return m.getCity();
    }
    @Override
    public void getPhoto(ImageView iv) {
        new DownloadImageTask(iv).execute(m.getUrlPhoto());
    }
    @Override
    public String getFriends() {
        return "Friends:" + m.getFriends();
    }
    @Override
    public String getFollowers() {
        return "Followers:" + m.getFollowers();
    }
}
