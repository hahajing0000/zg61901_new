package com.zy.usercenter.repository;

import com.zy.mvvmcore.model.Model;
import com.zy.mvvmcore.repository.BaseRepository;
import com.zy.net.protocol.BaseRespEntity;
import com.zy.usercenter.model.entity.UserEntity;
import com.zy.usercenter.model.service.UserCenterModel;

import androidx.lifecycle.LiveData;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.usercenter.repository
 * @ClassName: UserCenterRepository
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/28 17:29
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/28 17:29
 * @UpdateRemark:
 * @Version: 1.0
 */
public class UserCenterRepository extends BaseRepository {

    @Model
    private UserCenterModel model;

    public LiveData<BaseRespEntity<UserEntity>> login(UserEntity entity){
        return model.login(entity);
    }

    public LiveData<BaseRespEntity<UserEntity>> register(UserEntity entity){
        return model.register(entity);
    }
}
