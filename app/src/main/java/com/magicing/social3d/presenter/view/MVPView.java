package com.magicing.social3d.presenter.view;

import android.widget.FrameLayout;

public interface MVPView {

    void showLoadingDialog();

    void hideLoadingDialog();

    void showEmptyPrompt(FrameLayout view, String text);

    void hideEmptyPrompt();

}
