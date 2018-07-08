package ru.startandroid.vk_client.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import ru.startandroid.vk_client.R;

public abstract class listView extends AppCompatActivity {

    ListView lv;

    public abstract void ShowProgress();
    public abstract void HideProgress();
}
