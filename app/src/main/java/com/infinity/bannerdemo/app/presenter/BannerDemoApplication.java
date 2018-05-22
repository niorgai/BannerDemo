/*
 * FileName: BannerDemoApplication.java
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

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class BannerDemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
