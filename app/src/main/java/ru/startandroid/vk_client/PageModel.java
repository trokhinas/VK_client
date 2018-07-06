package ru.startandroid.vk_client;

/**
 * Created by toxat on 06.07.2018.
 */

public class PageModel {
    //private int size;
    private  Integer id;

    private String first_name;
    private String last_name;
    private String online_status;
    private String photoUrl;

    private int friends;
    private int followers;

    /*PageModel()
    {
        size = 0;
    }*/
    /*boolean isEmpty()
    {
        return size == 0;
    }*/


    public void setId(int id)
    {
        this.id = id;
    }
    public void setFname(String fname)
    {
        first_name = fname;
    }
    public void setLname(String lname)
    {
        last_name = lname;
    }
    public void setOnline(String online)
    {
        online_status = online;
    }
    public void setPhotoUrl(String url)
    {
        photoUrl = url;
    }

    public void setFriends(int num)
    {
        friends = num;
    }
    public void setFollowers(int num)
    {
        followers = num;
    }

    public Integer getId() {
        return id;
    }

    public String getFname() {
        return first_name;
    }
}
