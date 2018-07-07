package ru.startandroid.vk_client;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface vkAPI {
    @GET("method/user.get")
    Call<JSONObject> getUser(@Query("user_ids") String userID,
                             @Query("fields") String VKFields,
                            @Query("access_token") String VKAccessToken);
}
