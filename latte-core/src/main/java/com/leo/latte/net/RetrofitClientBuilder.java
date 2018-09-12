package com.leo.latte.net;

import android.content.Context;

import com.leo.latte.net.callback.IError;
import com.leo.latte.net.callback.IFailure;
import com.leo.latte.net.callback.IRequest;
import com.leo.latte.net.callback.ISuccess;
import com.leo.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RetrofitClientBuilder {
    private   String mUrl;
    private  static  final  WeakHashMap<String, Object> PARAMS = RetrofitCreator.getParams();

    private  IRequest mIRequest;
    private  ISuccess mISuccess;
    private  IError mIError;
    private  IFailure mIFailure;

    private  RequestBody mBody;

    private Context mContext;
    private LoaderStyle mLoaderStyle;

    //默认的访问级别， 是只允许同包的RetrofitClient去创建它
    RetrofitClientBuilder(){

    }

    public final RetrofitClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RetrofitClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RetrofitClientBuilder params(String key, Object value) {

        this.PARAMS.put(key, value);
        return  this;
    }
    public final RetrofitClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RetrofitClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RetrofitClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RetrofitClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RetrofitClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }


    private Map<String, Object> checkParams() {
        if (PARAMS == null) {
            return new WeakHashMap<>();
        }
        return PARAMS;
    }

    public final RetrofitClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;

        return this;
    }

    public final RetrofitClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;

        return this;
    }

    public final RetrofitClient build() {
        checkParams();
        return new RetrofitClient(mUrl, PARAMS, mIRequest, mISuccess, mIError,
                mIFailure, mBody, mContext, mLoaderStyle);
    }

}
