package com.zy.usercenter.view;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zy.mvvmcore.BR;
import com.zy.mvvmcore.view.BaseMVVMActivity;
import com.zy.net.RetrofitFactory;
import com.zy.net.protocol.BaseRespEntity;
import com.zy.owncommon.route.RoutePathConstant;
import com.zy.usercenter.R;
import com.zy.usercenter.databinding.MyBinding;
import com.zy.usercenter.model.api.UserCenterApi;
import com.zy.usercenter.model.entity.UserEntity;
import com.zy.usercenter.viewmodel.UserCenterViewModel;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

@Route(path = "/usercenter/login")
public class LoginActivity extends BaseMVVMActivity<UserCenterViewModel,MyBinding> {
    public ObservableField<UserEntity> pageDataSource=new ObservableField<>();

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadData() {
        pageDataSource.set(new UserEntity());
    }

    @Override
    protected UserCenterViewModel createViewModel() {
        return new UserCenterViewModel(this);
    }

    @Override
    protected void prepareSetVars(HashMap<Integer, Object> mMap) {
        mMap.put(BR.mine,this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    public void login(View view, ObservableField<UserEntity> entity){
        UserCenterApi api = RetrofitFactory.getInstance().create(UserCenterApi.class);
        UserEntity userEntity = entity.get();
        LiveData<BaseRespEntity<UserEntity>> result = api.login(entity.get());
        result.observe(this, new Observer<BaseRespEntity<UserEntity>>() {
            @Override
            public void onChanged(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
                Log.d("123", "onChanged: "+userEntityBaseRespEntity.toString());
                if (userEntityBaseRespEntity.getCode()<0){
                    showMsg(getResources().getString(R.string.usercenter_userpwderror));
                }
                else{
                    ARouter.getInstance().build(RoutePathConstant.MAIN_MAIN).navigation();
                }

            }
        });
    }

    @BindingAdapter({"spanText"})
    public static void setSpanTextView(TextView textView, String spanText){
        SpannableStringBuilder spannable=new SpannableStringBuilder(spanText);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        spannable.setSpan(new TextClickAndChangedColor(),6,10,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannable);
        textView.setHighlightColor(Color.parseColor("#00ffffff"));

    }

    static class TextClickAndChangedColor extends ClickableSpan{

        @Override
        public void onClick(@NonNull View widget) {
            ARouter.getInstance().build(RoutePathConstant.USERCENTER_REGISTER).navigation();
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            ds.setColor(Color.BLUE);
        }
    }
}