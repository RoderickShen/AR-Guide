package com.roderick.apple.shapp.Community.TourGroupAndStrategy;

/**
 * Created by dell on 2018/3/29.
 */

public class TourGroupModel {
    private  String title;
    private String desc;
    private String picture;
    private String content_url;

    public TourGroupModel(String title,String desc,String picture,String content_url){
        setTitle(title);
        setDesc(desc);
        setPicture(picture);
        setContent_url(content_url);
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }
}
