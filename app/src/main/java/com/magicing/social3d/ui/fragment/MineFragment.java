package com.magicing.social3d.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magicing.social3d.R;
import com.magicing.social3d.presenter.MessagePresenter;
import com.magicing.social3d.presenter.MinePresenter;
import com.magicing.social3d.presenter.view.IMessageView;
import com.magicing.social3d.presenter.view.IMineView;

public class MineFragment extends BaseFragment implements IMineView{



    private View view;
    private MinePresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init(){
        mPresenter = new MinePresenter(getActivity());
        mPresenter.attachView(this);
    }
}
