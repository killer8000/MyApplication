package performance.com.example.ndh.mvvmdemo.presenter.news;

import java.util.List;

import performance.com.example.ndh.mvvmdemo.module.news.NetResult;


/**
 * Created by ndh on 17/4/14.
 * 业务接口  获取消息
 */

public interface NewsFetchInterface {
    void FetchNews(OnFinishListener listener);

    interface OnFinishListener {
        void onFail(String erro);

        void onSuccess(List<NetResult.NewsBean> news);
    }
}
