package com.magicing.social3d.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.magicing.social3d.R;
import com.magicing.social3d.presenter.HomePresenter;
import com.magicing.social3d.presenter.MainPresenter;
import com.magicing.social3d.presenter.view.IHomeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements IHomeView {


    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private boolean isLoadmore;
    private int page = 1;


    private View view;
    private HomePresenter mPresenter;
    private int type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        mPresenter.onLoadData(page, true);
    }

    private void init(){
        ButterKnife.bind(this, view);

        mPresenter = new HomePresenter(getActivity());
        mPresenter.attachView(this);

        mPresenter.setRecyclerView(mRecyclerView);

        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.scrollToPosition(0);
        //添加RecyclerView 滚动监听，用于滑到差不多最底部，自动加载更多的数据
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    int lastVisiblePosition = manager.findLastVisibleItemPosition();
                    if(lastVisiblePosition >= manager.getItemCount() - 1){
                        swipeRefreshLayout.setRefreshing(true);
                        isLoadmore = true;
                        mPresenter.onLoadData(++page, false);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
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
