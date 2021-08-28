package com.zy.home.adapter;

import com.zy.home.common.recyclerview.BaseRecyclerViewAdapter;
import com.zy.home.common.recyclerview.BindingRecyclerView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.home.common.recyclerview
 * @ClassName: RecyclerViewBinding
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/28 9:15
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/28 9:15
 * @UpdateRemark:
 * @Version: 1.0
 */
public class RecyclerViewAdapter {
    @BindingAdapter(value = {"adapter"},requireAll = false)
    public static <T> void setAdapter(BindingRecyclerView recyclerView, BaseRecyclerViewAdapter adapter){

        if (recyclerView.getLayoutManager()==null){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
            recyclerView.addItemDecoration(dividerItemDecoration);
        }
        recyclerView.setBindingAdapter(adapter);

    }
}
