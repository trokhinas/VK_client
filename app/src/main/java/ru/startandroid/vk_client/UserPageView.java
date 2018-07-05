package ru.startandroid.vk_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKList;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserPageView extends AppCompatActivity {

    TextView tvUsername, tvOnline, tvCity;
    ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);
        Log.d("FUCK", this.getClass().toString() + " created");
        initUserPage();


        VKRequest req = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "first_name, last_name, online, city, photo_100"));
        req.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                Log.d("FUCK", response.json.toString());
                JSONArray resp = response.json.optJSONArray("response");
                JSONObject user = resp.optJSONObject(0);

                tvUsername.setText(user.optString("first_name") + " " + user.optString("last_name"));

                tvOnline.setText(user.optInt("online") == 1 ? "online" : "offline");

                String city = user.optJSONObject("city").optString("title");
                tvCity.setText(city);

                String url = user.optString("photo_100");
                new DownloadImageTask(ivPhoto).execute(url);
            }
        });


    }

    private void initUserPage() {
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvOnline = (TextView) findViewById(R.id.tvOnline);
        tvCity = (TextView) findViewById(R.id.tvCity);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);

    }

}
