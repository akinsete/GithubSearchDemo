package com.akinsete.nytgithubsearch.data.network.interceptors;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.IOException;
import java.util.Objects;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by sundayakinsete on 2019-01-17.
 */
public class LoggingInterceptor implements Interceptor {

    private static String mTag = "RetrofitLogging";

    @SuppressLint("DefaultLocale")
    @Override
    @NonNull
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Buffer buffer = new Buffer();
        if (request.body() != null) {
            Objects.requireNonNull(request.body()).writeTo(buffer);
        }

        long t1 = System.nanoTime();
        Log.i(mTag, String.format("Sending request %s %s on %s%n%s%n",
                request.method(), request.url(), chain.connection(), request.headers()));

        if (buffer.size() >= 10000 && request.method().equals("PUT")) {
            Log.v(mTag, "Message larger than 10KB");
        } else {
            Log.v(mTag, buffer.readUtf8());
        }

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        String msg = "";
        if (response.body() != null) {
            msg = response.body().string();
        }
        Log.i(mTag, String.format("Received response %s for %s in %.1fms%n%s",
                response.code(), response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        Log.v(mTag, msg);

        return response.newBuilder()
                .body(ResponseBody.create(response.body() != null ? response.body().contentType() :
                        null, msg))
                .build();
    }
}
