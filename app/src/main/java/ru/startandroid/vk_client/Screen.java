package ru.startandroid.vk_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

public class Screen extends AppCompatActivity {

    TextView tvHelloUser;
    VKAccessToken act;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen);

        tvHelloUser = (TextView) findViewById(R.id.tvHelloUser);
        act = VKAccessToken.tokenFromSharedPreferences(this.getApplicationContext(), "ACCESS_TOKEN");


        final VKRequest request = VKApi.users().get();
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                name = response.json.optString("id", "FCK U");
                Log.d("FUCK", response.responseString);
                Log.d("FUCK", name);
                tvHelloUser.setText(name);
            }

            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                super.attemptFailed(request, attemptNumber, totalAttempts);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }
        });



    }
}
