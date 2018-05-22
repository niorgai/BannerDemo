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

import java.util.ArrayList;

public class ImageUtils {

    public static class ImageItem {
        private int mImageId;

        public ImageItem(int drawableId) {
            mImageId = drawableId;
        }

        public int getImageId() {
            return mImageId;
        }

        public void setImageId(int imageId) {
            mImageId = imageId;
        }
    }

    public static ArrayList<ImageItem> sArrayList = new ArrayList<>();
}
