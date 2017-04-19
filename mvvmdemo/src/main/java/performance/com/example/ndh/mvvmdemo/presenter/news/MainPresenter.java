package performance.com.example.ndh.mvvmdemo.presenter.news;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import performance.com.example.ndh.mvvmdemo.adapter.NewsAdapter;
import performance.com.example.ndh.mvvmdemo.databinding.ActivityMain1Binding;
import performance.com.example.ndh.mvvmdemo.module.news.NetResult;

/**
 * Created by ndh on 17/4/14.
 * 用户交互接口
 */

public class MainPresenter {
    NewsFetchInterface mInterface;
    public ObservableBoolean showProgress = new ObservableBoolean();
    ViewDataBinding mBinding;
    Context mContext;
    List<NetResult.NewsBean> mNews;

    public MainPresenter(ViewDataBinding binding, Context context) {
        mInterface = new NewsFetchInterfaceImpl(context.getApplicationContext());
        mBinding = binding;
        mContext = context;
        ((ActivityMain1Binding) mBinding).lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mNews.get(position).setState(1);
            }
        });
    }

    public void onClick(View view) {
        showProgress.set(true);
        mInterface.FetchNews(new NewsFetchInterface.OnFinishListener() {
            @Override
            public void onFail(String erro) {
                showProgress.set(false);
            }

            @Override
            public void onSuccess(List<NetResult.NewsBean> news) {
                showProgress.set(false);
                mNews = news;
                ((ActivityMain1Binding) mBinding).setAdapter(new NewsAdapter(mNews, mContext));
            }
        });
    }
}
