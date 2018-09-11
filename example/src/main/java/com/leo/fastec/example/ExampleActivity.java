package com.leo.fastec.example;

import android.os.Bundle;

import com.leo.latte.activities.ProxyActivity;
import com.leo.latte.delegate.LatteDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
