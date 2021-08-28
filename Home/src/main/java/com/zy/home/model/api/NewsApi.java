package com.zy.home.model.api;

import com.zy.home.model.entity.NewsInfoEntitiy;
import com.zy.net.protocol.BaseRespEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.home.model.api
 * @ClassName: NewsApi
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/28 8:18
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/28 8:18
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface NewsApi {
    @GET(value = "api/News/getNews?")
    LiveData<BaseRespEntity<List<NewsInfoEntitiy>>> getNews(@Query("newstype") int newstype, @Query("pagenum") int pagenum, @Query("pagesize") int pagesize);
}
