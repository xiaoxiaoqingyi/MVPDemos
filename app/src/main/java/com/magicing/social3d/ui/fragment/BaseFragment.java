package com.magicing.social3d.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.magicing.social3d.R;
import com.magicing.social3d.presenter.view.MVPView;


public class BaseFragment extends Fragment implements MVPView {

    private ProgressDialog pDialog;
    private TextView prompt;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void init(){
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(true);
        pDialog.setCanceledOnTouchOutside(false);
    }

    public ProgressDialog getDialog() {
        return pDialog;
    }

    public void setDialogMessage(String message) {
        pDialog.setMessage(message);
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
            prompt = (TextView) LayoutInflater.from(getActivity()).inflate(
                    R.layout.textview, null);
            prompt.setText(text);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            prompt.setLayoutParams(params);
            view.addView(prompt);
        }else {
            if(view.findViewById(R.id.prompt) == null){
                prompt = null;
                showEmptyPrompt(view, text);
            }
            prompt.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void hideEmptyPrompt(){
        if(prompt != null ){
            prompt.setVisibility(View.GONE);
        }
    }





}
