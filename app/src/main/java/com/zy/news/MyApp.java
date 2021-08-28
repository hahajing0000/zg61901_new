package com.zy.news;

import android.os.Build;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.bugly.crashreport.CrashReport;
import com.zy.owncommon.MyApplication;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.news
 * @ClassName: MyApp
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/27 10:50
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/27 10:50
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MyApp extends MyApplication {
    @Override
    public void onCreate() {
        super.onCreate();
//        7c3c612e60
        CrashReport.initCrashReport(getApplicationContext(), "7c3c612e60", BuildConfig.DEBUG);
        if (BuildConfig.DEBUG){
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);

    }
}
