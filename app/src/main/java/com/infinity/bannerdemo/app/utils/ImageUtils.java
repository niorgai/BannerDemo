/*
 * FileName: ImageUtils.java
 *
 * Description:
 *
 * Author: Infinity
 *
 * Email: 309212292@qq.com
 *
 * Ver 1.0, 2018-05-22, create file.
 */

package com.infinity.bannerdemo.app.utils;

import android.net.Uri;

import java.util.ArrayList;

public class ImageUtils {

    public static class ImageItem {
        private int mImageId;
        private Uri mImageUri;

        public ImageItem(int drawableId, Uri imageUri) {
            mImageId = drawableId;
            mImageUri = imageUri;
        }

        public int getImageId() {
            return mImageId;
        }

        public void setImageId(int imageId) {
            mImageId = imageId;
        }

        public Uri getImageUri() {
            return mImageUri;
        }

        public void setImageUri(Uri imageUri) {
            mImageUri = imageUri;
        }
    }

    public static ArrayList<ImageItem> sArrayList = new ArrayList<>();
}
