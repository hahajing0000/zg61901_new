package com.zy.home.view;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.MotionEvent;

import com.zy.common.utils.ThreadUtils;
import com.zy.home.BR;
import com.zy.home.R;
import com.zy.home.common.recyclerview.BaseRecyclerViewAdapter;
import com.zy.home.common.recyclerview.BaseViewHolder;
import com.zy.home.databinding.FragmentHomeBinding;
import com.zy.home.model.entity.NewsInfoEntitiy;
import com.zy.home.viewmodel.NewsViewModel;
import com.zy.mvvmcore.view.BaseMVVMFragment;
import com.zy.net.protocol.BaseRespEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.home
 * @ClassName: HomeFragment
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/27 18:01
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/27 18:01
 * @UpdateRemark:
 * @Version: 1.0
 */
public class HomeFragment extends BaseMVVMFragment<NewsViewModel, FragmentHomeBinding> {
    private MutableLiveData<List<NewsInfoEntitiy>> datas=new MutableLiveData<>();
    public BaseRecyclerViewAdapter<NewsInfoEntitiy> adapter;

    @Override
    protected void initEvent() {
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        datas.setValue(new ArrayList<NewsInfoEntitiy>());
        LiveData<BaseRespEntity<List<NewsInfoEntitiy>>> result = mViewModel.getNewsByType(1, 0, 20);
        result.observe(this, new Observer<BaseRespEntity<List<NewsInfoEntitiy>>>() {
            @Override
            public void onChanged(BaseRespEntity<List<NewsInfoEntitiy>> listBaseRespEntity) {
               if (ThreadUtils.isMainThread()){
                   datas.setValue(listBaseRespEntity.getData());
               }
               else{
                   datas.postValue(listBaseRespEntity.getData());
               }
            }
        });

        adapter = new BaseRecyclerViewAdapter<NewsInfoEntitiy>(getViewLifecycleOwner(),datas) {
            @Override
            protected BaseViewHolder createHolder(ViewDataBinding binding) {
                BaseViewHolder viewHolder = new BaseViewHolder(binding);
                return viewHolder;
            }

            @Override
            protected int getLayoutId() {
                return R.layout.item_news;
            }

            @Override
            protected SparseArray<Object> setVariables(int position) {
                List<NewsInfoEntitiy> value = datas.getValue();
                NewsInfoEntitiy newsInfoEntitiy = datas.getValue().get(position);
                SparseArray<Object> sparseArray=new SparseArray<>();
                sparseArray.put(BR.item,newsInfoEntitiy);
                return sparseArray;
            }
        };
    }

    @Override
    protected NewsViewModel createViewModel() {
        return new NewsViewModel(this);
    }

    @Override
    protected void prepareSetVars(HashMap<Integer, Object> mMap) {
        mMap.put(BR.datasource,mViewModel);
        mMap.put(BR.own,this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    public RecyclerView.OnItemTouchListener onItemTouchListener=new RecyclerView.OnItemTouchListener() {
        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    };

}
