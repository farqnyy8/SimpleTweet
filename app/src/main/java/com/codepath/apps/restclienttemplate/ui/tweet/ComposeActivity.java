package com.codepath.apps.restclienttemplate.ui.tweet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.client.TwitterClient;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class ComposeActivity extends AppCompatActivity {

    private int MAX_TWEET_LENGTH = 280;
    public static String TAG = "ComposeActivity";

    RelativeLayout composeActivityRelativeLayout;
    EditText etCompose;
    Button btnTweet;

    TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        client = TwitterApp.getRestClient(this);

        initUi();
        setOnClickListeners();
    }

    private void initUi() {
        composeActivityRelativeLayout = findViewById(R.id.composeActivityRelativeLayout);
        etCompose = findViewById(R.id.etCompose);
        btnTweet = findViewById(R.id.btnTweet);
    }

    private void setOnClickListeners() {
        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweetContent = etCompose.getText().toString();
                if(tweetContent.length() == 0) {
                    Toast.makeText(ComposeActivity.this, R.string.tweet_too_short, Toast.LENGTH_SHORT).show();
                } else if ( tweetContent.length() > MAX_TWEET_LENGTH) {
                    Toast.makeText(ComposeActivity.this, R.string.tweet_too_long, Toast.LENGTH_SHORT).show();
                } else {
                    // make api call
                    client.publishTweet(tweetContent, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Headers headers, JSON json) {
                            Log.i(TAG, "onSuccess to publish tweet");
                            try {
                                Tweet tweet = Tweet.fromJson(json.jsonObject);
                                Intent resultIntent = new Intent();
                                resultIntent.putExtra(Tweet.TWEET_AS_STRING, Parcels.wrap(tweet));
                                setResult(RESULT_OK, resultIntent);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                            Log.e(TAG, "onFailure to publish tweet", throwable);
                        }
                    });
                    Snackbar.make(composeActivityRelativeLayout, R.string.tweet_success, Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}