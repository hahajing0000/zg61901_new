package com.zy.net;

import android.text.TextUtils;
import android.util.Log;

import com.zy.net.api.TokenApi;
import com.zy.net.common.Config;
import com.zy.net.protocol.TokenRespEntity;
import com.zy.net.retrofit.CusomGsonConverterFactory;
import com.zy.net.retrofit.LiveDataCallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.net
 * @ClassName: RetrofitFactory
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/17 13:38
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/17 13:38
 * @UpdateRemark:
 * @Version: 1.0
 */
public class RetrofitFactory {
    private Retrofit retrofit;
    private RetrofitFactory(){
        retrofit=createRetrofit();
    }
    private volatile static RetrofitFactory instance=null;
    public synchronized static RetrofitFactory getInstance(){
        if (null==instance){
            synchronized (RetrofitFactory.class){
                if (null==instance){
                    instance=new RetrofitFactory();
                }
            }
        }
        return instance;
    }


    private String mToken="";

    /**
     * 创建Retrofit的实例
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/17 13:46
     */
    private Retrofit createRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .addConverterFactory(CusomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .client(createOkHttpClient())
                .build();
        return retrofit;
    }

    /**
     * 创建OkHttp的Client实例
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/17 13:48
     */
    private OkHttpClient createOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(Config.TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Config.TIMEOUT,TimeUnit.SECONDS)
                .connectTimeout(Config.TIMEOUT,TimeUnit.SECONDS)
                .addInterceptor(createTokenInterceptor())
                .addNetworkInterceptor(createNetInterceptor())
                .build();

        return okHttpClient;
    }

    /**
     * 创建网络拦截器
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/17 13:49
     */
    private Interceptor createNetInterceptor() {
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }


    /**
     * Token处理的拦截器
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/17 13:52
     */
    private Interceptor createTokenInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                //获取本地Token
                String localToken = mToken;//StorageManager.getInstance().get("token");
                if (!TextUtils.isEmpty(localToken)){
                    return resetRequest(request,localToken,chain);
                }

                Response response = chain.proceed(request);

                //如果是401 同步请求Token然后加载到新请求的Header里，重新发起业务请求
                if (checkHttpCode401(response)){
                    String token=requestToken();
                    if (TextUtils.isEmpty(token)){
                        return response;
                    }

                    //TODO:保存Token 到SP
                    //StorageManager.getInstance().save("token",token);
                    mToken=token;

                    return resetRequest(request,token,chain);
                }
                return response;
            }
        };

        return interceptor;
    }

    /**
     * 重置请求
     * @param request 请求
     * @param token token令牌
     * @param chain Okhttp的执行链
     * @return
     */
    private Response resetRequest(Request request,String token,Interceptor.Chain chain){
        Request.Builder newBuilder = request.newBuilder().addHeader("Authorization", "bearer " + token);

        Request newRequest=newBuilder.build();
        try {
            return chain.proceed(newRequest);
        } catch (IOException e) {
            Log.e("123", "error info:"+e.getMessage());
        }
        return null;
    }



    /**
     * 判断HTTP CODE 是否401 —— TOKEN失效
     * @param response
     * @return
     */
    private boolean checkHttpCode401(Response response) {
        if (null==response){
            return false;
        }

        if (response.code()==401){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 获取Token的同步网络请求
     * @return
     */
    private String requestToken() {

        TokenApi tokenApi = create(TokenApi.class);
        Call<TokenRespEntity> tokenService = tokenApi.getToken("password", Config.AUTH_CODE, "");
        try {
            retrofit2.Response<TokenRespEntity> result = tokenService.execute();
            if (result!=null&&result.body()!=null){
                return  result.body().getAccess_token();
            }
        } catch (IOException e) {
            Log.e("123", "error info:"+e.getMessage());
        }
        return "";
    }

    /**
     * 通过api接口获取到其实例
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/8/17 13:46
     */ 
    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }

}
