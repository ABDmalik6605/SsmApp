package com.micromerger.ssms.main;

import android.content.Context;
import androidx.camera.camera2.Camera2Config;
import androidx.camera.core.CameraXConfig;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import com.androidnetworking.AndroidNetworking;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.utils.util;
import net.sqlcipher.database.SQLiteDatabase;

/* loaded from: classes2.dex */
public class SSMS extends MultiDexApplication implements CameraXConfig.Provider {
    private static BaseFragment currentFragment;
    private static Context mContext;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
        mContext = getApplicationContext();
        SQLiteDatabase.loadLibs(getApplicationContext());
        AndroidNetworking.initialize(getApplicationContext());
        util.logException(new Exception("App Started"));
    }

    @Override // androidx.multidex.MultiDexApplication, android.content.ContextWrapper
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    public static void setCurrentFragment(BaseFragment fragment) {
        currentFragment = fragment;
    }

    @Override // androidx.camera.core.CameraXConfig.Provider
    public CameraXConfig getCameraXConfig() {
        return CameraXConfig.Builder.fromConfig(Camera2Config.defaultConfig()).setMinimumLoggingLevel(6).build();
    }

    @Override // android.app.Application
    public void onTerminate() {
        util.logException(new Exception("App Terminated"));
        super.onTerminate();
    }
}
