package com.ziyata.basicmvp.model;

import com.google.gson.annotations.SerializedName;

public class SingelUserResponse {
    @SerializedName("data")
    private UserData userData;

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
