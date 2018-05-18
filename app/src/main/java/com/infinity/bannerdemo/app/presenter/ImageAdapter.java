package com.infinity.bannerdemo.app.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import com.infinity.bannerdemo.app.utils.ImageUtils.ImageItem;
import com.infinity.bannerdemo.app.R;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ImageItem> mImageList;

    public ImageAdapter(Context context) {
        mContext = context;
    }
    public void setImageList(ArrayList<ImageItem> imageList) {
        mImageList = imageList;
    }

    @Override
    public int getCount() {
        return mImageList.size();
    }

    @Override
    public ImageItem getItem(int position) {
        return mImageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageItem image = getItem(position);
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        ItemHolder itemHolder = null;
        if (convertView == null) {
            itemHolder = new ItemHolder();
            convertView = layoutInflater.inflate(R.layout.item_image, parent, false);
            itemHolder.thumbnail = convertView.findViewById(R.id.thumbnail);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) convertView.getTag();
        }
        itemHolder.thumbnail.setImageResource(image.getImageId());
        return convertView;
    }

    private class ItemHolder {
        ImageView thumbnail;
    }
}
