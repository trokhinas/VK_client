
package ru.startandroid.vk_client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Counters {

    @SerializedName("albums")
    @Expose
    private Integer albums;
    @SerializedName("videos")
    @Expose
    private Integer videos;
    @SerializedName("audios")
    @Expose
    private Integer audios;
    @SerializedName("notes")
    @Expose
    private Integer notes;
    @SerializedName("photos")
    @Expose
    private Integer photos;
    @SerializedName("groups")
    @Expose
    private Integer groups;
    @SerializedName("gifts")
    @Expose
    private Integer gifts;
    @SerializedName("friends")
    @Expose
    private Integer friends;
    @SerializedName("online_friends")
    @Expose
    private Integer onlineFriends;
    @SerializedName("user_photos")
    @Expose
    private Integer userPhotos;
    @SerializedName("followers")
    @Expose
    private Integer followers;
    @SerializedName("subscriptions")
    @Expose
    private Integer subscriptions;
    @SerializedName("pages")
    @Expose
    private Integer pages;

    public Integer getAlbums() {
        return albums;
    }

    public void setAlbums(Integer albums) {
        this.albums = albums;
    }

    public Integer getVideos() {
        return videos;
    }

    public void setVideos(Integer videos) {
        this.videos = videos;
    }

    public Integer getAudios() {
        return audios;
    }

    public void setAudios(Integer audios) {
        this.audios = audios;
    }

    public Integer getNotes() {
        return notes;
    }

    public void setNotes(Integer notes) {
        this.notes = notes;
    }

    public Integer getPhotos() {
        return photos;
    }

    public void setPhotos(Integer photos) {
        this.photos = photos;
    }

    public Integer getGroups() {
        return groups;
    }

    public void setGroups(Integer groups) {
        this.groups = groups;
    }

    public Integer getGifts() {
        return gifts;
    }

    public void setGifts(Integer gifts) {
        this.gifts = gifts;
    }

    public Integer getFriends() {
        return friends;
    }

    public void setFriends(Integer friends) {
        this.friends = friends;
    }

    public Integer getOnlineFriends() {
        return onlineFriends;
    }

    public void setOnlineFriends(Integer onlineFriends) {
        this.onlineFriends = onlineFriends;
    }

    public Integer getUserPhotos() {
        return userPhotos;
    }

    public void setUserPhotos(Integer userPhotos) {
        this.userPhotos = userPhotos;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Integer subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

}
