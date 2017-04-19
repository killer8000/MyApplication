package performance.com.example.ndh.mvpdemo.view.news.adapter;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import performance.com.example.ndh.mvpdemo.R;
import performance.com.example.ndh.mvpdemo.base.CommonViewHolder;
import performance.com.example.ndh.mvpdemo.module.news.NetResult;

/**
 * Created by ndh on 17/4/19.
 */

public class NewsHolder extends CommonViewHolder<NetResult.NewsBean> {
    TextView mTextView;

    @Override
    public int getItemLayout() {
        return R.layout.item;
    }

    @Override
    public void initItemView() {
        mTextView = findViewById(R.id.tv_item);
    }

    @Override
    protected void initItemData(int position, List<NetResult.NewsBean> list, View root) {
        NetResult.NewsBean bean = list.get(position);
        mTextView.setText(bean.getTitle());
        if (bean.getState() == 0) {
            mTextView.setTextColor(root.getContext().getResources().getColor(R.color.colorPrimary));
        } else {
            mTextView.setTextColor(root.getContext().getResources().getColor(R.color.colorAccent));
        }
    }
}
