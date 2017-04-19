package performance.com.example.ndh.mvpdemo.presenter.news;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import performance.com.example.ndh.mvpdemo.R;
import performance.com.example.ndh.mvpdemo.module.news.NetResult;
import performance.com.example.ndh.mvpdemo.view.news.MainView;

/**
 * Created by ndh on 17/4/14.
 */

public class MainPresenterimpl implements MainPresenter, NewsFetchInterface.OnFinishListener {
    private MainView mainView;
    private NewsFetchInterface mNewsFetchInterface;
    List<NetResult.NewsBean> mNews = new ArrayList<>();

    public MainPresenterimpl(MainView view) {
        mainView = view;
        mNewsFetchInterface = new NewsFetchInterfaceImpl((Context) view);
    }


    @Override
    public void onFail(String str) {
        mainView.hideProgress();
        // 获取消息失败  弹出提示
        mainView.showMessage(str);
    }

    @Override
    public void onSuccess(List<NetResult.NewsBean> news) {
        mNews.addAll(news);
        mainView.hideProgress();
        // 获取消息成功  更新listView
        mainView.setItems(mNews);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mNews && mNews.size() > position)
            mNews.get(position).setState(1);
        mainView.showMessage(mNews.get(position).getTitle());
        mainView.nofityDatahasChangeed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_get_news:
                mainView.showProgress();
                mNewsFetchInterface.FetchNews(this);
                break;
        }

    }
}
