package com.magicing.social3d.presenter;

import android.content.Context;

import com.magicing.social3d.presenter.view.IMessageView;
import com.magicing.social3d.presenter.view.IMineView;

public class MinePresenter extends Presenter{

    private Context mContext;
    private IMineView mView;


    public MinePresenter(Context mContext){
        this.mContext = mContext;
    }

    public void attachView(IMineView view){
        this.mView = view;
    }



}
