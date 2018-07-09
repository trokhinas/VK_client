package ru.startandroid.vk_client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendsGetRequestResult {

    @SerializedName("response")
    @Expose
    private FriendListModel response;

    public FriendListModel getResponse() {
        return response;
    }

    public void setResponse(FriendListModel response) {
        this.response = response;
    }

}
