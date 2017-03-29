package com.magicing.social3d.presenter;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

import com.magicing.social3d.model.bean.ListResult;
import com.magicing.social3d.model.bean.Note;
import com.magicing.social3d.model.dao.ApiService;
import com.magicing.social3d.presenter.view.IMainlView;
import com.magicing.social3d.ui.adapter.MainListViewAdapter;
import com.magicing.social3d.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Administrator on 2016/7/15.
 */
public class MainPresenter extends Presenter {
    private Activity mContext;
    private IMainlView mView;

    private ListView mListView;
    private List<Note> mNoteList = new ArrayList<Note>();
    private MainListViewAdapter mAdapter;

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

    public void setListView(ListView listView) {
        this.mListView = listView;
    }

    /**
     * 网络加载数据
     * @param page  第几页
     * @param isPull  true:是下拉刷新, false: 上拉加载更多
     */
    public void onLoadData(int page, final boolean isPull){
        Map map = new HashMap<String, Object>();
        map.put("page",page);
        ApiService.getInstance().getNoteList(Utils.map2Json(map))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListResult<Note>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListResult<Note> result) {
                        if(result.getStatus() == 200 && result.getResult().size() > 0){
                            if(isPull){ //下拉刷新
                                mNoteList.clear();
                                mNoteList = result.getResult();
                                if(mAdapter == null){
                                    mAdapter = new MainListViewAdapter(mContext, mNoteList);
                                    mListView.setAdapter(mAdapter);
                                }else {
                                    mAdapter.setmNoteList(mNoteList);
                                }
                                mAdapter.notifyDataSetChanged();
                            }else{//上拉加载更多
                                mNoteList.addAll(result.getResult());
                                mAdapter.setmNoteList(mNoteList);
                                mAdapter.notifyDataSetChanged();
                            }
                        }

                        mView.loadFinish(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
