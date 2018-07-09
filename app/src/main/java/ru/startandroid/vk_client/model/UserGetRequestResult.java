
package ru.startandroid.vk_client.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserGetRequestResult {

    @SerializedName("response")
    @Expose
    private List<UserPageModel> response = null;

    public List<UserPageModel> getResponse() {
        return response;
    }

    public void setResponse(List<UserPageModel> response) {
        this.response = response;
    }

}
