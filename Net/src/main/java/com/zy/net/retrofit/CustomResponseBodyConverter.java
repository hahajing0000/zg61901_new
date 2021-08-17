package com.zy.net.retrofit;

import com.google.gson.Gson;
import com.zy.net.protocol.BaseRespEntity;
import com.zy.net.protocol.TokenRespEntity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.net.retrofit
 * @ClassName: CustomResponseBodyConverter
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/17 15:10
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/17 15:10
 * @UpdateRemark:
 * @Version: 1.0
 */
public class CustomResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    @Override
    public T convert(ResponseBody value) throws IOException {
        String jsonContent = value.string();
        if (jsonContent.contains("access_")){
           return (T) new Gson().fromJson(jsonContent, TokenRespEntity.class);
        }
        BaseRespEntity entity = new Gson().fromJson(jsonContent, BaseRespEntity.class);
        return (T) entity;
    }
}
