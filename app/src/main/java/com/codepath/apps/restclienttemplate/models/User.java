package com.codepath.apps.restclienttemplate.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
@Entity
public class User {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SCREEN_NAME = "screen_name";
    private static final String PROFILE_IMAGE_URL = "profile_image_url_https";

    @PrimaryKey
    @ColumnInfo
    public long id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public String screenName;

    @ColumnInfo
    public String publicImageUrl;

    public User() {}

    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.id = jsonObject.getLong(ID);
        user.name = jsonObject.getString(NAME);
        user.screenName = jsonObject.getString(SCREEN_NAME);
        user.publicImageUrl = jsonObject.getString(PROFILE_IMAGE_URL);
        return user;
    }

    public static List<User> fromJsonTweetArray(List<Tweet> tweetsFromNetwork) {
        List<User> users = new ArrayList<>();
        for(int i = 0; i < tweetsFromNetwork.size(); i++) {
            users.add(tweetsFromNetwork.get(i).user);
        }
        return users;
    }
}
