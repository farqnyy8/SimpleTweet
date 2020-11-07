package com.codepath.apps.restclienttemplate.ui.tweet;

import com.codepath.apps.restclienttemplate.utils.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {

    private static String TEXT = "text";
    private static String CREATED_AT = "created_at";
    private static String USER = "user";
    private static String ID = "id";

    public static final String TWEET_AS_STRING = "tweet";

    public String body;
    public String createdAt;
    public User user;
    public long id;

    public Tweet() {}

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString(TEXT);
        tweet.createdAt = jsonObject.getString(CREATED_AT);
        tweet.user = User.fromJson(jsonObject.getJSONObject(USER));
        tweet.id = jsonObject.getLong(ID);
        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++)
        {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }

        return tweets;
    }

    public String getFormattedTimeStamp() {
        return TimeFormatter.getTimeDifference(createdAt);
    }
}
