package performance.com.example.ndh.myapplication.leak.innerclass;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import performance.com.example.ndh.myapplication.R;

/**
 * Created by ndh on 17/3/7.
 */

public class InnerClassLeak extends Activity {

    private ListView mListView;
    List<String> mDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_class);
        mListView = (ListView) findViewById(R.id.lv);
        initData();
        mListView.setAdapter(new MyAdapter());
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            mDatas.add("list-->" + i);
        }
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (null == convertView) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(InnerClassLeak.this, R.layout.item_inner_class_leak, null);
                viewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.mTextView.setText(mDatas.get(position));

            return convertView;
        }
    }

    private class ViewHolder {
        TextView mTextView;
    }
}
