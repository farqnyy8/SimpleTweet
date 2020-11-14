package com.codepath.apps.restclienttemplate.ui.tweet;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.TweetViewHolder> {

    Context context;
    List<Tweet> tweets;

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    @NonNull
    @Override
    public TweetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new TweetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Tweet> tweets) {
        this.tweets.addAll(tweets);
        notifyDataSetChanged();
    }

    // Define a viewHolder
    public class TweetViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        TextView tvTweetBody;
        TextView tvScreenName;
        TextView tvTimeStamp;
        RelativeLayout tweetContainer;

        public TweetViewHolder(@NonNull View itemView) {
            super(itemView);
            tweetContainer = itemView.findViewById(R.id.tweetContainer);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvTweetBody = itemView.findViewById(R.id.tvTweetBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
        }

        public void bind(final Tweet tweet) {
            tvTweetBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            Glide.with(context).load(tweet.user.publicImageUrl).into(ivProfileImage);
            tvTimeStamp.setText(tweet.getFormattedTimeStamp());

            tweetContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent viewTweetDetails = new Intent(context, ViewTweetDetailsActivity.class);
                    viewTweetDetails.putExtra(Tweet.TWEET_AS_STRING, Parcels.wrap(tweet));
                    context.startActivity(viewTweetDetails);
                }
            });
        }
    }
}
