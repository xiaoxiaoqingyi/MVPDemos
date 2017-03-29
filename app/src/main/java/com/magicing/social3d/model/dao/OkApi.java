package com.magicing.social3d.model.dao;


import com.magicing.social3d.model.bean.ListResult;
import com.magicing.social3d.model.bean.Note;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/03/27.
 */
public interface OkApi {

    //首页列表
    @FormUrlEncoded
    @POST("/api/getNoteList")
    Observable<ListResult<Note>> getNoteList(@Field("data") String data);


}
