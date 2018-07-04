package ru.startandroid.vk_client;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.vk.sdk.util.VKUtil;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
        Log.d("FKING LOG",fingerprints[0]);
        Log.d("FKING LOG", "FUCK");
    }
}
