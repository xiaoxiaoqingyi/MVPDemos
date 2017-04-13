package com.magicing.social3d.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.magicing.social3d.model.bean.ListResult;
import com.magicing.social3d.model.bean.Note;
import com.magicing.social3d.model.dao.ApiService;
import com.magicing.social3d.presenter.view.IHomeView;
import com.magicing.social3d.ui.adapter.HomeListAdapter;
import com.magicing.social3d.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends Presenter{

    private Context mContext;
    private IHomeView mView;
    private RecyclerView mRecyclerView;
    private List<Note> mNoteList = new ArrayList<Note>();
    private HomeListAdapter mAdapter;


    public HomePresenter(Context mContext){
        this.mContext = mContext;
    }

    public void attachView(IHomeView view){
        this.mView = view;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;

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
                                    mAdapter = new HomeListAdapter(mContext, mNoteList);
                                    mRecyclerView.setAdapter(mAdapter);
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
