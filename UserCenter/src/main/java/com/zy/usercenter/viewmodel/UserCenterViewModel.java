package com.zy.usercenter.viewmodel;

import com.zy.mvvmcore.viewmodel.BaseViewModel;
import com.zy.net.protocol.BaseRespEntity;
import com.zy.usercenter.model.entity.UserEntity;
import com.zy.usercenter.repository.UserCenterRepository;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.usercenter.viewmodel
 * @ClassName: UserCenterViewModel
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/28 17:30
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/28 17:30
 * @UpdateRemark:
 * @Version: 1.0
 */
public class UserCenterViewModel extends BaseViewModel<UserCenterRepository> {
    public MutableLiveData<UserEntity> dataSource=new MutableLiveData<>();
    public UserCenterViewModel(LifecycleOwner owner) {
        super(owner);
        dataSource.setValue(new UserEntity());
    }

    @Override
    protected UserCenterRepository createRepository() {
        return new UserCenterRepository();
    }

    public LiveData<BaseRespEntity<UserEntity>> login(UserEntity entity){
        return mRepository.login(entity);
    }

    public LiveData<BaseRespEntity<UserEntity>> register(UserEntity entity){
        return mRepository.register(entity);
    }
}
