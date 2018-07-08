package ru.startandroid.vk_client.Model;

import java.util.ArrayList;

/**
 * Created by toxat on 08.07.2018.
 */

public class FriendListModel {
    private int size;

    private ArrayList<FriendModel> list;

    public FriendListModel(ArrayList<FriendModel> arr)
    {
        list = arr;
        size = 1;
    }
    public void addInList(FriendModel fm)
    {
        list.add(fm);
    }
    public FriendModel getFromList(int index)
    {
        if(index > list.size())
            return null;
        return list.get(index);
    }
    public ArrayList<FriendModel> getList()
    {
        return list;
    }
}
