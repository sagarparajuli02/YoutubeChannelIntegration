package com.sagar.technicalyoutubechannels;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class FullScreenDemoActivity extends YouTubeBaseActivity {

    private YouTubePlayerView youTubePlayerView;

    private String videoid;
    String abc= null;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_demo);
        setTitle("Watch Videos");


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);
        Intent intent = getIntent();
        videoid = intent.getStringExtra("video");
       String title= intent.getStringExtra("title");
        abc = intent.getStringExtra("abc");
        TextView Title= (TextView)findViewById(R.id.titleVideo);

        Title.setText(title);

   youTubePlayerView.initialize("AIzaSyCLtRzn0-P2_mPfBKlk7SqzTSI-S49CTSs", new YouTubePlayer.OnInitializedListener() {


        @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {


                youTubePlayer.loadVideo(videoid);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

    }


}
