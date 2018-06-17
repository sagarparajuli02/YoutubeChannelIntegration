package com.sagar.technicalyoutubechannels.youtube;


import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;

public class JsonParser {

    public String SmartSupport = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=UUQmg6f48mtIOX4DoerGJYWw&key=AIzaSyCLtRzn0-P2_mPfBKlk7SqzTSI-S49CTSs";
    public String technicalGuruji = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=UUOhHO2ICt0ti9KAh-QHvttQ&key=AIzaSyCLtRzn0-P2_mPfBKlk7SqzTSI-S49CTSs";
    public String geekyRanjit = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=UUO2WJZKQoDW4Te6NHx4KfTg&key=AIzaSyCLtRzn0-P2_mPfBKlk7SqzTSI-S49CTSs";

    public String Sharmaji = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=UUdiHpeH9fr4DMK_3wbUZapA&key=AIzaSyCLtRzn0-P2_mPfBKlk7SqzTSI-S49CTSs";

    public String C4Tech = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=UUlVIlK8QHZ2PFkXF97bA0lg&key=AIzaSyCLtRzn0-P2_mPfBKlk7SqzTSI-S49CTSs";

    public String TechnicalSagar ="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=UUCneap9DE1plCWwcObu-20Q&key=AIzaSyCLtRzn0-P2_mPfBKlk7SqzTSI-S49CTSs";

    public String Aaiyasik = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=UUiWe-JhZJL3cGpFsZNyU7uA&key=AIzaSyCLtRzn0-P2_mPfBKlk7SqzTSI-S49CTSs";
    public String GadgetHouse ="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=UUvpfclapgcuJo0M_x65pfRw&key=AIzaSyCLtRzn0-P2_mPfBKlk7SqzTSI-S49CTSs";
    public String Igyaan = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=UU7dLvCYNwhYe-l__yczFp1Q&key=AIzaSyCLtRzn0-P2_mPfBKlk7SqzTSI-S49CTSs";

    public String TechnicaDost = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=UURGl2gA9X6BXqOvNL2jePtw&key=AIzaSyCLtRzn0-P2_mPfBKlk7SqzTSI-S49CTSs";

    private static StringBuilder sb;
    private JSONObject jObj;
    public JsonParser(){

    }
    public JSONObject getJsonFromYoutube(String url) {
        DefaultHttpClient httpclient = new DefaultHttpClient();


        HttpGet getRequest = new HttpGet(url);
        getRequest.setHeader("Accept", "application/json");
        getRequest.setHeader("Accept-Encoding", "gzip");
        try {
            HttpResponse response = httpclient.execute(getRequest);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                Header contentEncoding = response.getFirstHeader("Content-Encoding");
                if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                    instream = new GZIPInputStream(instream);
                }
                String result = readStream(instream);
                Log.i("JSON", result);
                instream.close();
                this.jObj = new JSONObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.jObj;
    }
    private static String readStream(InputStream is){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            sb= new StringBuilder();
            String line = null;
            try{
                while (( line= reader.readLine())!=null){
                    sb.append(line+"\n");

                }
            }
            catch (IOException e){
                e.printStackTrace();


            }finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch(UnsupportedEncodingException e1){
            e1.printStackTrace();
        }
        return sb.toString();
    }}
