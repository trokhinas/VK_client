package ru.startandroid.vk_client.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.startandroid.vk_client.R;
import ru.startandroid.vk_client.model.UserModel;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<UserModel> items = new ArrayList<>();
    private Integer count;

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setItems(List<UserModel> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }
    public void clearItems()
    {
        items.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friend_list_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName;
        ImageView ivPhoto;
        public UserViewHolder(View itemView) {
            super(itemView);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName_listItem);
            ivPhoto = (ImageView) itemView.findViewById(R.id.ivUserPhoto_listItem);
        }

        public void bind(UserModel userModel) {
            tvUserName.setText(userModel.getFirstName() + " " + userModel.getLastName());
            Picasso.with(itemView.getContext())
                    .load(userModel.getPhoto100())
                    .into(((ImageView) (ivPhoto)));
        }
    }
}