package com.ktruong.instagram;


import android.net.Uri;

public class InstagramPhoto {
    private String name;
    private String userFullName;
    private long id;
    private Uri profileImageUrl;
    private int likeCounts;
    private Uri imageUrl;
    private String createdTime;
    private String caption;
    private String captionFromUserName;

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Uri getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(Uri profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public int getLikeCounts() {
        return likeCounts;
    }

    public void setLikesCount(int commentCount) {
        this.likeCounts = commentCount;
    }

    public void setImageUrl(Uri imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Uri getImageUrl() {
        return imageUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setCaptionFromUserName(String captionFromUserName) {
        this.captionFromUserName = captionFromUserName;
    }

    public String getCaptionFromUserName() {
        return captionFromUserName;
    }
}
