/*
 * FileName: ImagePreviewFragment.java
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
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.infinity.bannerdemo.app.R;

import static com.infinity.bannerdemo.app.utils.Constants.IMAGE_ID;

public class ImagePreviewFragment extends Fragment {

    private int mImageId;
    private ImageView mImageView;

    public static ImagePreviewFragment newInstance(int imageId) {
        ImagePreviewFragment fragment = new ImagePreviewFragment();
        final Bundle args = new Bundle();
        args.putInt(IMAGE_ID, imageId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getArguments();
        if (extras != null) {
            mImageId = extras.getInt(IMAGE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_preview, container, false);
        mImageView = (ImageView) view.findViewById(R.id.image);
        loadImage();
        return view;
    }

    private void loadImage() {
        mImageView.setImageResource(mImageId);
    }
}
