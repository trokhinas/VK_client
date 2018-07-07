package ru.startandroid.vk_client.Model;

/**
 * Created by toxat on 06.07.2018.
 */

public class PageModel {
    private int size;
    private  Integer id;





    private String first_name;
    private String last_name;
    private String online_status;
    private String photoUrl;
    private String city;

    private int friends;
    private int followers;

    PageModel()
    {
        size = 0;
    }
    public boolean isEmpty()
    {
        return size == 0;
    }


    public void setId(int id)
    {
        this.id = id;
        size = 1;
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
    public void setCity(String city)
    {
        this.city = city;
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
    public String getLname() {
        return last_name;
    }
    public String getOnline(){return online_status;}
    public String getUrlPhoto(){return photoUrl;}
    public int getFriends(){return friends;}
    public int getFollowers(){return followers;}
    public String getCity(){return city;}
}
