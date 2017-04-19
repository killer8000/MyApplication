package performance.com.example.ndh.mvvmdemo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import performance.com.example.ndh.mvvmdemo.BR;
import performance.com.example.ndh.mvvmdemo.R;
import performance.com.example.ndh.mvvmdemo.module.news.NetResult;


/**
 * Created by ndh on 17/4/14.
 */

public class NewsAdapter extends BaseAdapter {
    private Context mContext;
    List<NetResult.NewsBean> mNews;

    public NewsAdapter(List<NetResult.NewsBean> news, Context context) {
        mContext = context;
        mNews = news;
    }

    @Override
    public int getCount() {
        return mNews.size();
    }

    @Override
    public Object getItem(int position) {
        return mNews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding binding = null;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item, parent, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }
        // 绑定数据 BR.news 是item.xml 定义的变量
        binding.setVariable(BR.news, mNews.get(position));
        return binding.getRoot();
    }

}
