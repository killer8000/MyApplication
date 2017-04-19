package performance.com.example.ndh.mvpdemo.view.news;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import performance.com.example.ndh.mvpdemo.R;
import performance.com.example.ndh.mvpdemo.module.news.NetResult;
import performance.com.example.ndh.mvpdemo.presenter.news.MainPresenter;
import performance.com.example.ndh.mvpdemo.presenter.news.MainPresenterimpl;
import performance.com.example.ndh.mvpdemo.view.news.adapter.NewsAdapter;

public class MainActivity extends Activity implements MainView, View.OnClickListener, AdapterView.OnItemClickListener {
    MainPresenter mMainPresenter;
    private Button mButton;
    private ProgressBar mProgressBar;
    private ListView mListView;
    NewsAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.bt_get_news);
        mProgressBar = (ProgressBar) findViewById(R.id.pb);
        mListView = (ListView) findViewById(R.id.lv);
        mListView.setOnItemClickListener(this);
        mButton.setOnClickListener(this);
        mMainPresenter = new MainPresenterimpl(this);
    }


    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setItems(List<NetResult.NewsBean> news) {
        adapter = new NewsAdapter(news, this);
        mListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void nofityDatahasChangeed() {
        if (null != adapter) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        mMainPresenter.onClick(v);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mMainPresenter.onItemClick(parent, view, position, id);
    }
}
