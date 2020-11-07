package com.codepath.apps.restclienttemplate.ui.tweet;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class User {

    private static final String NAME = "name";
    private static final String SCREEN_NAME = "screen_name";
    private static final String PROFILE_IMAGE_URL = "profile_image_url_https";

    public String name;
    public String screenName;
    public String publicImageUrl;

    public User() {}

    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.name = jsonObject.getString(NAME);
        user.screenName = jsonObject.getString(SCREEN_NAME);
        user.publicImageUrl = jsonObject.getString(PROFILE_IMAGE_URL);
        return user;
    }
}
