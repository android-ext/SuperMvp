package com.ly.supermvp;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.ly.supermvp.utils.CrashHandler;
import com.ly.supermvp.utils.ToastUtils;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.GINGERBREAD;

/**
 * <Pre>
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 10:47
 */
public class MyApplication extends Application {
    private static MyApplication instance;

    public static String cacheDir = "";
//    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init().logLevel(LogLevel.FULL);
        instance = (MyApplication) getApplicationContext();
//        this.enabledStrictMode();
        ToastUtils.register(this);
        //LeakCanary检测OOM
        LeakCanary.install(this);

        //初始化日志输出工具
        CrashHandler.init(new CrashHandler(getApplicationContext()));
        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */

        if (getApplicationContext().getExternalCacheDir() != null && isExistSDCard()) {
            cacheDir = getApplicationContext().getExternalCacheDir().toString();

        }
        else {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }
    }

    private boolean isExistSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        }
        else {
            return false;
        }
    }

    private void enabledStrictMode() {
        if (SDK_INT >= GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                    .detectAll()  //
                    .penaltyLog() //
                    .penaltyDeath() //
                    .build());
        }
    }
    // 获取ApplicationContext
    public static Context getContext() {
        return instance;
    }
}
