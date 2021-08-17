package com.zy.net.retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.net.retrofit
 * @ClassName: LiveDataCallAdapterFactory
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/17 14:05
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/17 14:05
 * @UpdateRemark:
 * @Version: 1.0
 */
public class LiveDataCallAdapterFactory extends CallAdapter.Factory  {

    public static LiveDataCallAdapterFactory create(){
        return new LiveDataCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (!(returnType instanceof ParameterizedType)){
            throw new IllegalArgumentException("要求返回值必须是可参数化的（支持泛型）");
        }
        Class<?> returnTypeClazz = CallAdapter.Factory.getRawType(returnType);
        if (returnTypeClazz!= LiveData.class&&returnTypeClazz!= Call.class){
            throw new IllegalArgumentException("返回值类型必须是LiveData或者是Call");
        }
        Type t = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) returnType);
        if (returnTypeClazz==Call.class){
            return new DefaultCallAdapter<>(t);
        }else if (returnTypeClazz==LiveData.class){
            return new LiveDataCallAdapter<>(t);
        }
        return new DefaultCallAdapter<>(t);
    }
}
