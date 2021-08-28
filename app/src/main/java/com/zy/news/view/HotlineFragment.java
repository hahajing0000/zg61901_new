package com.zy.news.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zy.news.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.news.view
 * @ClassName: HotlineFragment
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/27 17:52
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/27 17:52
 * @UpdateRemark:
 * @Version: 1.0
 */
public class HotlineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hotline, null);
        return view;
    }
}
