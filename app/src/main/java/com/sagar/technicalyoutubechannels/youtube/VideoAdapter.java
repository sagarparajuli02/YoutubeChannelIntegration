package com.sagar.technicalyoutubechannels.youtube;


import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.sagar.technicalyoutubechannels.R;
import com.sagar.technicalyoutubechannels.loadimage.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class VideoAdapter extends ArrayAdapter<Video> implements SectionIndexer {

@SuppressWarnings("unused")
    HashMap<String, Integer> alphaIndexer;
    private final Context context;
    private final ArrayList<Video> itemsArrayList;
    ContentResolver resolver;
    Resources resource;
    String[] sections;
    private ImageLoader imgLoader;

    public VideoAdapter(Context context, ArrayList<Video> itemsArrayList, ContentResolver resolver, Resources resource) {
        super(context, R.layout.textvideo, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
        this.resolver = resolver;
        this.resource = resource;
        alphaIndexer = new HashMap<String, Integer>();
        int size = itemsArrayList.size();
        for (int x = 0; x < size; x++) {
            Video s = itemsArrayList.get(x);
            String ch = s.getVideoTitle().substring(0, 1);
            ch = ch.toUpperCase();
            if (!alphaIndexer.containsKey(ch)) {
                alphaIndexer.put(ch, x);

            }
            imgLoader = new ImageLoader(context);
            Set<String> sectionLetter = alphaIndexer.keySet();
            ArrayList<String> sectionList = new ArrayList<String>(sectionLetter);
            Collections.sort(sectionList);
            sections = new String[sectionList.size()];
            sections = sectionList.toArray(sections);
            notifyDataSetChanged();
        }}
        @Override
        public View getView ( int position, View convertView, ViewGroup parent){
            RelativeLayout alertView;
            if (convertView == null) {
                alertView = new RelativeLayout(getContext());
                String inflater = Context.LAYOUT_INFLATER_SERVICE;
                LayoutInflater vi;
                vi = (LayoutInflater) getContext().getSystemService(inflater);
                vi.inflate(R.layout.textvideo, alertView, true);
            } else {
                alertView = (RelativeLayout) convertView;

            }

            ImageView image = (ImageView) alertView.findViewById(R.id.tumbhid);
            TextView labelView = (TextView) alertView.findViewById(R.id.label);

            Picasso.with(context)
                    .load(itemsArrayList.get(position).getVideoThumbnail())
                    .placeholder(R.drawable.thumb).
                    error(R.drawable.thumb)
                    .into(image);

            labelView.setText((itemsArrayList.get(position)).getVideoTitle());
            return alertView;
        }

    public int getPositionForSection(int section) {
        return (alphaIndexer.get(sections[section]));
    }

    public int getSectionForPosition(int position) {
        return 0;
    }

    public Object[] getSections() {
        return this.sections;
    }
}
