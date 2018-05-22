/*
 * FileName: ImagePreviewAdapter.java
 *
 * Description:
 *
 * Author: Infinity
 *
 * Email: 309212292@qq.com
 *
 * Ver 1.0, 2018-05-22, create file.
 */

package com.infinity.bannerdemo.app.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.infinity.bannerdemo.app.utils.ImageUtils.ImageItem;
import com.infinity.bannerdemo.app.view.ImagePreviewFragment;

import java.util.ArrayList;

public class ImagePreviewAdapter extends FragmentStatePagerAdapter {

    private ArrayList<ImageItem> mImageItems;

    public ImagePreviewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        ImageItem item = mImageItems.get(position);
        Fragment f;
        f = ImagePreviewFragment.newInstance(item.getImageId());
        return f;
    }

    @Override
    public int getCount() {
        return mImageItems == null ? 0 : mImageItems.size();
    }

    public void setImageItems(ArrayList<ImageItem> imageItems) {
        mImageItems = new ArrayList<>(imageItems);
        mImageItems.add(0, imageItems.get(imageItems.size()-1));
        mImageItems.add(imageItems.get(0));
    }
}
