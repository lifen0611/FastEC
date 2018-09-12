package com.leo.fastec.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.leo.latte.delegate.LatteDelegate;
import com.leo.latte.net.RetrofitClient;
import com.leo.latte.net.callback.IError;
import com.leo.latte.net.callback.IFailure;
import com.leo.latte.net.callback.ISuccess;

public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        //super.onBindView(savedInstanceState, rootView);
        testRetrofitClient();
    }

    private void testRetrofitClient() {
        RetrofitClient.builder().url("http://news.baidu.com")
                .params("", "")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(Object response) {
                        //Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                }).build().get();
    }
}
