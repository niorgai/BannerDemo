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
