package com.zy.owncommon;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.common
 * @ClassName: MyApplication
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/27 7:47
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/27 7:47
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MyApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this.getApplicationContext();

    }

    /**
     * 获取Application上下文
     * @return
     */
    public static Context getAppContext(){
        return mContext;
    }
}
