package com.magicing.social3d.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AbsListView;
import android.widget.ListView;

import com.magicing.social3d.R;
import com.magicing.social3d.presenter.MainPresenter;
import com.magicing.social3d.presenter.view.IMainlView;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements IMainlView{

    @BindView(R.id.listview)
    ListView mListView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private MainPresenter mPresenter;
    private boolean isLoadmore;
    private int page = 1;

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
        initView();
        mPresenter.onLoadData(page, true);
    }


    private  void initView(){
        mPresenter = new MainPresenter(this);
        mPresenter.attachView(this);

        mPresenter.setListView(mListView);
        //添加listview 滚动监听，用于滑到差不多最底部，自动加载更多的数据
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (mListView.getLastVisiblePosition() == (totalItemCount - 1)) {

                    if(totalItemCount > 9 && !isLoadmore){
                        swipeRefreshLayout.setRefreshing(true);
                        isLoadmore = true;
                        mPresenter.onLoadData(++page, false);
                    }

                }
            }
        });

        //下拉刷新监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.onLoadData(page, true);
            }
        });
    }


    @Override
    public void loadFinish(boolean isSuccess) {
        swipeRefreshLayout.setRefreshing(false);
        isLoadmore = false;
    }
}
