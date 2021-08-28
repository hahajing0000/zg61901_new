package com.zy.home.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.databinding.BindingAdapter;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.home.adapter
 * @ClassName: ImageViewAdapter
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/28 17:11
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/28 17:11
 * @UpdateRemark:
 * @Version: 1.0
 */
public class ImageViewAdapter {

    @BindingAdapter({"imgPath"})
    public static void setImageViewImg(ImageView imageView,String imgPath){
        Glide.with(imageView.getContext()).load(imgPath).into(imageView);
    }
}
