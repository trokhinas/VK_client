package ru.startandroid.vk_client.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ru.startandroid.vk_client.DownloadClass.DownloadImageTask;
import ru.startandroid.vk_client.Model.FriendModel;
import ru.startandroid.vk_client.R;
import ru.startandroid.vk_client.View.FriendListView;


public class FriendAdapter extends BaseAdapter{
    Context cntx;
    ArrayList<FriendModel> arr;
    LayoutInflater lInflater;

    public FriendAdapter(Context context, ArrayList<FriendModel> ar) {
        cntx = context;
        arr = ar;
        lInflater = (LayoutInflater) cntx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = lInflater.inflate(R.layout.friend_list_item, parent, false);

        FriendModel f = (FriendModel) getItem(position);

        TextView userName = (TextView) view.findViewById(R.id.tvUserName_listItem);
        userName.setText(f.getUserName());
        ImageView userPhoto = (ImageView) view.findViewById(R.id.ivUserPhoto_listItem);
        new DownloadImageTask(userPhoto).execute(f.geturlPhoto());

        return view;
    }
}
