package com.zy.home.common.recyclerview;

import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.home.common.recyclerview
 * @ClassName: BaseViewHolder
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/28 8:50
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/28 8:50
 * @UpdateRemark:
 * @Version: 1.0
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    ViewDataBinding mBinding;
    private SparseArray<Object> variables=new SparseArray<>();
    public BaseViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        mBinding=binding;
    }

    /**
     * 绑定子视图
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/8/28 9:02
     */ 
    public void bind(int position){
        initVariables(variables);
        setVariables(mBinding,variables);
        mBinding.executePendingBindings();
    }

    /**
     * 设置变量
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/28 9:02
     */ 
    private void setVariables(ViewDataBinding mBinding, SparseArray<Object> variables) {
        if (null!=variables&&variables.size()>0){
            for (int i=0;i<variables.size();i++){
                int br = variables.keyAt(i);
                Object value = variables.valueAt(i);
                mBinding.setVariable(br,value);
            }
        }
    }

    /**
     * 初始化变量
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/28 9:02
     */ 
    protected void initVariables(SparseArray<Object> _variables){
        this.variables=_variables;
    }
}
