package com.tonykazanjian.exoplayersample;

import android.content.Context;
import android.content.res.AssetManager;

/**
 * @author Tony Kazanjian
 */

public class VideoAssets {

    private AssetManager mAssets;

    public VideoAssets(Context context){
        mAssets = context.getAssets();
    }
    public AssetManager getAssets(){
        return mAssets;
    }
}
