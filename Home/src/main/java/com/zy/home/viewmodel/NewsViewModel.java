package com.zy.home.viewmodel;

import com.zy.home.model.entity.NewsInfoEntitiy;
import com.zy.home.repository.NewsRepository;
import com.zy.mvvmcore.viewmodel.BaseViewModel;
import com.zy.net.protocol.BaseRespEntity;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.home.viewmodel
 * @ClassName: NewsViewModel
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/28 8:27
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/28 8:27
 * @UpdateRemark:
 * @Version: 1.0
 */
public class NewsViewModel extends BaseViewModel<NewsRepository> {
    public NewsViewModel(LifecycleOwner owner) {
        super(owner);
    }

    @Override
    protected NewsRepository createRepository() {
        return new NewsRepository();
    }

    public LiveData<BaseRespEntity<List<NewsInfoEntitiy>>> getNewsByType(int newstype, int pagenum, int pagesize){
        return mRepository.getNewsByType(newstype, pagenum, pagesize);
    }

}
