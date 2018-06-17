package com.sagar.technicalyoutubechannels.loadimage;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;


import com.sagar.technicalyoutubechannels.youtube.C0306R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;

public class ImageLoader {
    ExecutorService executorService;
    FileCache fileCache;
    private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap());
    MemoryCache memoryCache = new MemoryCache();
    int stub_id = C0306R.drawable.ace_profile;

    class BitmapDisplayer implements Runnable {
        Bitmap bitmap;
        PhotoToLoad photoToLoad;

        public BitmapDisplayer(Bitmap b, PhotoToLoad p) {
            this.bitmap = b;
            this.photoToLoad = p;
        }

        public void run() {
            if (!ImageLoader.this.imageViewReused(this.photoToLoad)) {
                if (this.bitmap != null) {
                    this.photoToLoad.imageView.setImageBitmap(this.bitmap);
                } else {
                    this.photoToLoad.imageView.setImageResource(ImageLoader.this.stub_id);
                }
            }
        }
    }

    private class PhotoToLoad {
        public ImageView imageView;
        public String url;

        public PhotoToLoad(String u, ImageView i) {
            this.url = u;
            this.imageView = i;
        }
    }

    class PhotosLoader implements Runnable {
        PhotoToLoad photoToLoad;

        PhotosLoader(PhotoToLoad photoToLoad) {
            this.photoToLoad = photoToLoad;
        }

        public void run() {
            if (!ImageLoader.this.imageViewReused(this.photoToLoad)) {
                Bitmap bmp = ImageLoader.this.getBitmap(this.photoToLoad.url);
                ImageLoader.this.memoryCache.put(this.photoToLoad.url, bmp);
                if (!ImageLoader.this.imageViewReused(this.photoToLoad)) {
                    ((Activity) this.photoToLoad.imageView.getContext()).runOnUiThread(new BitmapDisplayer(bmp, this.photoToLoad));
                }
            }
        }
    }

    public ImageLoader(Context context) {
        this.fileCache = new FileCache(context);
        this.executorService = Executors.newFixedThreadPool(5);
    }

    public void DisplayImage(String url, int loader, ImageView imageView) {
        this.stub_id = loader;
        this.imageViews.put(imageView, url);
        Bitmap bitmap = this.memoryCache.get(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        queuePhoto(url, imageView);
        imageView.setImageResource(loader);
    }

    private void queuePhoto(String url, ImageView imageView) {
        this.executorService.submit(new PhotosLoader(new PhotoToLoad(url, imageView)));
    }

    private Bitmap getBitmap(String url) {
        File f = this.fileCache.getFile(url);
        Bitmap b = decodeFile(f);
        if (b != null) {
            return b;
        }
        try {
            Log.e("urll", "" + url.toString());
            HttpsURLConnection conn = (HttpsURLConnection) new URL(url.toString()).openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is = conn.getInputStream();
            OutputStream os = new FileOutputStream(f);
            Utils.CopyStream(is, os);
            os.close();
            return decodeFile(f);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private Bitmap decodeFile(File f) {
        try {
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);
            int width_tmp = o.outWidth;
            int height_tmp = o.outHeight;
            int scale = 1;
            while (width_tmp / 2 >= 70 && height_tmp / 2 >= 70) {
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = 2;
            return Bitmap.createScaledBitmap(BitmapFactory.decodeStream(new FileInputStream(f), null, o2), 80, 60, true);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    boolean imageViewReused(PhotoToLoad photoToLoad) {
        String tag = (String) this.imageViews.get(photoToLoad.imageView);
        if (tag == null || !tag.equals(photoToLoad.url)) {
            return true;
        }
        return false;
    }

    public void clearCache() {
        this.memoryCache.clear();
        this.fileCache.clear();
    }
}