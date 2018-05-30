package com.infinity.bannerdemo.app.presenter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.infinity.bannerdemo.app.utils.Constants;
import com.infinity.bannerdemo.app.utils.ImageUtils;

import java.util.ArrayList;

import static com.infinity.bannerdemo.app.utils.Constants.USE_INFINY;
import static com.infinity.bannerdemo.app.utils.ImageUtils.sArrayList;

public class ImagePreviewViewAdapter extends PagerAdapter {

    private static final int IMAGE_LIST_SIZE = 3;
    private ArrayList<SimpleDraweeView> mImageList;
    private ViewPager mViewPager;

    public ImagePreviewViewAdapter(Context context, ViewPager viewPager) {
        this.mViewPager = viewPager;
        mImageList = new ArrayList<>(IMAGE_LIST_SIZE);
        for (int i = 0; i < IMAGE_LIST_SIZE; i++) {
            mImageList.add(new SimpleDraweeView(context));
        }
    }

    @Override
    public int getCount() {
        return ImageUtils.sArrayList != null ? Integer.MAX_VALUE : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // 把position对应位置的ImageView添加到ViewPager中
        Log.d("zhang", position + " instantiateItem " + position % IMAGE_LIST_SIZE);
        SimpleDraweeView iv = loadImage(position);
        if (iv.getParent() != null) {
            container.removeView(iv);
        }
        mViewPager.addView(iv);
        // 把当前添加ImageView返回回去.
        return iv;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        Log.d("zhang", position + " destroyItem " + position % IMAGE_LIST_SIZE);
//        mViewPager.removeView(mImageList.get(position % IMAGE_LIST_SIZE));
    }

    private SimpleDraweeView loadImage(int position) {
        SimpleDraweeView simpleDraweeView = mImageList.get(position % IMAGE_LIST_SIZE);
        Uri uri = sArrayList.get(position % Constants.IMAGE_COUNT).getImageUri();
        simpleDraweeView.setImageURI(uri);
        return simpleDraweeView;
    }
}
