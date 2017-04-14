package com.magicing.social3d.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magicing.social3d.R;
import com.magicing.social3d.presenter.ExplorePresenter;
import com.magicing.social3d.presenter.MessagePresenter;
import com.magicing.social3d.presenter.view.IExploreView;
import com.magicing.social3d.presenter.view.IMessageView;

public class MessageFragment extends BaseFragment implements IMessageView{



    private View view;
    private MessagePresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init(){
        mPresenter = new MessagePresenter(getActivity());
        mPresenter.attachView(this);
    }
}
