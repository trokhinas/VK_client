package ru.startandroid.vk_client.Model;

/**
 * Created by toxat on 08.07.2018.
 */

public class FriendModel {
    private String id;

    private String first_name;
    private String last_name;
    private String urlPhoto;

    public FriendModel(String id, String fname, String lname, String url){
        this.id = id;
        first_name = fname;
        last_name = lname;
        urlPhoto = url;
    }

    public void setId(String id)
    {
        this.id = id;
    }
    public void setFirstName(String fname)
    {
        first_name = fname;
    }
    public void setLastName(String lname)
    {
        last_name = lname;
    }
    public void setUrlPhoto(String url)
    {
        urlPhoto = url;
    }

    public String getUserName() {
        return first_name + " " + last_name;
    }
    public String getId(){return id;}
    public String geturlPhoto() {
        return urlPhoto;
    }
}
