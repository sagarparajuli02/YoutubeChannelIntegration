package com.sagar.technicalyoutubechannels;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sagar.technicalyoutubechannels.youtube.JsonParser;
import com.sagar.technicalyoutubechannels.youtube.Video;
import com.sagar.technicalyoutubechannels.youtube.VideoAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Playlist extends AppCompatActivity implements AdapterView.OnClickListener, AdapterView.OnItemClickListener {
    String abc = null;
//    CoordinatorLayout coordinatorLayout;
    private ListView listVideo;
    private JsonParser parserVideo;
    private ProgressDialog progress;
    private Video vObject;
    private VideoAdapter videoAdapter;
    private ArrayList<Video> videoArrayList;
    boolean internet_connection(){
        //Check if connected to internet, output accordingly
        ConnectivityManager cm =
                (ConnectivityManager)Playlist.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        setTitle("Latest Videos");




        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);;
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if(!internet_connection()){
            new AlertDialog.Builder(this)
                    .setMessage("No Internet Connection")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .show();
        }

        videoArrayList = new ArrayList<Video>();
        listVideo= (ListView)findViewById(R.id.list_item);
        listVideo.setOnItemClickListener(this);
        parserVideo = new JsonParser();
        listVideo.setAdapter(videoAdapter);
        abc= getIntent().getExtras().get("abc").toString();
        new Async().execute();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onBackPressed() {
        Intent myIntent = new Intent(Playlist.this, MainActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(myIntent);
        finish();
        return ;
    }
    public ArrayList<Video> parsingJson(String videoUrl) {
        try {
            JSONObject json = parserVideo.getJsonFromYoutube(videoUrl);
            JSONArray jArray = new JSONArray(json.getString("items"));
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject thumbnail= jArray.getJSONObject(i);
                JSONObject snippets = thumbnail.getJSONObject("snippet");
                JSONObject defaulturl= snippets.getJSONObject("thumbnails");
                JSONObject url = defaulturl.getJSONObject("medium");
                JSONObject resourceId = snippets.getJSONObject("resourceId");
                String videoId = resourceId.getString("videoId");
                String imageurl= url.getString("url");
                String title= snippets.getString("title");
                vObject= new Video(title,imageurl,videoId);
                videoArrayList.add(i, vObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.videoArrayList;
    }

    public void onItemClick(AdapterView<?>parent , View view, int position, long id) {
        vObject = videoArrayList.get(position);
        String video = vObject.getVideoId();
        String videoTitle= vObject.getVideoTitle();
        Intent intent = new Intent(Playlist.this, FullScreenDemoActivity.class);
        intent.putExtra("video", video);
        intent.putExtra("title", videoTitle);


        intent.putExtra("abc", abc);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

    }


    public class Async extends AsyncTask<String, String, String> {
        ArrayList<Video> videolist;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(Playlist.this);
            progress.setMessage("Loading Videos ...");
            progress.show();
        }

        protected String doInBackground(String... params) {
            try {
                if (abc.equals("SmartSupport")) {
                    videolist = parsingJson(parserVideo.SmartSupport);
                }
                else if (abc.equals("technicalGuruji")) {
                    videolist = parsingJson(parserVideo.technicalGuruji);
                }
                else  if (abc.equals("Sharmaji")) {
                    videolist = parsingJson(parserVideo.Sharmaji);
                }
                else  if (abc.equals("C4Tech")) {
                    videolist = parsingJson(parserVideo.C4Tech);
                }
                else  if (abc.equals("geekyRanjit")) {
                    videolist = parsingJson(parserVideo.geekyRanjit);
                }
                else  if (abc.equals("TechnicalSagar")) {
                    videolist = parsingJson(parserVideo.TechnicalSagar);
                }
                else  if (abc.equals("Aaiyasik")) {
                    videolist = parsingJson(parserVideo.Aaiyasik);
                }
                else  if (abc.equals("GadgetHouse")) {
                    videolist = parsingJson(parserVideo.GadgetHouse);
                }
                else  if (abc.equals("Igyaan")) {
                    videolist = parsingJson(parserVideo.Igyaan);
                }
                else  if (abc.equals("TechnicaDost")) {
                    videolist = parsingJson(parserVideo.TechnicaDost);
                }




                else {
                    Toast.makeText(getApplicationContext(), "Not Found", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                finish();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                videoAdapter = new VideoAdapter(Playlist.this, this.videolist, Playlist.this.getContentResolver(), Playlist.this.getResources());
                listVideo.setAdapter(videoAdapter);
                listVideo.setFastScrollEnabled(true);
                progress.dismiss();

            } catch (Exception e) {
            }
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home);
        finish();
        return super.onOptionsItemSelected(item);
    }

}