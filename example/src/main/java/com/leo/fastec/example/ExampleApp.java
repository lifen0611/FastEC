package com.leo.fastec.example;

import android.app.Application;

import com.leo.latte.app.Latte;

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Latte.init(this).
                withApiHost("127.0.0.1")
                .configure();

    }
}
