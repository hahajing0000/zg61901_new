package com.zy.home.model.service;

import com.zy.home.model.api.NewsApi;
import com.zy.home.model.entity.NewsInfoEntitiy;
import com.zy.mvvmcore.model.IModel;
import com.zy.net.RetrofitFactory;
import com.zy.net.protocol.BaseRespEntity;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.home.model.service
 * @ClassName: NewsRemoteModel
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/28 8:21
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/28 8:21
 * @UpdateRemark:
 * @Version: 1.0
 */
public class NewsRemoteModel implements IModel {
    /**
     * 根据新闻类别获取新闻列表
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/28 8:24
     */
    public LiveData<BaseRespEntity<List<NewsInfoEntitiy>>> getNewsByType(int newstype, int pagenum, int pagesize){
        LiveData<BaseRespEntity<List<NewsInfoEntitiy>>> result = RetrofitFactory.getInstance().create(NewsApi.class).getNews(newstype, pagenum, pagesize);
        return result;
    }
}
