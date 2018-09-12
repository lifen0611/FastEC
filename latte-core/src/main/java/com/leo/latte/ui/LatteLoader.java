package com.leo.latte.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.leo.latte.R;
import com.leo.latte.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class LatteLoader {

    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 10;

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    private static final String DEAFAULT_LOADER_STYLE = LoaderStyle.BallClipRotatePulseIndicator.name();


    public static void showLoading(Context context, LoaderStyle loaderStyle) {
        showLoading(context, loaderStyle.name());
    }

    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();

            layoutParams.width = deviceWidth / LOADER_SIZE_SCALE;
            layoutParams.height = deviceHeight / LOADER_SIZE_SCALE;
            layoutParams.height = layoutParams.height + deviceHeight / LOADER_OFFSET_SCALE;
            layoutParams.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();

    }

    public static void showLoading(Context context) {
        showLoading(context, DEAFAULT_LOADER_STYLE);
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog: LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                    dialog.dismiss();
                }
            }
        }
    }
}
