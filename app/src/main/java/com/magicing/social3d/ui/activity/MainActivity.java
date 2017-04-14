package com.magicing.social3d.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.magicing.social3d.R;
import com.magicing.social3d.presenter.MainPresenter;
import com.magicing.social3d.presenter.view.IMainlView;
import com.magicing.social3d.ui.fragment.ExploreFragment;
import com.magicing.social3d.ui.fragment.HomeFragment;
import com.magicing.social3d.ui.fragment.MessageFragment;
import com.magicing.social3d.ui.fragment.MineFragment;
import com.magicing.social3d.util.Utils;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements IMainlView, View.OnClickListener, RadioGroup.OnCheckedChangeListener{


    @BindView(R.id.tabBottom)
    RadioGroup mRadioGroup;
    private MainPresenter mPresenter;
    private Fragment mCurrentFragment;
    private long exitTime = 0;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.app_name));
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        initTittlebar();
        initView();
        initHostTab();

    }


    private  void initView(){
        mPresenter = new MainPresenter(this);
        mPresenter.attachView(this);
    }

    /**
     * 自定义titlebar，添加左边个人中心icon
     */
    private void initTittlebar(){
        ImageButton mapButtom = new ImageButton(this);
        mapButtom.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        mapButtom.setImageResource(R.mipmap.icon_map);
        mapButtom.setId(android.R.id.home);
        mapButtom.setOnClickListener(this);
        Toolbar mToolbar = getToolbar();
        mToolbar.addView(mapButtom,0);
    }

    /**
     * 初始化Tab切换页面
     */
    private void initHostTab(){
        mRadioGroup.setOnCheckedChangeListener(this);
        mCurrentFragment = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content, mCurrentFragment, "radio1");
        ft.commit();
    }

    /**
     * 通过标示Tag 切换 fragment
     * @param tag
     */
    private void transferFragment(String tag){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(mCurrentFragment != null){
            ft.detach(mCurrentFragment);
        }

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if(fragment != null){
            ft.attach(fragment);
            mCurrentFragment = fragment;
        }else if("radio2".equals(tag)){
            mCurrentFragment = new ExploreFragment();
            ft.add(R.id.content , mCurrentFragment, "radio2");
        }else if("radio4".equals(tag)){
            mCurrentFragment = new MessageFragment();
            ft.add(R.id.content ,mCurrentFragment, "radio4");
        }else if("radio5".equals(tag)){
            mCurrentFragment = new MineFragment();
            ft.add(R.id.content ,mCurrentFragment, "radio5");
        }

        ft.commit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Utils.toast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case android.R.id.home:
                Utils.toast("跳转到地图页面");
                break;
        }
    }

    @OnClick(R.id.photoBtn)
     void onClickPhotoBtn(){
        Utils.toast("跳转到拍照页面");
    }
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        switch (checkedId) {
            case R.id.radio1:
                transferFragment("radio1");
                break;
            case R.id.radio2:
                transferFragment("radio2");
                break;

            case R.id.radio4:
                transferFragment("radio4");
                break;
            case R.id.radio5:
                transferFragment("radio5");
                break;
        }
    }
}
