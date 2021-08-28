package com.zy.usercenter.model.service;

import com.zy.mvvmcore.model.IModel;
import com.zy.net.RetrofitFactory;
import com.zy.net.protocol.BaseRespEntity;
import com.zy.usercenter.model.api.UserCenterApi;
import com.zy.usercenter.model.entity.UserEntity;

import androidx.lifecycle.LiveData;
import retrofit2.http.Body;


/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.usercenter.model.service
 * @ClassName: UserCenterModel
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/28 17:28
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/28 17:28
 * @UpdateRemark:
 * @Version: 1.0
 */
public class UserCenterModel implements IModel {
    /**
     * 用户登录
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/28 18:59
     */ 
    public LiveData<BaseRespEntity<UserEntity>> login(UserEntity entity){
        UserCenterApi userCenter = RetrofitFactory.getInstance().create(UserCenterApi.class);
        LiveData<BaseRespEntity<UserEntity>> result = userCenter.login(entity);
        return result;
    }

    /**
     * 用户注册
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/8/28 18:59
     */ 
    public LiveData<BaseRespEntity<UserEntity>> register(UserEntity entity){
        return RetrofitFactory.getInstance().create(UserCenterApi.class).register(entity);
    }
}
