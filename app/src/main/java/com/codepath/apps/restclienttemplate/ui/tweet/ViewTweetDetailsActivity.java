package com.codepath.apps.restclienttemplate.ui.tweet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class ViewTweetDetailsActivity extends AppCompatActivity {

    ImageView ivProfileImage;
    TextView tvTweetBody;
    TextView tvScreenName;
    TextView tvTimeStamp;

    Tweet tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tweet_details);

        // back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tweet = Parcels.unwrap(getIntent().getParcelableExtra(Tweet.TWEET_AS_STRING));
        initUI(tweet);
    }

    public void initUI(Tweet tweet) {
        ivProfileImage = findViewById(R.id.detailsProfileImage);
        tvTweetBody = findViewById(R.id.detailsTweetBody);
        tvScreenName = findViewById(R.id.detailsScreenName);
        tvTimeStamp = findViewById(R.id.detailsTimeStamp);

        tvTweetBody.setText(tweet.body);
        tvScreenName.setText(tweet.user.screenName);
        Glide.with(this).load(tweet.user.publicImageUrl).into(ivProfileImage);
        tvTimeStamp.setText(tweet.getFormattedTimeStamp());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if( item.getItemId() == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}