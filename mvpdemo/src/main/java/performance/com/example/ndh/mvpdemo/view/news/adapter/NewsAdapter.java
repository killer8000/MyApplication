package performance.com.example.ndh.mvpdemo.view.news.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import performance.com.example.ndh.mvpdemo.R;
import performance.com.example.ndh.mvpdemo.base.CommonAdapter;
import performance.com.example.ndh.mvpdemo.module.news.NetResult;

/**
 * Created by ndh on 17/4/19.
 */

public class NewsAdapter extends CommonAdapter<NetResult.NewsBean, NewsHolder> {
    public NewsAdapter(Context context) {
        super(context);
    }

    public NewsAdapter(List<NetResult.NewsBean> datas, Context context) {
        super(datas, context);
    }

    @Override
    protected NewsHolder initViewHolder() {
        return new NewsHolder();
    }

}
