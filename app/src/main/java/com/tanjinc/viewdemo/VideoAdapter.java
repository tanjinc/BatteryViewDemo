package com.tanjinc.viewdemo;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by tanjincheng on 16/10/20.
 */
public class VideoAdapter extends BaseAdapter {

    List<Uri> mVideoUris;
    LayoutInflater mInflater;
    boolean mBeginPlay = true; //初始播放状态

    public VideoAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<Uri> list) {
        mVideoUris = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mVideoUris!=null?mVideoUris.size():0;
    }

    @Override
    public Object getItem(int position) {
        return mVideoUris.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.video_container, null);
            viewHolder.mTextureVideoView = (TextureVideoView) convertView.findViewById(R.id.videoView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextureVideoView.setOnPreparedListener(onPrepareListener);
        viewHolder.mTextureVideoView.setVideoURI(mVideoUris.get(position));
        Log.d("@@", "video getView: position=" + position);
        if (position  == 3) {
            viewHolder.mTextureVideoView.start();
        } else {
            viewHolder.mTextureVideoView.pause();
        }
        return convertView;
    }

    MediaPlayer.OnPreparedListener onPrepareListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            if (mBeginPlay) {
                mp.start();
            } else {
                mp.pause();
            }
        }
    };
    class ViewHolder {
        TextureVideoView mTextureVideoView;
    }
}
