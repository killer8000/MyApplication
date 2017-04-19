package performance.com.example.ndh.rxandroiddemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import performance.com.example.ndh.rxandroiddemo.R;
import performance.com.example.ndh.rxandroiddemo.realm.NewsBean;


/**
 * Created by ndh on 17/4/14.
 */

public class NewsAdapter extends BaseAdapter {
    private Context mContext;
    List<NewsBean> mNews;

    public NewsAdapter(List<NewsBean> news, Context context) {
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(mNews.get(position).getTitle());
        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }
}
