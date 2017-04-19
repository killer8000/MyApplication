package performance.com.example.ndh.mvvmdemo.net;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ndh on 17/3/31.
 */

public class RetrofitManager {
    static Retrofit mRetrofit;

    private RetrofitManager(Context context, String baseUrl) {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .cache(new Cache(new File(context.getExternalCacheDir(), "catch"), 10 * 1024 * 1024))
                .connectTimeout(10000, TimeUnit.SECONDS)// 超时
                .retryOnConnectionFailure(true)// 重试
                .addInterceptor(httpLoggingInterceptor)// 拦截器
                .addNetworkInterceptor(new MyIntercepte())//网络拦截器
                .build();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)// 添加自定义的client
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))//格式化后的gson
                .build();
    }

    public static Retrofit getRetrofit(Context context,String url) {
        new RetrofitManager(context,url);
        return mRetrofit;
    }

    private static class MyIntercepte implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
//            request.header()  一般加一些全局的东西在这里
            Response response = chain.proceed(request);
            return response;
        }
    }
}
