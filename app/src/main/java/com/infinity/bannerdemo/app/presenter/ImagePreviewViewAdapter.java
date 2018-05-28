package com.infinity.bannerdemo.app.presenter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import static com.infinity.bannerdemo.app.utils.Constants.USE_INFINY;

public class ImagePreviewViewAdapter extends PagerAdapter {

    private ArrayList<SimpleDraweeView> mImageList;
    private ViewPager mViewPager;

    public ImagePreviewViewAdapter(ArrayList<SimpleDraweeView> imageList, ViewPager viewPager) {
        this.mImageList = imageList;
        this.mViewPager = viewPager;
    }

    @Override
    public int getCount() {
        if (USE_INFINY) {
            return Integer.MAX_VALUE;
        } else {
            return mImageList == null ? 0 : mImageList.size();
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // 把position对应位置的ImageView添加到ViewPager中
        SimpleDraweeView iv = mImageList.get(position % mImageList.size());
        mViewPager.addView(iv);
        // 把当前添加ImageView返回回去.
        return iv;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        mViewPager.removeView(mImageList.get(position % mImageList.size()));
    }
}
