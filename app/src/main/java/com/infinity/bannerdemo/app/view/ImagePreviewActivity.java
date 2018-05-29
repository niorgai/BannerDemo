/*
 * FileName: ImagePreviewActivity.java
 *
 * Description:
 *
 * Author: Infinity
 *
 * Email: 309212292@qq.com
 *
 * Ver 1.0, 2018-05-22, create file.
 */

package com.infinity.bannerdemo.app.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.infinity.bannerdemo.app.R;
import com.infinity.bannerdemo.app.presenter.ImagePreviewViewAdapter;
import com.infinity.bannerdemo.app.utils.Constants;

import static com.infinity.bannerdemo.app.utils.ImageUtils.sArrayList;

public class ImagePreviewActivity extends AppCompatActivity {

    private static final int PLAY_DELAY_TIME = 2500;
    private ViewPager mViewPager;
    private ImagePreviewViewAdapter mViewAdapter;
    private Button mPlayButton;
    private AutoScrollHandler mH;
    private int mImageId;
    private int mCurrentIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        mImageId = getIntent().getIntExtra(Constants.IMAGE_ID, 0);
        initViewPager();
        mH = new AutoScrollHandler();
        mPlayButton = (Button) findViewById(R.id.play);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mH.isPlay()) {
                    mH.stopLoop();
                } else {
                    mH.startLoop();
                }

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mH.stopLoop();
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.vp_image_preview);
        setCurrentIndex();
        mViewAdapter = new ImagePreviewViewAdapter(this, mViewPager);
        mViewPager.setAdapter(mViewAdapter);
        int m = (Integer.MAX_VALUE / 2) % sArrayList.size();
        int currentPosition = Integer.MAX_VALUE / 2 - m + mCurrentIndex;
        mViewPager.setCurrentItem(currentPosition);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mCurrentIndex = position % sArrayList.size();
            }

            @Override
            public void onPageScrolled(int arg0, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setCurrentIndex() {
        for (int i = 0; i < sArrayList.size(); i++) {
            if (mImageId == sArrayList.get(i).getImageId()) {
                mCurrentIndex = i;
                break;
            }
        }
    }

    private class AutoScrollHandler extends Handler {
        private boolean mIsPlay = false;
        @Override
        public void handleMessage(Message msg) {
            if (mIsPlay) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            }
            sendEmptyMessageDelayed(msg.what, PLAY_DELAY_TIME);
        }

        void startLoop() {
            mPlayButton.setText(R.string.play_auto_scroll);
            mIsPlay = false;
            removeCallbacksAndMessages(null);
            sendEmptyMessageDelayed(1, PLAY_DELAY_TIME);
        }

        void stopLoop() {
            mPlayButton.setText(R.string.stop_auto_scroll);
            mIsPlay = true;
            removeCallbacksAndMessages(null);
        }

        public boolean isPlay() {
            return mIsPlay;
        }

        public void setPlay(boolean play) {
            mIsPlay = play;
        }
    }
}
