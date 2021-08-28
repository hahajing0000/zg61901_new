package com.zy.usercenter.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zy.mvvmcore.view.BaseMVVMActivity;
import com.zy.net.protocol.BaseRespEntity;
import com.zy.usercenter.BR;
import com.zy.usercenter.R;
import com.zy.usercenter.databinding.ActivityRegisterBinding;
import com.zy.usercenter.model.entity.UserEntity;
import com.zy.usercenter.viewmodel.UserCenterViewModel;

import java.util.HashMap;

@Route(path = "/usercenter/register")
public class RegisterActivity extends BaseMVVMActivity<UserCenterViewModel, ActivityRegisterBinding> {

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected UserCenterViewModel createViewModel() {
        return new UserCenterViewModel(this);
    }

    @Override
    protected void prepareSetVars(HashMap<Integer, Object> mMap) {
        mMap.put(BR.viewmodel,mViewModel);
        mMap.put(BR.mine,this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }


    public void onRegisterClick(View view){
        UserEntity value = mViewModel.dataSource.getValue();
        if (null==value){
            showMsg(getResources().getString(R.string.usercenter_register_databindfailed));
            return;
        }

        if (TextUtils.isEmpty(value.getUsername())){
            showMsg(getResources().getString(R.string.usercenter_register_inputusername));
            return;
        }
        if (TextUtils.isEmpty(value.getPwd())){
            showMsg(getResources().getString(R.string.usercenter_register_inputpassword));
        }
        if (TextUtils.isEmpty(value.getPwd2())){
            showMsg(getResources().getString(R.string.usercenter_register_inputpasswordagain));
            return;
        }

        if (!value.getPwd().equals(value.getPwd2())){
            showMsg(getResources().getString(R.string.usercenter_register_twoinputpwdisnotsame));
            return;
        }
        mViewModel.register(value).observe(this, new Observer<BaseRespEntity<UserEntity>>() {
            @Override
            public void onChanged(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
                if (userEntityBaseRespEntity.getCode()>0){
                    showMsg(getResources().getString(R.string.usercenter_register_success));
                    finish();
                }
                else {
                    showMsg(getResources().getString(R.string.usercenter_register_failed));
                }
            }
        });
    }
}