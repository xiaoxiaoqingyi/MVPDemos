package com.magicing.social3d.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.magicing.social3d.R;
import com.magicing.social3d.presenter.view.MVPView;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/27.
 */
public abstract class BaseActivity extends AppCompatActivity implements MVPView {

    protected Toolbar mToolbar;
    private TextView prompt;
    private ProgressDialog pDialog;
    private TextView titleTxt;

    /**
     * 返回内容布局layout
     * 实现该方法后，不需再setContentView 设置内容页面
     * @return
     */
    public abstract int getLayoutResID();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        ButterKnife.bind(this);
        init();
        initToolBar();

    }

    private void init(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.mipmap.icon_back);
        titleTxt = (TextView)mToolbar.findViewById(R.id.titleTxt);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(true);
        pDialog.setCanceledOnTouchOutside(false);
    }

    private void initToolBar() {
        if(mToolbar == null){
            throw new NullPointerException("please add a Toolbar in your layout.");
        }
        setSupportActionBar(mToolbar);

     }

     public void setTitle(String title){
//        super.setTitle(title);
        titleTxt.setText(title);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     }

    public Toolbar getToolbar(){
        return  mToolbar;
    }

    public void setDialogMessage(String message) {
        pDialog.setMessage(message);
    }

    public ProgressDialog getDialog() {
        return pDialog;
    }

    @Override
    public void showLoadingDialog() {
        pDialog.show();
    }

    @Override
    public void hideLoadingDialog() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    @Override
    public void showEmptyPrompt(FrameLayout view, String text) {
        if(prompt == null){
            prompt = (TextView) LayoutInflater.from(this).inflate(
                    R.layout.textview, null);
            prompt.setText(text);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            prompt.setLayoutParams(params);
            view.addView(prompt);
        }else {
            prompt.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideEmptyPrompt() {
        if(prompt != null ){
            prompt.setVisibility(View.GONE);
        }
    }
}
