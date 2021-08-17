package com.zy.usercenter.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import retrofit2.Retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zy.net.RetrofitFactory;
import com.zy.net.protocol.BaseRespEntity;
import com.zy.usercenter.R;
import com.zy.usercenter.api.UserCenter;
import com.zy.usercenter.databinding.MyBinding;
import com.zy.usercenter.entity.UserEntity;

public class LoginActivity extends AppCompatActivity {
    public ObservableField<UserEntity> pageDataSource=new ObservableField<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageDataSource.set(new UserEntity());
        MyBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.setMine(this);
    }

    public void login(View view, ObservableField<UserEntity> entity){
        UserCenter api = RetrofitFactory.getInstance().create(UserCenter.class);
        UserEntity userEntity = entity.get();
        LiveData<BaseRespEntity<UserEntity>> result = api.login(entity.get());
        result.observe(this, new Observer<BaseRespEntity<UserEntity>>() {
            @Override
            public void onChanged(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
                Log.d("123", "onChanged: "+userEntityBaseRespEntity.toString());
            }
        });
    }
}