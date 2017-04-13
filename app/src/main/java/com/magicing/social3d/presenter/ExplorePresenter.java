package com.magicing.social3d.presenter;

import android.content.Context;
import com.magicing.social3d.presenter.view.IExploreView;
import com.magicing.social3d.ui.adapter.HomeListAdapter;

public class ExplorePresenter extends Presenter{

    private Context mContext;
    private IExploreView mView;
    private HomeListAdapter mAdapter;


    public ExplorePresenter(Context mContext){
        this.mContext = mContext;
    }

    public void attachView(IExploreView view){
        this.mView = view;
    }



}
