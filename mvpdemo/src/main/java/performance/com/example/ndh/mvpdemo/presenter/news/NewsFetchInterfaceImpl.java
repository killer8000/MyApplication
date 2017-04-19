package performance.com.example.ndh.mvpdemo.presenter.news;

import android.content.Context;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import performance.com.example.ndh.mvpdemo.module.news.NetResult;
import performance.com.example.ndh.mvpdemo.net.NetApi;
import performance.com.example.ndh.mvpdemo.net.RetrofitManager;

/**
 * Created by ndh on 17/4/14.
 */

public class NewsFetchInterfaceImpl implements NewsFetchInterface {
    private Context mContext;
    private OnFinishListener mListener;

    public NewsFetchInterfaceImpl(Context context) {
        mContext = context;
    }

    @Override
    public void FetchNews(OnFinishListener listener) {
        mListener = listener;
        requestNews("http://api.dagoogle.cn/");
    }


    private void requestNews(String url) {
        RetrofitManager.getRetrofit(mContext.getApplicationContext(), url).create(NetApi.class).requestNews(1, 20, 1)
                .subscribeOn(Schedulers.io())
                .map(new Function<NetResult, List<NetResult.NewsBean>>() {
                    @Override
                    public List<NetResult.NewsBean> apply(NetResult news) throws Exception {
                        if (null != news)
                            return news.getData();
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<NetResult.NewsBean>>() {
                    @Override
                    public void accept(final List<NetResult.NewsBean> newsBeen) throws Exception {
                        mListener.onSuccess(newsBeen);
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mListener.onFail(throwable.toString());
                    }
                })
        ;
    }
}
