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

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.infinity.bannerdemo.app.R;
import com.infinity.bannerdemo.app.presenter.ImagePreviewAdapter;
import com.infinity.bannerdemo.app.presenter.ImagePreviewViewAdapter;
import com.infinity.bannerdemo.app.utils.Constants;

import java.util.ArrayList;

import static com.infinity.bannerdemo.app.utils.Constants.USE_INFINY;
import static com.infinity.bannerdemo.app.utils.Constants.USE_VIEW;
import static com.infinity.bannerdemo.app.utils.ImageUtils.sArrayList;

public class ImagePreviewActivity extends AppCompatActivity {

    private static final int PLAY_DELAY_TIME = 3000;
    private ViewPager mViewPager;
    private ImagePreviewAdapter mAdapter;
    private ImagePreviewViewAdapter mViewAdapter;
    private Button mPlayButton;
    private AutoScrollHandler H;
    private int mImageId;
    private int mCurrentIndex;
    private boolean mIsPlay = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        mImageId = getIntent().getIntExtra(Constants.IMAGE_ID, 0);
        initViewPager();
        H = new AutoScrollHandler();
        mPlayButton = (Button) findViewById(R.id.play);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsPlay) {
                    mPlayButton.setText("Play");
                    mIsPlay = false;
                    H.stopLoop();
                } else {
                    mPlayButton.setText("Stop");
                    mIsPlay = true;
                    H.startLoop();
                }

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIsPlay = false;
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.vp_image_preview);
        if (USE_VIEW) {
            mViewAdapter = new ImagePreviewViewAdapter(initSimpleDraweeViewList(), mViewPager);
            mViewPager.setAdapter(mViewAdapter);
            mViewPager.setOffscreenPageLimit(2); // 多缓存一页，避免快速滑动时来不及加载
            setCurrentIndex();
            if (USE_INFINY) {
                int m = (Integer.MAX_VALUE / 2) % sArrayList.size();
                int currentPosition = Integer.MAX_VALUE / 2 - m + mCurrentIndex;
                mViewPager.setCurrentItem(currentPosition);
            } else {
                mViewPager.setCurrentItem(++mCurrentIndex);
            }
        } else {
            mAdapter = new ImagePreviewAdapter(getSupportFragmentManager());
            mAdapter.setImageItems(sArrayList);
            mViewPager.setAdapter(mAdapter);
            mViewPager.setOffscreenPageLimit(2); // 多缓存一页，避免快速滑动时来不及加载
            setCurrentIndex();
            mViewPager.setCurrentItem(++mCurrentIndex);
        }
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (USE_INFINY) {
                    mCurrentIndex = position % sArrayList.size();
                } else {
                    mCurrentIndex = position;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float positionOffse, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (!USE_INFINY) {
                    //若viewpager滑动未停止，直接返回
//                    if (state != ViewPager.SCROLL_STATE_IDLE) {
//                        return;
//                    }
                    //若当前为第一张，设置页面为倒数第二张
                    if (mCurrentIndex == 0) {
                        mViewPager.setCurrentItem(mViewAdapter.getCount() - 2, false);
                    } else if (mCurrentIndex == mViewAdapter.getCount() - 1) {
                        //若当前为倒数第一张，设置页面为第二张
                        mViewPager.setCurrentItem(1, false);
                    }
                }
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

    private ArrayList<ImageView> initImageViewList() {
        ArrayList<ImageView> arrayList = new ArrayList<>(sArrayList.size());
        for (int i = 0; i < sArrayList.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(sArrayList.get(i).getImageId());
            arrayList.add(imageView);
        }
        if (!USE_INFINY) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(sArrayList.get(sArrayList.size() - 1).getImageId());
            arrayList.add(0, imageView);
            imageView = new ImageView(this);
            imageView.setImageResource(sArrayList.get(0).getImageId());
            arrayList.add(imageView);
        }
        return sArrayList.size() == 0 ? null : arrayList;
    }

    private ArrayList<SimpleDraweeView> initSimpleDraweeViewList() {
        ArrayList<SimpleDraweeView> arrayList = new ArrayList<>(sArrayList.size());

        for (int i = 0; i < sArrayList.size(); i++) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this);
            Uri uri = Uri.parse("res://" + getPackageName() + "/" + sArrayList.get(i).getImageId());
            simpleDraweeView.setImageURI(uri);
            arrayList.add(simpleDraweeView);
        }
        if (!USE_INFINY) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this);
            Uri uri = Uri.parse("res://" + getPackageName() + "/"
                    + sArrayList.get(sArrayList.size() - 1).getImageId());
            simpleDraweeView.setImageURI(uri);
            arrayList.add(0, simpleDraweeView);
            simpleDraweeView = new SimpleDraweeView(this);
            uri = Uri.parse("res://" + getPackageName() + "/" + sArrayList.get(0).getImageId());
            simpleDraweeView.setImageURI(uri);
            arrayList.add(simpleDraweeView);
        }
        return sArrayList.size() == 0 ? null : arrayList;
    }

    private class AutoScrollHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            if (mIsPlay) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            }
            sendEmptyMessageDelayed(msg.what, PLAY_DELAY_TIME);
        }

        void startLoop() {
            removeCallbacksAndMessages(null);
            sendEmptyMessageDelayed(1, PLAY_DELAY_TIME);
        }

        void stopLoop() {
            removeCallbacksAndMessages(null);
        }
    }
}
