package com.magicing.social3d.model.dao;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2017/03/27.
 */
public class ApiService {

    /**
     * 测试环境地址
     */
    public static final String BASE_HOST = "http://chat.qijingonline.com";


    private static Retrofit mRetrofit;

    /**
     * 获取网络请求实例，初始化相应的参数
     * @return
     */
    public static OkApi getInstance(){
        if(mRetrofit == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(30, TimeUnit.SECONDS);
            builder.readTimeout(300, TimeUnit.SECONDS);
            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_HOST)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        }

        return mRetrofit.create(OkApi.class);

    }
}
