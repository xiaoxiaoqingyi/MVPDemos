package com.magicing.social3d.presenter;

import android.content.Context;

import com.magicing.social3d.presenter.view.IExploreView;
import com.magicing.social3d.presenter.view.IMessageView;
import com.magicing.social3d.ui.adapter.HomeListAdapter;

public class MessagePresenter extends Presenter{

    private Context mContext;
    private IMessageView mView;


    public MessagePresenter(Context mContext){
        this.mContext = mContext;
    }

    public void attachView(IMessageView view){
        this.mView = view;
    }



}
