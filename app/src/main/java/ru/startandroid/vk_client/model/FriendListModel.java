package ru.startandroid.vk_client.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendListModel {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("items")
    @Expose
    private List<UserModel> items = null;

    public FriendListModel(Integer count, List<UserModel> items)
    {
        this.count = count;
        this.items = items;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<UserModel> getItems() {
        return items;
    }

    public void setItems(List<UserModel> items) {
        this.items = items;
    }

}
