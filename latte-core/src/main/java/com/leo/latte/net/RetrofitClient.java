package com.leo.latte.net;

import android.content.Context;

import com.leo.latte.net.callback.IError;
import com.leo.latte.net.callback.IFailure;
import com.leo.latte.net.callback.IRequest;
import com.leo.latte.net.callback.ISuccess;
import com.leo.latte.net.callback.RequstCallbacks;
import com.leo.latte.ui.LatteLoader;
import com.leo.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Url;

public class RetrofitClient {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RetrofitCreator.getParams();

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;

    private final RequestBody BODY;

    //loading
    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;


    RetrofitClient(String url, WeakHashMap<String, Object> params,
                   IRequest request, ISuccess success,
                   IError error, IFailure failure,
                   RequestBody body, Context context,
                   LoaderStyle loaderStyle) {
        URL = url;
        PARAMS.putAll(params);
        REQUEST = request;
        SUCCESS = success;
        ERROR = error;
        FAILURE = failure;
        BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }


    public static RetrofitClientBuilder builder() {
        return new RetrofitClientBuilder();
    }

    private void request(HttpMethod method) {
        RetrofitService service = RetrofitCreator.getRetrofitService();

        Call<String> call = null;

        //请求开始
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallbacks());
        }

    }

    private RequstCallbacks getRequestCallbacks() {
        return new RequstCallbacks(REQUEST, SUCCESS, ERROR, FAILURE, LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
