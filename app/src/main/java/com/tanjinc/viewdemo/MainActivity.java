package com.tanjinc.viewdemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    TextureVideoView mVideoView;

    ListView mListView;
    VideoAdapter mVideoAdapter;
    List<Uri> mVideoViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.play).setOnClickListener(this);
        findViewById(R.id.pause).setOnClickListener(this);


        mVideoAdapter = new VideoAdapter(this);
        mListView = (ListView) findViewById(R.id.list);
        mListView.setAdapter(mVideoAdapter);

//        for (int i=0; i < 5; i++) {
            mVideoViews.add(Uri.parse("/sdcard/111.mp4"));
            mVideoViews.add(Uri.parse("/sdcard/12.爱神的箭.avi"));
            mVideoViews.add(Uri.parse("/sdcard/localfile.mp4"));
            mVideoViews.add(Uri.parse("/sdcard/MKV.mkv"));
//        }
        mVideoAdapter.setData(mVideoViews);

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void playVideoAtPostion(int position) {
        if (mListView != null && position < mListView.getChildCount()) {
            LinearLayout linearLayout = (LinearLayout) mListView.getChildAt(position);
            if (linearLayout != null) {
                mVideoView = (TextureVideoView) linearLayout.findViewById(R.id.videoView);
                mVideoView.setVideoURI(Uri.parse("/sdcard/localfile.mp4"));
            }
        }
    }

    private void stopVideoAtPostion(int position) {
        if (mListView != null && position < mListView.getChildCount()) {
            LinearLayout linearLayout = (LinearLayout) mListView.getChildAt(position);
            if (linearLayout != null) {
                mVideoView = (TextureVideoView) linearLayout.findViewById(R.id.videoView);
                if (mVideoView.isPlaying()) {
                    mVideoView.pause();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
//                mVideoAdapter.getItem(2);
                playVideoAtPostion(1);

                break;
            case R.id.pause:
//
                stopVideoAtPostion(1);
//                mVideoView.pause();
                break;
        }
    }

    void startFloatService() {
        Intent mIntent = new Intent(this, MenuService.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startService(mIntent);
    }
}
