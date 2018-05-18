package com.infinity.bannerdemo.app.utils;

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
}
