package com.zy.usercenter.api;

import com.zy.net.protocol.BaseRespEntity;
import com.zy.usercenter.entity.UserEntity;

import androidx.lifecycle.LiveData;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.usercenter.api
 * @ClassName: UserCenter
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/17 14:36
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/17 14:36
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface UserCenter {

    @POST("api/User/login")
    LiveData<BaseRespEntity<UserEntity>> login(@Body UserEntity entity);
}
