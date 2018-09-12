package com.leo.latte.net;

import com.leo.latte.app.ConfigType;
import com.leo.latte.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitCreator {

    private static final class ParamsHolder {

        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();

    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    public static RetrofitService getRetrofitService() {
        return RetrofitServiceHolder.RETROFIT_SERVICE;
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .build();

    }

    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RetrofitServiceHolder {
        private static final RetrofitService RETROFIT_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RetrofitService.class);
    }
}
