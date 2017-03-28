package com.magicing.social3d;

import android.app.Application;


/**
 * Created by Administrator.
 */
public class Social3DApp extends Application {

    public static Social3DApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

}
