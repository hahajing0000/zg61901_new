package com.zy.net.retrofit;

import com.google.gson.Gson;
import com.zy.net.protocol.BaseRespEntity;
import com.zy.net.protocol.TokenRespEntity;

import java.io.IOException;
import java.lang.reflect.Type;

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
    private Type mType;

    public CustomResponseBodyConverter(Type _type) {
        mType = _type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String jsonContent = value.string();
        if (jsonContent.contains("access_")) {
            return (T) new Gson().fromJson(jsonContent, TokenRespEntity.class);
        }
        Gson gson = new Gson();
        BaseRespEntity temp_result = gson.fromJson(jsonContent, BaseRespEntity.class);
        if (temp_result.getCode() <= 0) {
            return (T) temp_result;
        } else {
            return gson.fromJson(jsonContent, mType);
        }

    }
}
