package com.tonykazanjian.exoplayersample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exoplayer.R;

import java.util.List;

/**
 * @author Tony Kazanjian
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {

    private List<Video> mVideoList;

    public VideoAdapter(List<Video> videoList) {
        mVideoList = videoList;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_video, parent, false));
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {
        Video video = mVideoList.get(position);
        holder.mTextView.setText(video.getTitle());

    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    class VideoHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mTextView;

        public VideoHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.video_thumbnail);
            mTextView = (TextView)itemView.findViewById(R.id.video_title);
        }
    }

}
