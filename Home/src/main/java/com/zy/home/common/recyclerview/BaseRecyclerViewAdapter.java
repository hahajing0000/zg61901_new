package com.zy.home.common.recyclerview;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.home.common.recyclerview
 * @ClassName: BaseRecyclerViewAdapter
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/28 9:03
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/28 9:03
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private MutableLiveData<List<T>> dataSource;
    private LifecycleOwner owner;
    public BaseRecyclerViewAdapter(LifecycleOwner _owner,MutableLiveData<List<T>> _dataSource){
        owner=_owner;
        dataSource=_dataSource;

        setDataChangedListener(dataSource);
    }


    /**
     * 添加数据变化监听
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/28 9:05
     */
    private void setDataChangedListener(final MutableLiveData<List<T>> dataSource) {
        dataSource.observe(owner, new Observer<List<T>>() {
            @Override
            public void onChanged(List<T> t) {
//                dataSource.setValue(t);
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutId(), parent, false);
        return createHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.initVariables(setVariables(position));
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return dataSource.getValue().size();
    }

    /**
     * 创建ViewHolder
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/28 9:11
     */
    protected abstract BaseViewHolder createHolder(ViewDataBinding binding);

    /**
     * 设置布局id
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/28 9:11
     */
    protected abstract int getLayoutId();

    protected abstract SparseArray<Object> setVariables(int position);
}
