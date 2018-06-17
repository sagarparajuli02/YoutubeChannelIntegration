package com.sagar.technicalyoutubechannels.loadimage;

import android.graphics.Bitmap;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MemoryCache {
    private Map<String, SoftReference<Bitmap>> cache = Collections.synchronizedMap(new HashMap());

    public Bitmap get(String id) {
        if (this.cache.containsKey(id)) {
            return (Bitmap) ((SoftReference) this.cache.get(id)).get();
        }
        return null;
    }

    public void put(String id, Bitmap bitmap) {
        this.cache.put(id, new SoftReference(bitmap));
    }

    public void clear() {
        this.cache.clear();
    }
}
