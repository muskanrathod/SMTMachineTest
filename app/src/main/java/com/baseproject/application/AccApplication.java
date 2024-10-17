package com.baseproject.application;

import android.app.Application;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class AccApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

    }
}
