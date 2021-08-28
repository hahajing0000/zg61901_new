package com.zy.home.common.recyclerview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.home.common.recyclerview
 * @ClassName: BindingRecyclerView
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/28 9:15
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/28 9:15
 * @UpdateRemark:
 * @Version: 1.0
 */
public class BindingRecyclerView extends RecyclerView {
    public BindingRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public <T> void setBindingAdapter(BaseRecyclerViewAdapter adapter){
        setAdapter(adapter);

    }


}
