package com.zy.net.retrofit;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.net.retrofit
 * @ClassName: DefaultCallAdapter
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/17 14:49
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/17 14:49
 * @UpdateRemark:
 * @Version: 1.0
 */
public class DefaultCallAdapter<R> implements CallAdapter<R,Object> {
    Type mType=null;
    public DefaultCallAdapter(Type _t){
        mType=_t;
    }

    @Override
    public Type responseType() {
        return mType;
    }

    @Override
    public Object adapt(Call<R> call) {
        return call;
    }
}
