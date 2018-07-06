package ru.startandroid.vk_client;

import android.util.Log;
import android.widget.Toast;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Anton on 05.07.2018.
 */

public class UserPagePresenter extends PagePresenter<UserPageView, UserPageModel> {

    UserPageView v;
    UserPageModel m;

    UserPagePresenter(UserPageView view)
    {
        v = view;
        m = new UserPageModel();
    }

    void fillModel()
    {
        final VKRequest req = VKApi.users().
                get(VKParameters.from
                        (VKApiConst.FIELDS, "first_name, last_name, online, city, photo_100, counters"));
        req.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                JSONArray res = response.json.optJSONArray("response");
                Log.d("FUCC", "there's your request" + res.toString());
                JSONObject body = res.optJSONObject(0);
                if(body != null)
                {
                    m.setId(new Integer(body.optInt("id")));
                    Log.d("FUCK", m.getId() + " in execute");
                    m.setFname(new String(body.optString("first_name")));
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

    public void msg() {
        Log.d("FUCK", m.getId() + "out of execute");
        Log.d("FUCK", m.getFname() + "out of execute");
    }
}
