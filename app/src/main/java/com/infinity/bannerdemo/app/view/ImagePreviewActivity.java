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
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.infinity.bannerdemo.app.R;
import com.infinity.bannerdemo.app.presenter.ImagePreviewAdapter;
import com.infinity.bannerdemo.app.utils.Constants;

import static com.infinity.bannerdemo.app.utils.ImageUtils.sArrayList;

public class ImagePreviewActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ImagePreviewAdapter mAdapter;
    private int mImageId;
    private int mCurrentIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        mImageId = getIntent().getIntExtra(Constants.IMAGE_ID, 0);
        mViewPager = (ViewPager) findViewById(R.id.vp_image_preview);
        mAdapter = new ImagePreviewAdapter(getSupportFragmentManager());
        mAdapter.setImageItems(sArrayList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(2); // 多缓存一页，避免快速滑动时来不及加载
        setCurrentIndex();
        mViewPager.setCurrentItem(mCurrentIndex + 1);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mCurrentIndex = position;
            }

            @Override
            public void onPageScrolled(int arg0, float positionOffse, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //若viewpager滑动未停止，直接返回
                if (state != ViewPager.SCROLL_STATE_IDLE) {
                    return;
                }
                //若当前为第一张，设置页面为倒数第二张
                if (mCurrentIndex == 0) {
                    mViewPager.setCurrentItem(mAdapter.getCount() - 2, false);
                } else if (mCurrentIndex == mAdapter.getCount() - 1) {
                    //若当前为倒数第一张，设置页面为第二张
                    mViewPager.setCurrentItem(1, false);
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
}
