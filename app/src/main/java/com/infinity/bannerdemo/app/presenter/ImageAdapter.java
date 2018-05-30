/*
 * FileName: ImageAdapter.java
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

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.infinity.bannerdemo.app.utils.ImageUtils.ImageItem;
import com.infinity.bannerdemo.app.R;

import static com.infinity.bannerdemo.app.utils.ImageUtils.sArrayList;

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
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        ItemHolder itemHolder = null;
        if (convertView == null) {
            itemHolder = new ItemHolder();
            convertView = layoutInflater.inflate(R.layout.item_image, parent, false);
            itemHolder.thumbnail = (SimpleDraweeView) convertView.findViewById(R.id.thumbnail);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) convertView.getTag();
        }
        Uri uri = Uri.parse("res://" + mContext.getPackageName() + "/" + sArrayList.get(position).getImageId());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(1, 1))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(itemHolder.thumbnail.getController())
                .build();
        itemHolder.thumbnail.setController(controller);
        return convertView;
    }

    private class ItemHolder {
        SimpleDraweeView thumbnail;
    }
}
