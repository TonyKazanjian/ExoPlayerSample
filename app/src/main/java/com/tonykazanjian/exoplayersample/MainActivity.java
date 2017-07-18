package com.tonykazanjian.exoplayersample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.exoplayer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tony Kazanjian
 */

public class MainActivity extends AppCompatActivity implements VideoAdapter.VideoListener{

    private VideoAssets mVideoAssets;
    private RecyclerView mRecyclerView;
    private VideoAdapter mVideoAdapter;
    private Video video;
    private List<Video> mVideoList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoAssets = new VideoAssets(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.videoRv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mVideoList = createVideoList();
        mVideoAdapter = new VideoAdapter(mVideoList, this);
        mRecyclerView.setAdapter(mVideoAdapter);
    }

    public List<Video> createVideoList() {
        List<Video> videos = new ArrayList<>();
        try {
            parseItems(videos, new JSONObject(loadJSONFromAsset()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return videos;
    }

    public String loadJSONFromAsset() {
        String json = null;
        InputStream is = null;
        try {
            is = mVideoAssets.getAssets().open("VideoData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    public void parseItems(List<Video> videos, JSONObject jsonBody)
            throws IOException, JSONException {

        JSONObject videoJsonObject = jsonBody.getJSONObject("result");
        JSONArray videoJsonArray = videoJsonObject.getJSONArray("videos");

        for (int i = 0; i < videoJsonArray.length(); i++) {
            JSONObject jsonObject = videoJsonArray.getJSONObject(i);

            video = new Video();
            video.setUrl(jsonObject.getString("url"));
            video.setTitle(jsonObject.getString("title"));
            video.setTag(jsonObject.getString("tag"));

            videos.add(video);
        }
    }

    @Override
    public void startVideoPlayer(String tag) {
        switch (tag){
            case "normal":
                startActivity(PlayerActivity.newIntent(this, mVideoList.get(0)));
                break;
            case "VR":
                startActivity(PlayerActivity.newIntent(this, mVideoList.get(1)));
                break;
        }
    }
}
