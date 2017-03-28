package com.magicing.social3d.presenter;

import android.content.Context;
import android.widget.ListView;

import com.magicing.social3d.presenter.view.IMainlView;

/**
 * Created by Administrator on 2016/7/15.
 */
public class MainPresenter extends Presenter {
    private Context mContext;
    private IMainlView mView;

    private ListView mListView;

    public MainPresenter(Context mContext){
        this.mContext = mContext;
    }

    /**
     * 关联Activity， 让activity 处理所有UI操作
     * @param view
     */
    public void attachView(IMainlView view){
        this.mView = view;
    }

    public void setListView(ListView listView) {
        this.mListView = listView;
    }

    /**
     * 网络加载数据
     * @param page  第几页
     * @param isPull  true:是下拉刷新, false: 上拉加载更多
     */
    public void onLoadData(int page, final boolean isPull){

    }

}
