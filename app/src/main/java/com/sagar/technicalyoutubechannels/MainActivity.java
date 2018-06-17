package com.sagar.technicalyoutubechannels;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    Button SmartSupport,TechnicalGuruji,Sharmaji,TechnicalSagar,GeekyRanjit,C4Tech,Aaiyasik,GadgetHouse,TechnicaDost,Igyaan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SmartSupport= (Button)findViewById(R.id.MysmartSupport);
        TechnicalGuruji= (Button)findViewById(R.id.technicalGuruji);
        Sharmaji= (Button)findViewById(R.id.sharmaJi);
        TechnicalSagar= (Button)findViewById(R.id.technicalSagar);
        GeekyRanjit= (Button)findViewById(R.id.geekyRanjit);
        C4Tech = (Button)findViewById(R.id.C4Tech);
        Aaiyasik= (Button)findViewById(R.id.aaiyaSiktay);
        GadgetHouse= (Button)findViewById(R.id.gadgetHouse);
        TechnicaDost= (Button)findViewById(R.id.technicalDost);
        Igyaan= (Button)findViewById(R.id.igayaan);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


     TechnicaDost.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent tech = new Intent(MainActivity.this,Playlist.class);
             tech.putExtra("abc","TechnicaDost");
             startActivity(tech);
         }
     });
        Igyaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smart= new Intent( MainActivity.this,Playlist.class);
                smart.putExtra("abc","Igyaan");
                startActivity(smart);
            }
        });
        GeekyRanjit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smart= new Intent( MainActivity.this,Playlist.class);
                smart.putExtra("abc","geekyRanjit");
                startActivity(smart);
            }
        });
        GadgetHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smart= new Intent( MainActivity.this,Playlist.class);
                smart.putExtra("abc","GadgetHouse");
                startActivity(smart);
            }
        });

        SmartSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smart= new Intent( MainActivity.this,Playlist.class);
                smart.putExtra("abc","SmartSupport");
                startActivity(smart);
            }
        });

        TechnicalGuruji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent play= new Intent( MainActivity.this,Playlist.class);
                play.putExtra("abc","technicalGuruji");
                startActivity(play);
            }
        });
        Sharmaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent play= new Intent( MainActivity.this,Playlist.class);
                play.putExtra("abc","Sharmaji");
                startActivity(play);
            }
        });
        TechnicalSagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent play= new Intent( MainActivity.this,Playlist.class);
                play.putExtra("abc","TechnicalSagar");
                startActivity(play);
            }
        });
        C4Tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent play= new Intent( MainActivity.this,Playlist.class);
                play.putExtra("abc","C4Tech");
                startActivity(play);
            }
        });
        Aaiyasik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent play= new Intent( MainActivity.this,Playlist.class);
                play.putExtra("abc","Aaiyasik");
                startActivity(play);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // action with ID action_settings was selected
            case R.id.action_settings:
                Intent setting= new Intent(MainActivity.this,About.class);
                startActivity(setting);
                break;


            case R.id.updateApp :
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.sagar.technicalyoutubechannels"));
                startActivity(intent);
                break;

            default:
                break;
        }

        return true;
    }


}

