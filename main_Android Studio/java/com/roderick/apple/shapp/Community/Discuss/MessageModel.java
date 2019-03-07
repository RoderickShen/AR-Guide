package com.roderick.apple.shapp.Community.Discuss;

public class MessageModel {
    private String content;
    private String headprotrait;
    private String userAdress;

    public MessageModel(String content,String picture,String userAdress){
        this.content=content;
        setHeadprotrait(picture);
        setUserAdress(userAdress);
    }



    public void setHeadprotrait(String picture) {
        this.headprotrait = picture;
    }
    public void setUserAdress(String userAdress){
        this.userAdress=userAdress;
    }
    public String getContent() {
        return content;
    }
    public String getHeadprotrait(){return headprotrait;}
    public String getUserAdress(){return userAdress;}
}
