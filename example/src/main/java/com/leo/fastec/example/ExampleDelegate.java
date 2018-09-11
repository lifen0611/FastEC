package com.leo.fastec.example;

import android.os.Bundle;
import android.view.View;

import com.leo.latte.delegate.LatteDelegate;

public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        //super.onBindView(savedInstanceState, rootView);
    }
}
