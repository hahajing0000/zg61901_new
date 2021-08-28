package com.zy.home.repository;

import com.zy.common.utils.NetUtil;
import com.zy.home.model.entity.NewsInfoEntitiy;
import com.zy.home.model.service.NewsRemoteModel;
import com.zy.mvvmcore.model.Model;
import com.zy.mvvmcore.repository.BaseRepository;
import com.zy.net.protocol.BaseRespEntity;
import com.zy.owncommon.MyApplication;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.home.repository
 * @ClassName: NewsRepository
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/28 8:24
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/28 8:24
 * @UpdateRemark:
 * @Version: 1.0
 */
public class NewsRepository extends BaseRepository {

    @Model
    private NewsRemoteModel remoteModel;

    /**
     * 根据新闻类别获取新闻列表
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/28 8:24
     */
    public LiveData<BaseRespEntity<List<NewsInfoEntitiy>>> getNewsByType(int newstype, int pagenum, int pagesize){
        if (NetUtil.isNetworkAvailable(MyApplication.getAppContext())){
            return remoteModel.getNewsByType(newstype,pagenum,pagesize);
        }else{
            throw new IllegalStateException("网络不可用，还未实现LocalModel");
        }
    }

}
