package com.zy.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.zy.home.view.HomeFragment;
import com.zy.news.view.HotlineFragment;
import com.zy.news.view.VideoFragment;
import com.zy.usercenter.view.MineFragment;

import java.util.ArrayList;

@Route(path = "/main/main")
public class MainActivity extends AppCompatActivity {
    private ViewPager vpMain;
    private BottomNavigationView bnvMain;
    private Button btnCrash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }


    /**
     * 初始化ViewPager
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/8/27 18:19
     */ 
    private void initViewPager(ViewPager vpMain) {
        final ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new VideoFragment());
        fragments.add(new HotlineFragment());
        fragments.add(new MineFragment());
        FragmentStatePagerAdapter adapter=new FragmentStatePagerAdapter(getSupportFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        vpMain.setAdapter(adapter);
        vpMain.setCurrentItem(0);
    }

    /**
     * 事件处理
     */
    private void initEvent() {
        bnvMain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bottom_item_home:
                        vpMain.setCurrentItem(0);
                        break;
                    case R.id.bottom_item_video:
                        vpMain.setCurrentItem(1);
                        break;
                    case R.id.bottom_item_hotline:
                        vpMain.setCurrentItem(2);
                        break;
                    case R.id.bottom_item_mine:
                        vpMain.setCurrentItem(3);
                        break;
                }
                return false;
            }
        });

        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bnvMain.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        vpMain = (ViewPager) findViewById(R.id.vp_main);
        bnvMain = (BottomNavigationView) findViewById(R.id.bnv_main);

        bnvMain.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        initViewPager(vpMain);
    }


}