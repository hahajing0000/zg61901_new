package com.zy.net.retrofit;

import android.os.Looper;

import com.zy.net.protocol.BaseRespEntity;

import java.lang.reflect.Type;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.net.retrofit
 * @ClassName: LiveDataCallAdapter
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/17 14:28
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/17 14:28
 * @UpdateRemark:
 * @Version: 1.0
 */
public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<BaseRespEntity<R>>> {

    Type type;
    public LiveDataCallAdapter(Type _t){
        this.type=_t;
    }

    @Override
    public Type responseType() {
        return type;
    }

    @Override
    public LiveData<BaseRespEntity<R>> adapt(Call<R> call) {
        final MutableLiveData<BaseRespEntity<R>> liveData=new MutableLiveData<>();
        call.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    liveData.setValue((BaseRespEntity<R>) response.body());
                }else {
                    liveData.postValue((BaseRespEntity<R>) response.body());
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                BaseRespEntity entity=new BaseRespEntity();
                entity.setCode(-11);
                entity.setMsg(t.getMessage());
                if (Looper.getMainLooper().getThread()==Thread.currentThread()){

                    liveData.setValue(entity);
                }else {
                    liveData.postValue(entity);
                }
            }
        });
        return liveData;
    }
}
