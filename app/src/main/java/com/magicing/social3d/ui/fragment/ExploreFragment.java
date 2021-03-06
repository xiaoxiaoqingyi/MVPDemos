package com.magicing.social3d.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magicing.social3d.R;
import com.magicing.social3d.presenter.ExplorePresenter;
import com.magicing.social3d.presenter.HomePresenter;
import com.magicing.social3d.presenter.view.IExploreView;
import com.magicing.social3d.presenter.view.IHomeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExploreFragment extends BaseFragment implements IExploreView{



    private View view;
    private ExplorePresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_explore, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init(){
        mPresenter = new ExplorePresenter(getActivity());
        mPresenter.attachView(this);
    }
}
