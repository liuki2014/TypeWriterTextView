package com.kino.typewritertextview.sample;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import com.kino.typewritertextview.FontTypeWriterTextView;
import com.kino.typewritertextview.R;

public class MainActivity extends AppCompatActivity {

    private FontTypeWriterTextView description1, description2;
    private ViewPager viewPager;
    private String[] titles1, titles2;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.player);
        titles1 = getResources().getStringArray(R.array.titles1);
        titles2 = getResources().getStringArray(R.array.titles2);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter());
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        description1 = (FontTypeWriterTextView) findViewById(R.id.description1);
        description2 = (FontTypeWriterTextView) findViewById(R.id.description2);
        updateTextView(0);
        playVideo();
    }


    @Override
    protected void onDestroy() {
        videoView.stopPlayback();
        super.onDestroy();
    }

    private void playVideo() {
        videoView.setOnPreparedListener(new PlayerOnPreparedListener());
        String playUri = "android.resource://" + getPackageName() + "/" + R.raw.landing;
        videoView.setVideoPath(playUri);
        videoView.start();
    }

    private void updateTextView(int position) {
        description1.setText(titles1[position]);
        description2.setText(titles2[position]);
        description1.startAnim();
        description2.startAnim();
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            updateTextView(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    private class PlayerOnPreparedListener implements MediaPlayer.OnPreparedListener {


        @Override
        public void onPrepared(MediaPlayer mp) {
            mp.setLooping(true);
            videoView.start();
        }
    }

}
