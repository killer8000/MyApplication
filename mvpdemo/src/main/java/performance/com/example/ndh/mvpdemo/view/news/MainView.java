package performance.com.example.ndh.mvpdemo.view.news;

import java.util.List;

import performance.com.example.ndh.mvpdemo.module.news.NetResult;

/**
 * Created by ndh on 17/4/14.
 * 界面更新接口
 */

public interface MainView {


    void showProgress();

    void hideProgress();

    void setItems(List<NetResult.NewsBean> news);
    void showMessage(String str);
    void nofityDatahasChangeed();
}
