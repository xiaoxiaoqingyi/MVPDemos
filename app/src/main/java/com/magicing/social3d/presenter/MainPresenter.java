package com.magicing.social3d.presenter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.magicing.social3d.model.bean.Note;
import com.magicing.social3d.presenter.view.IMainlView;
import com.magicing.social3d.ui.adapter.HomeListAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/03/5.
 */
public class MainPresenter extends Presenter {
    private Activity mContext;
    private IMainlView mView;

    private RecyclerView mRecyclerView;
    private List<Note> mNoteList = new ArrayList<Note>();
    private HomeListAdapter mAdapter;

    public MainPresenter(Activity mContext){
        this.mContext = mContext;
    }

    /**
     * 关联Activity， 让activity 处理所有UI操作
     * @param view
     */
    public void attachView(IMainlView view){
        this.mView = view;
    }



}
